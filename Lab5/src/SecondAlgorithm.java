import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Created by Glove on 2015-05-31.
 */
public class SecondAlgorithm {
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

    public SecondAlgorithm(Systems systems,  ArrayList<Task> array) {
        this.systems = systems;
        this.procesorNumber = systems.procesorArray.length;
        this.task = array;
    }



    public void run() {
        int find = 0;
        find = random.nextInt(procesorNumber);
        current = systems.procesorArray[find];
        while (!task.isEmpty() || !queue.isEmpty()) { // dodawanie do procesorow zadan
            addToQueue();
            increaseEndTimeInQueue();
            active = queue.poll();
            if (active != null) {
                if (current.getPower() > systems.verge) {
                    find = random.nextInt(procesorNumber);
                    current = systems.procesorArray[find];
                    if (current.getPower() < systems.verge) {
                        current.addPower(active.workLoad);
                        current.arrayTask.add(active);
                        undoneTaskArray.add(active);
                    } else {
                        queue.add(active);//odloz na kolejke i poczekaj
                    }
                    askNumber++;
                } else {
                    current.addPower(active.workLoad);
                    current.arrayTask.add(active);
                    undoneTaskArray.add(active);
                }
            }
            check();
            time++;
        }

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
            allSummary += summary / active.workLoad.size();
            System.out.println("Procesor " + (i + 1) + " srednie obciazenie: " + summary / active.workLoad.size());
        }
        System.out.println("Calkowite srednie obciazenie: " + allSummary / systems.procesorArray.length);    }
}

