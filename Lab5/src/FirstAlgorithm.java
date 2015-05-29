import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Created by Glove on 2015-05-29.
 */
public class FirstAlgorithm {

    int counter;
    int time;
    int procesorNumber;
    Systems systems;
    Random random = new Random();
    Processor current;
    Task active;
    TimeComparator comparator = new TimeComparator();
    PriorityQueue<Task> queue = new PriorityQueue<Task>(comparator);

    public FirstAlgorithm(Systems systems) {
        this.systems = systems;
        this.procesorNumber = systems.procesorArray.length - 1;
    }

    public int run() {
        int find = 0;
        while (!systems.taskArray.isEmpty() || !queue.isEmpty()) {
            addToQueue();
            active = queue.peek();
            if (active != null) {
                if (counter < systems.chanceTime) {
                    find = random.nextInt(procesorNumber);
                    current = systems.procesorArray[find];
                    if (current.getPower() < systems.verge) {
                        current.subPower(active.workLoad);
                        current.arrayTask.add(active);
                    } else {
                        queue.add(active);//od³ó¿ na kolejke i poczekaj
                    }
                    time++;
                }
            } else {
                time++;
            }
            check();
        }
        return time;
    }

    public void addToQueue() {
        Iterator<Task> iter = systems.taskArray.iterator();
        while (iter.hasNext()) {
            Task task = iter.next();
            if (task.comeTime <= time) {
                queue.offer(task);
                iter.remove();
            }
        }
    }

    public void check() {
        Processor active;
        for (int i = 0; i < systems.procesorArray.length; i++) {
            active = systems.procesorArray[i];
            if (!active.arrayTask.isEmpty()) {
                Iterator<Task> iterator = active.arrayTask.iterator();
                while (iterator.hasNext()) {
                    if (iterator.next().endTime == time) {
                        active.addPower(iterator.next().workLoad);
                        iterator.remove();
                    }
                }
            } else {
                continue;
            }
        }

    }
}
