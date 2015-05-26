public class Proces {

    public static final int MAX_NUMBER_OF_PAGES = 50;

    int[] appeals;
    Page[] arrayPages;
    static int number = 1;
    int lp;
    int moment;
    int numberOfLostPages;
    int reference;
    int numberOfVictim;
    int timeMin;
    boolean done;

    public Proces() {
        lp = number++;
        int random = (int) (Math.random() * MAX_NUMBER_OF_PAGES);
        arrayPages = new Page[random];
        for (int i = 0; i < arrayPages.length; i++) {
            arrayPages[i] = new Page();
        }
        appeals = LocalArray.generateAppeals(random, arrayPages.length);
        done = false;
    }
}
