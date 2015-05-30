import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Created by Glove on 2015-05-29.
 */
public class FirstAlgorithm {

    int counter;
    int askNumber;
    int time;
    int procesorNumber;
    Systems systems;
    Random random = new Random();
    Processor current;
    Task active;
    TimeComparator comparator = new TimeComparator();
    PriorityQueue<Task> queue = new PriorityQueue<Task>(comparator);
    ArrayList<Task> task;
    ArrayList<Task> undoneTaskArray = new ArrayList<Task>();

    public FirstAlgorithm(Systems systems, ArrayList<Task> array) {
        this.systems = systems;
        this.procesorNumber = systems.procesorArray.length;
        this.task = array;
    }

    public void run() {
        int find = 0;
        while (!task.isEmpty() || !queue.isEmpty()) { // dodawanie do procesorow zadan
            addToQueue();
            increaseEndTimeInQueue();
            active = queue.poll();
            if (active != null) {
                if (counter < systems.chanceTime) {
                    find = random.nextInt(procesorNumber);
                    current = systems.procesorArray[find];
                    if (current.getPower() < systems.verge) {
                        current.addPower(active.workLoad);
                        current.arrayTask.add(active);
                        undoneTaskArray.add(active);
                        counter = 0;
                    } else {
                        queue.add(active);//odloz na kolejke i poczekaj
                        counter++;
                    }
                    askNumber++;
                } else {
                    counter = 0;
                    current.addPower(active.workLoad);
                    current.arrayTask.add(active);
                    undoneTaskArray.add(active);
                }
            }
            check();
            time++;
        }
        time--;
        endTask();
    }

    public void addToQueue() {
        Iterator<Task> iter = task.iterator();
        while (iter.hasNext()) {
            Task task = iter.next();
            if (task.comeTime <= time) {
                queue.offer(task);
                iter.remove();
            }
        }
    }

    public void increaseEndTimeInQueue() {
        Iterator<Task> iterator = queue.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            task.addTime();
        }
    }


    public void check() {
        for (int i = 0; i < systems.procesorArray.length; i++) {
            Processor active = systems.procesorArray[i];
            if (!active.arrayTask.isEmpty()) {
                Iterator<Task> iterator = active.arrayTask.iterator();
                while (iterator.hasNext()) {
                    Task help = iterator.next();
                    if (help.endTime == time) {
                        active.subPower(help.workLoad);
                        undoneTaskArray.remove(help);
                        iterator.remove();
                    }
                }
            } else {
                continue;
            }
        }

    }

    public void endTask() {
        while (!undoneTaskArray.isEmpty()) {
            for (int i = 0; i < systems.procesorArray.length; i++) {
                Processor active = systems.procesorArray[i];
                if (!active.arrayTask.isEmpty()) {
                    Iterator<Task> iterator = active.arrayTask.iterator();
                    while (iterator.hasNext()) {
                        Task help = iterator.next();
                        if (help.endTime == time) {
                            active.subPower(help.workLoad);
                            undoneTaskArray.remove(help);
                            iterator.remove();
                        }
                    }
                } else {
                    continue;
                }
            }
            time++;
        }
        time--;
        System.out.println("Czas: " + time);
    }

    public void average() {
        int allSummary = 0;
        for (int i = 0; i < systems.procesorArray.length; i++) {
            Processor active = systems.procesorArray[i];
            int summary = 0;
            for (Integer value : active.workLoad) {
                summary += value;
            }
            System.out.println("Procesor " + (i + 1) + " srednie obciazenie: " + summary / active.workLoad.size());
            allSummary += summary / active.workLoad.size();
        }
        System.out.println("Calkowite srednie obciazenie: " + allSummary / systems.procesorArray.length);
    }

}
