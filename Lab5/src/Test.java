import java.util.ArrayList;

public class Test {

    static ArrayList<Task> array;

    private static ArrayList<Task> copyArray(ArrayList<Task> app) {
        ArrayList<Task> application = new ArrayList<Task>();
        for (Task a : app) {
            application.add(a);
        }
        return application;
    }

    public static void main(String[] args) {

        Systems system = new Systems(40, 60, 4, 100);
        system.createProcess();
        ArrayList<Task> firstArray = system.createArrayTask();
        ArrayList<Task> secondArray = system.createArrayTask();
        ArrayList<Task> thirdArray = system.createArrayTask();



        System.out
                .println("===============================================================");
        System.out.println("\t\tPierwszy Algorytm");
        System.out
                .println("===============================================================");

        FirstAlgorithm first = new FirstAlgorithm(system,copyArray(firstArray));
        first.run();
        System.out.println("Licba zapytan: " + first.askNumber);
        System.out.println("Liczba migracji: " + first.migration);
        first.average();

        System.out
                .println("===============================================================");
        System.out.println("\t\tDrugi Algorytm");
        System.out
                .println("===============================================================");
        system.createProcess();

        SecondAlgorithm second = new SecondAlgorithm(system, copyArray(secondArray));
        second.run();
        System.out.println("Licba zapytan: " + second.askNumber);
        System.out.println("Liczba migracji: " + second.migration);
        second.average();

        System.out
                .println("===============================================================");
        System.out.println("\t\tTrzeci Algorytm");
        System.out
                .println("===============================================================");

        system.createProcess();
        ThirdAlgorithm third = new ThirdAlgorithm(system, copyArray(thirdArray));
        third.run();
        System.out.println("Licba zapytan: " + third.askNumber);
        System.out.println("Liczba migracji: " + third.migration);
        third.average();
    }
}
