public class ALRU {

	
	int[] data;
	int[] frames;
	Page[] arrayPages;
	int moment;
	int numberOfLostPages;
	int reference;
	int timeMin;
	int numberOfVictim;

	public ALRU(int[] data, int numberOfFrames, int numberOfPages) {
		this.data = data;
		this.frames = new int[numberOfFrames];
		for (int i = 0; i < frames.length; i++)
			frames[i] = -1;
		this.arrayPages = new Page[numberOfPages];
		for (int i = 0; i < numberOfPages; i++)
			arrayPages[i] = new Page();
	}

	boolean isInMemory() {
		for (int j = 0; j < frames.length; j++) {
			if (reference == frames[j])// 
				return true;
		}
		return false;
	}

	public void lookForVictim() {
		timeMin = moment;// na pewno nie ma czasu wiekszego
		numberOfVictim = 0;
		for (int j = 0; j < arrayPages.length; j++) {
			if (arrayPages[j].getTime() != -1
					&& arrayPages[j].getTime() < timeMin) {
				timeMin = arrayPages[j].getTime();// czas minimalny
				numberOfVictim = j;// bo strony sa uporzadkowane wg numerów
			}
		}// w tym momencie czas minimalny zostal ustalony i numer strony do
			// wymiany również
	}

	public void algorithm() {
		int numberOfEntry = 0;
		int number = 0;
		reference = 0;

		while (numberOfEntry != frames.length && number < data.length) {
			reference = data[number++];
			boolean exist = isInMemory();
			if (!exist) {
				//System.out.println("nie zawiera " + reference);
				frames[numberOfEntry++] = reference;
				numberOfLostPages++;
				arrayPages[reference].setTime(moment);
				arrayPages[reference].setBit(1);// to trwa takt
				moment++;
			} else
			//	System.out.println("zawiera " + reference);

			moment++;
		}// while-end

		while (number < data.length) {
			reference = data[number++];
			boolean exist = isInMemory();
			if (!exist) {// znajdz element do wymiany->najmniejszy czas,
							// wykluczajac zera
			//	System.out.print("nie zawiera " + reference);
				boolean replace = false;
				while (!replace) {
					// szukam ofiary
					lookForVictim();
					if (arrayPages[numberOfVictim].getBit() == 0) {
					//	System.out.println(" za " + numberOfVictim);
						arrayPages[numberOfVictim].setTime(-1);// czas od
																// sprowadzeniaDoPamieci
																// ustawiam na
																// -1->proces
																// nie jest w
																// pamięci;

						// szukam miejsca w ramkach, gdzie była ofiara:
						boolean found = false;
						for (int j = 0; j < frames.length && !found; j++) {
							if (numberOfVictim == frames[j]) {// tu była stara
																// strona
								found = true;
								frames[j] = reference;
								arrayPages[reference].setTime(moment);// uaktualnienie
																	// czasu
								arrayPages[reference].setBit(1);// to trwa takt
								moment++;
							}
						}
						replace = true;
						numberOfLostPages++;
						// takt++;
					} else {// druga szansa dla tej strony
						arrayPages[numberOfVictim].setTime(moment);
						arrayPages[numberOfVictim].setBit(0);// to trwa takt
						moment++;
					//	System.out.println("2.szansa dla " + numberOfVictim);
					}
				}
			}// if-end
			else {
			//	System.out.println("zawiera " + reference);
				arrayPages[reference].setBit(1);//
				moment++;//
			}

		}// while-end

	}
}