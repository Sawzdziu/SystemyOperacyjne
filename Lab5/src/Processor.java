import java.util.ArrayList;

public class Processor {

    int power = 0;
    ArrayList<Task> arrayTask = new ArrayList<Task>();


    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public ArrayList<Task> getArrayTask() {
        return arrayTask;
    }

    public void setArrayTask(ArrayList<Task> arrayTask) {
        this.arrayTask = arrayTask;
    }


    public void subPower(int power) {
        this.power -= power;
    }

    public void addPower(int power) {
        this.power += power;
    }


}
