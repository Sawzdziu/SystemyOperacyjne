import java.util.ArrayList;


public class Test {

    static ArrayList<Task> taskArray;

    public static void main(String[] args) {

        Systems system = new Systems(40, 10, 4, 50);
        taskArray = system.createArrayTask();
        //System.out.println(taskArray.toString());
        system.first();
    }
}
