import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Created by Glove on 2015-05-31.
 */
public class ThirdAlgorithm {
    int askNumber;
    int time;
    int procesorNumber;
    int migration;
    Systems systems;
    Random random = new Random();
    Processor current;
    Processor ask;
    Task active;
    TimeComparator comparator = new TimeComparator();
    PriorityQueue<Task> queue = new PriorityQueue<Task>(comparator);
    ArrayList<Task> task;
    ArrayList<Task> undoneTaskArray = new ArrayList<Task>();

    public ThirdAlgorithm(Systems systems, ArrayList<Task> array) {
        this.systems = systems;
        this.procesorNumber = systems.procesorArray.length;
        this.task = new ArrayList<Task>(array);
    }


    public void run() {
       // System.out.println(task.toString());
        int find = 0;
        int take = 0;
        ask = systems.procesorArray[find];
        while (!task.isEmpty() || !queue.isEmpty()) { // dodawanie do procesorow zadan
            increaseEndTimeInQueue();
            addToQueue();
            active = queue.poll();
            if (active != null) {
                if (ask.getPower() < systems.minVerge) {
                    find = random.nextInt(procesorNumber);
                    current = systems.procesorArray[find];
                    migration++;
                    if (current.getPower() > systems.verge) {
                        take = random.nextInt(current.getPower());
                        ask.addPower(take);
                        current.subPower(take);
                        current.addPower(active.workLoad);
                        current.arrayTask.add(active);
                        ask.arrayTask.add(active);
                    } else {
                        ask.addPower(active.workLoad);
                        ask.workLoad.add(active.workLoad);
                        ask.arrayTask.add(active);
                        undoneTaskArray.add(active);
                    }
                    askNumber++;
                } else {
                    if ((current.getPower() + active.workLoad) > 100) {
                        queue.add(active);
                    } else {
                        current.addPower(active.workLoad);
                        current.arrayTask.add(active);
                        undoneTaskArray.add(active);
                        ask = systems.procesorArray[random.nextInt(procesorNumber)];
                        migration++;
                    }
                    askNumber++;
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
            if (systems.procesorArray[i].workLoad.isEmpty()) {
                continue;
            }
            Processor active = systems.procesorArray[i];
            int summary = 0;
            for (Integer value : active.workLoad) {
                summary += value;
            }
            allSummary += summary / active.workLoad.size();
            System.out.println("Procesor " + (i + 1) + " srednie obciazenie: " + summary / active.workLoad.size());
        }
        System.out.println("Calkowite srednie obciazenie: " + allSummary / systems.procesorArray.length);
    }

}

