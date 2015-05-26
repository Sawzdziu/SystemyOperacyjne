public class RAND {
	
	int[] data;
	int[] frames;
	Page[] arrayPages;
	int moment;
	int numberOfLostPages;
	int reference;

	RAND(int[] data, int numberOfFrames, int numberOfPages) {
		this.data = data;
		this.frames = new int[numberOfFrames];// potem inicjacja
		for (int i = 0; i < frames.length; i++)
			frames[i] = -1;
		this.arrayPages = new Page[numberOfPages];
		for (int i = 0; i < numberOfPages; i++)
			arrayPages[i] = new Page();
	}

	boolean isInMemory() {
		return arrayPages[reference].getBit() == 1;
	}

	public void algorithm() {
		int number = 0;
		int numberOfEntry = 0;
		reference = 0;
		
		while (numberOfEntry != frames.length && moment < data.length) {
			reference = data[number++];
			boolean exist = isInMemory();
			if (!exist) {
				// System.out.println("nie zawiera "+reference);
				frames[numberOfEntry++] = reference;
				numberOfLostPages++;
				arrayPages[reference].setTime(moment);// uaktualniam
				arrayPages[reference].setBit(1);
			}
			moment++;
		}// while-end
		
		while (moment < data.length) {
			reference = data[number++];
			//System.out.println("pobralo");
			// czy strona jest w pamięci?:
			boolean exist = isInMemory();
			// System.out.println("sprawdzilo");
			if (!exist) {
				// System.out.print("nie zawiera "+odniesienie);
				// losuje
				int numberOfVictim = (int) (Math.random() * frames.length);// wskazuje
																	// na indeks
																	// w ramki[]
				// System.out.println("wylosowalo ofiare");
				// System.out.println(" za "+ramki[ofiara]);
				arrayPages[frames[numberOfVictim]].setTime(-1);
				arrayPages[frames[numberOfVictim]].setBit(0);
				// podmiana:
				frames[numberOfVictim] = reference;
				arrayPages[reference].setTime(moment);// uaktualnienie czasu
				arrayPages[reference].setBit(1);
				numberOfLostPages++;
			}// if-end
			moment++;
		}// while-end
	}

}