import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.random;


public class LocalArray {

    static int[] appealsArray;
    static int appeal;
    static int range;

    public static int[] generateAppeals(int random, int numberOfPages) {
        appealsArray = new int[10 * numberOfPages];
        range = (int) (0.1 * numberOfPages);
        appeal = (int) (random() * numberOfPages);// [0,iloscStron - 1]

        for (int i = 1; i < appealsArray.length; i++) {
            int jump = (int) (random() * 100);// [0,99]
            if (jump == 0) {
                longJump(numberOfPages);
            } else if (jump < 10) {
                shortJump(numberOfPages);// [1,9]
            } else {
                next(numberOfPages);
            }
            appealsArray[i] = appeal;
        }

        return appealsArray;
    }

    public static void next(int numberOfPages) {
        if (appeal == 0) {
            appeal++;
        } else if (appeal == numberOfPages - 1) {// zakres żeby nie ywszło po
            // za
            appeal--;
        } else {
            int lucky = (int) (random() * 2);// los [0,1]
            if (lucky == 0) {
                appeal++;
            } else {
                appeal--;
            }
        }
    }

    public static void shortJump(int numberOfPages) {
        if (appeal < 2) {
            shortJumpRight(numberOfPages);// 0,1
        } else if (appeal > numberOfPages - 3) {
            shortJumpLeft();
        } else {
            int lucky = (int) (Math.random() * 2);// [0,1]
            if (lucky == 0) {
                shortJumpRight(numberOfPages);
            } else {
                shortJumpLeft();
            }
        }
    }

    public static void shortJumpRight(int numberOfPages) {
        appeal = min((int) (random() * range + appeal + 2), numberOfPages - 1);// dla
        // granicznych
        // moze
        // wyjsc
        // poza
        // zakres
        if (appeal > numberOfPages) {
            appeal = numberOfPages - 1;
        }
    }

    public static void shortJumpLeft() {
        appeal = max(0, (int) (random() * range + appeal - 3));
    }

    public static void longJump(int numberOfPages) {
        if (appeal < range + 2) {
            longJumpRight(numberOfPages);
        } else if (appeal > numberOfPages - 2 - range) {
            longJumpLeft();
        } else {
            int lucky = (int) (Math.random() * 2);// [0,1]
            if (lucky == 0) {
                longJumpRight(numberOfPages);
            } else {
                longJumpLeft();
            }
        }
    }

    public static void longJumpRight(int numberOfPages) {
        appeal = min((int) (random() * (numberOfPages - appeal - 2 - range)
                + appeal + 2 + range), numberOfPages - 1);
        if (appeal > numberOfPages) {
            appeal = numberOfPages - 1;
        }
    }

    public static void longJumpLeft() {
        appeal = max(0, (int) (random() * (appeal - range - 1)));
    }
}
