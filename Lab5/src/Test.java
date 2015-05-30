import java.util.ArrayList;

public class Test {

    static ArrayList<Task> arrayTask;

    private static ArrayList<Task> arrayCopy(ArrayList<Task> task) {
        ArrayList<Task> wyn = new ArrayList<Task>();
        for (Task v : task) {
            wyn.add(v);
        }
        return wyn;
    }

    public static void main(String[] args) throws CloneNotSupportedException {

        Systems system = new Systems(40, 10, 4, 100);
        arrayTask = system.createArrayTask();
        system.createProcess();

        FirstAlgorithm first = new FirstAlgorithm(system, arrayCopy(arrayTask));
        first.run();
        System.out.println("Licba zapytan: " + first.askNumber);
        first.average();

        System.out.println("Drugi algorytm");

        SecondAlgorithm second = new SecondAlgorithm(system, arrayCopy(arrayTask));
        second.run();
        System.out.println("Licba zapytan: " + second.askNumber);
        second.average();
    }
}
