public class Test {

    private static final int NUMBER_OF_FRAMES = 60;

    public static Proces[] copyArray(Proces[] procesy) {
        Proces[] copy = new Proces[procesy.length];

        System.arraycopy(procesy, 0, copy, 0, procesy.length);
        return copy;
    }

    public static void main(String[] args) {

        int result = 0;
        int numberOfProces = 1;

        Proces[] procesArray = new Proces[10];
        Proces[] copy;
        for (int i = 0; i < procesArray.length; i++) {
            procesArray[i] = new Proces();
        }

        copy = copyArray(procesArray);

        for (Proces p : copy) {
            LRU lru = new LRU(p.appeals, Constant.divide(procesArray,
                    NUMBER_OF_FRAMES), p.arrayPages.length);
            lru.algorithm();
            result += lru.numberOfLostPages;
            System.out.println("Proces" + (numberOfProces++) + ": "
                    + lru.numberOfLostPages);

			 /*// System.out.println("Liczba stron: " + p.arrayPages.length);
			  System.out.println("Zawartosc odwolan: "); for(int i = 0; i <
			  p.appeals.length; i ++){ System.out.print(p.appeals[i]+", "); }
			 */
        }
        System.out.println("Suma: " + result + "\n");
        result = 0;
        numberOfProces = 0;

        // copy = copyArray(procesArray);

        System.out.println("Proporcjonalny:");
        for (int i = 0; i < copy.length; i++) {

            LRU lru = new LRU(copy[i].appeals, Proportional.divide(procesArray,
                    NUMBER_OF_FRAMES, i), copy[i].arrayPages.length);
            lru.algorithm();
            result += lru.numberOfLostPages;
            System.out.println("Proces" + (numberOfProces++) + ": "
                    + lru.numberOfLostPages);
        }
        System.out.println("Suma:" + result + "\n");

        result = 0;
        numberOfProces = 0;

        // copy = copyArray(procesArray);

        System.out.println("Strefowy: ");
        for (int i = 0; i < copy.length; i++) {

            LRU lru = new LRU(copy[i].appeals, Zonal.divide(procesArray,
                    NUMBER_OF_FRAMES, i), copy[i].arrayPages.length);
            lru.algorithm();
            result += lru.numberOfLostPages;
            System.out.println("Proces" + (numberOfProces++) + ": "
                    + lru.numberOfLostPages);
        }
        System.out.println("Suma:" + result + "\n");
    }

}
