public class Zonal {

    static final int OKNO = 5;

    public static int count(Proces[] procesy, int numberOfFrames, int which) {
        int howMany = 0;
        boolean exist;
        int value;
        for (int i = 0; i < procesy[which].appeals.length; i += OKNO) {
            int[] temp = new int[OKNO];
            for (int n = 0; n < OKNO; n++) {
                temp[n] = 0;
            }
            exist = false;
            for (int j = i; j - i < OKNO; j++) {
                if (j < procesy[which].appeals.length) {
                    value = procesy[which].appeals[j];
                    for (Integer l : temp) {
                        if (l == value) {
                            exist = true;
                        }
                    }
                    temp[j - i] = value;
                    if (!exist) {
                        howMany++;
                    }
                }
            }
        }
        return howMany;
    }

    public static int divide(Proces[] procesy, int numberOfFrames, int which) {

        int allOfTheProces = 0;
        int current = count(procesy, numberOfFrames, which);

        for (int i = 0; i < procesy.length; i++) {
            allOfTheProces += count(procesy, numberOfFrames, i);
        }

        double result = (numberOfFrames * current);
        result = result / allOfTheProces;
        if ((int) result == 0) {
            return 1;
        } else {
            return (int) result;
        }
    }
}
