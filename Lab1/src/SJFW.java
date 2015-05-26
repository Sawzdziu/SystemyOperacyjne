import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

public class SJFW {
	int sumOfTime = 0;
	int time = 0;
	SJFComparator comparator = new SJFComparator();
	ArrayList<Proces> adding = new ArrayList<Proces>();
	PriorityQueue<Proces> queue = new PriorityQueue<Proces>(comparator);
	Proces active = null;
	ArrayList<Proces> ended = new ArrayList<Proces>();

	public SJFW(ArrayList<Proces> add) {
		this.adding = add;
	}

	public double go() {
		while (!queue.isEmpty() || !adding.isEmpty()) {
			addToQueue();
			active = queue.poll();
			while(active == null){
				time++;
				addToQueue();
				active = queue.poll();
			}
			if (!queue.isEmpty()) {
				if (queue.peek().phaseLenght < active.phaseLenght) {
					queue.offer(active);
					active = queue.poll();
				}
			}
			if (active.phaseLenght == active.remainingTime) {
				active.waitingTime = time - active.comeTime;
			}

			active.remainingTime--;
			Iterator<Proces> it = queue.iterator();
			while (it.hasNext()) {
				it.next().waitingTime++;
			}
			if (active.remainingTime <= 0) {
				sumOfTime += active.waitingTime;
				ended.add(active);
			} else {
				queue.offer(active);
			}
			time++;
			System.out.println(active);
		}
		return summary();
	}

	public void addToQueue() {
		Iterator<Proces> iter = adding.iterator();
		while (iter.hasNext()) {
			Proces proc = iter.next();
			if (proc.comeTime == time) {
				queue.offer(proc);
				iter.remove();
			}
		}
	}

	public double summary() {
		int numberOfProcess = 0;
		double waitTime = 0;
		for (Proces proc : ended) {
			numberOfProcess++;
			waitTime += proc.waitingTime;
		}
		System.out.println("Liczba procesow: " + numberOfProcess);
		/*
		 * System.out.println("SJFW"); System.out.println("Liczba procesow:  " +
		 * liczbaProcesow); System.out.println("Łączny czas wykonywania:  " +
		 * czas); System.out.println("Sredni czas oczekiwania:  " +
		 * CzasOczekiwania / liczbaProcesow);
		 */

		return waitTime / numberOfProcess;
	}

}