public class LRU {

	int[] data;
	int[] frames;
	Page[] arrayPages;
	int moment;
	int numberOfLostPages;
	int reference;
	int numberOfVictim;
	int timeMin;

	public LRU(int[] data, int numberOfFrames, int numberOfPages) {
		this.data = data;
		this.frames = new int[numberOfFrames];
		for (int i = 0; i < frames.length; i++) {
			frames[i] = -1;
		}
		this.arrayPages = new Page[numberOfPages];
		for (int i = 0; i < numberOfPages; i++) {
			this.arrayPages[i] = new Page();
		}
	}

	boolean isInMemory() {
		return arrayPages[reference].getBit() == 1;
	}

	void lookForVictim() {
		timeMin = moment;
		numberOfVictim = 0;
		for (int i = 0; i < arrayPages.length; i++) {
			if (arrayPages[i].getTime() != -1
					&& arrayPages[i].getTime() < timeMin) {
				timeMin = arrayPages[i].getTime();
				numberOfVictim = i;
			}
		}
	}

	public void algorithm() {
		int numberOfEntry = 0;
		int number = 0;

		while (numberOfEntry != frames.length && moment < data.length) {
			reference = data[number++];
			boolean contains = isInMemory();
			if (!contains) {
				// System.out.println("nie zawiera "+odniesienie);
				frames[numberOfEntry++] = reference;
				numberOfLostPages++;
				arrayPages[reference].setBit(1);
			}
			// else System.out.println("zawiera "+odniesienie);

			arrayPages[reference].setTime(moment);// uaktualniam
			moment++;
		}// while-end
		
		while (moment < data.length) {
			reference = data[number++];
			boolean contains = isInMemory();
			if (!contains) {// znajdz element do wymiany->najmniejszy czas,
							// wykluczajac zera
				// szukam ofiary:
				lookForVictim();

				// System.out.println(" za "+numerOfiary);
				arrayPages[numberOfVictim].setTime(-1);// usuwamy z pamieci i
														// "zerujemy" czas
				arrayPages[numberOfVictim].setBit(0);
				// szukam miejsca w ramkach, gdzie była ofiara:
				boolean found = false;
				for (int j = 0; j < frames.length && !found; j++) {
					if (numberOfVictim == frames[j]) {// tu była stara strona
						found = true;
						frames[j] = reference;
						numberOfLostPages++;
						arrayPages[reference].setTime(moment);// uaktualnienie
															// czasu
						arrayPages[reference].setBit(1);
					}
				}
			}// if-end
			else {
				// System.out.println("zawiera "+odniesienie);
				arrayPages[reference].setTime(moment);// uaktualnienie czasu
			}
			moment++;
		}// while-end
	}

}
