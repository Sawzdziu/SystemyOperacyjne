import java.util.ArrayList;


public class Systems {

    Processor procesorArray[];
    int verge;//prog - p
    int chanceTime;
    int minVerge;//min prog - r
    int numberOfTask;

    Systems(int p, int r, int z, int N) {
        this.verge = p;
        this.minVerge = r;
        this.chanceTime = z;
        this.numberOfTask = N;
        procesorArray = new Processor[10];
    }

    public void createProcess() {
        for (int i = 0; i < procesorArray.length; i++) {
            procesorArray[i] = new Processor();
        }
    }

    public ArrayList<Task> createArrayTask() {
        ArrayList<Task> array = new ArrayList<Task>();
        for (int i = 0; i < numberOfTask; i++) {
            array.add(new Task());
        }
        return array ;
    }
}
