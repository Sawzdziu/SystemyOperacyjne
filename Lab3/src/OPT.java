public class OPT {

	int[] data;
	int[] frames;
	Page[] arrayPages;
	int takt;
	int numberOfLostPages;
	int reference;

	public OPT(int[] data, int numberOfFrames, int numberOfPages) {
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

	public void algorithm() {
		int numberOfEntry = 0;// ilosc wpisow
		int number = 0;
		// zapełmniamy wszystkie ramki
		while (numberOfEntry != frames.length && takt < data.length) {
			reference = data[number++];
			boolean contains = isInMemory();
			if (!contains) {
				// System.out.println("Nie zawiera " + reference);
				arrayPages[reference].setBit(1);
				frames[numberOfEntry++] = reference;
				numberOfLostPages++;
			}
			// System.out.println("Zawiera " + reference);
			takt++;
		}
		// obsługa po zapełnieniu ramek
		while (takt < data.length) {
			reference = data[number++];
			boolean contains = isInMemory();
			if (!contains) {
			//	System.out.println("Nie zawiera " + reference);
				int deletFrameNumber = lookForVictim();
				arrayPages[frames[deletFrameNumber]].setBit(0);
				arrayPages[reference].setBit(1);
				frames[deletFrameNumber] = reference;
				numberOfLostPages++;
			}
			takt++;
		}
	}

	int lookForVictim() {
		int maxDistance = -1;
		int indexOfVictim = -1;// indeks ofiary
		int distance = -1;
		for (int i = 0; i < frames.length; i++) {// dla kazdej ramki przeszukuje
													// nastepne odniesienia
			boolean find = false;
			int j = takt;// -1, bo na pozycji takt nie ma, wchodzimy tu jesli
							// elementu nie ma wramce
			for (; j < data.length && !find; j++) {// czy strona powtorzy sie w
													// ciagu odwolan?; bez -1
				if (frames[i] == data[j]) {
					find = true;
				}
			}
			if (find) {
				distance = j - takt;
				if (distance > maxDistance) {
					maxDistance = distance;
					indexOfVictim = i;
				}
			} else {
				indexOfVictim = i;//ofiara bo sie nie pojawi wcale, wiec odleglosc max
				break;// wyjscie z fora glownego
			}
		}
		return indexOfVictim;
	}

}
