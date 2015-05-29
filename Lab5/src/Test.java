import java.util.ArrayList;


public class Test {


    public static void main(String[] args) {

        Systems system = new Systems(40, 10, 4, 5);
        system.createArrayTask();
        system.createProcess();

        System.out.println(system.taskArray.toString());
        //system.first();
        FirstAlgorithm first = new FirstAlgorithm(system);
        first.run();
        System.out.println(first.time);

    }
}
