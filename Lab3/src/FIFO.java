public class FIFO {

	int[] data;
	int[] frames;
	Page[] arrayPages;
	int moment;
	int numberOfLostPages;
	int reference;
	int numberOfVictim;
	int timeMin;

	public FIFO(int[] data, int numberOfFrames, int numberOfPages) {
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
		// System.out.println("Referencja: "+reference);
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

	void algorithm() {
		int numberOfEntry = 0;// ilosc wpisow
		int number = 0;
		reference = 0;
		// while do pierwszego zapelnienia
		while (numberOfEntry != frames.length && moment < data.length) {
			reference = data[number++];
			boolean contains = isInMemory();
			if (!contains) {
				// System.out.println("Nie zawiera " + reference);
				frames[numberOfEntry++] = reference;
				numberOfLostPages++;
				arrayPages[reference].setTime(moment);
				arrayPages[reference].setBit(1);
			}
			// System.out.println("Zawiera " + reference);
			moment++;
		}
		// obsluga po zapelnieniu wszystkich ramek
		while (moment < data.length) {
			reference = data[number++];
			boolean contains = isInMemory();
			if (!contains) {
				// System.out.println("Nie zawiera " + reference);
				lookForVictim();
				// System.out.println("Za " + numberOfVictim);
				arrayPages[numberOfVictim].setTime(-1);
				arrayPages[numberOfVictim].setBit(0);
				for (int i = 0; i < frames.length; i++) {
					if (numberOfVictim == frames[i]) {
						frames[i] = reference;
					}
				}
				arrayPages[reference].setTime(moment);
				arrayPages[reference].setBit(1);
				numberOfLostPages++;
			}
			moment++;
		}
	}

	void print() {
		System.out.printf("|%-10s|%-15s|%-20s|\n", "FIFO", frames.length,
				numberOfLostPages);
	}

}
