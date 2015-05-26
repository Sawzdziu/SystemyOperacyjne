public class Proportional {

    public static int divide(Proces[] proces, int numberOfFrames, int which) {
        int pages = 0;
        for (int i = 0; i < proces.length; i++) {
            pages += proces[i].arrayPages.length;
        }
        double result = (numberOfFrames * proces[which].arrayPages.length);
        // System.out.println("Liczba stron: " + pages);
        result /= pages;
        //System.out.println("Liczba ramek otrzymanych: " + (int) result);
        if ((int) result == 0) {
            return 1;
        } else {
            return (int) result + 1;
        }
    }
}
