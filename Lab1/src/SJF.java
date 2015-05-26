import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

public class SJF {
	int time = 0;
	SJFComparator comparator = new SJFComparator();
	ArrayList<Proces> adding = new ArrayList<Proces>();
	PriorityQueue<Proces> queue = new PriorityQueue<Proces>(comparator);
	Proces active = null;
	ArrayList<Proces> ended = new ArrayList<Proces>();

	public SJF(ArrayList<Proces> add) {
		this.adding = add;
	}

	public double go() {
		while (!queue.isEmpty() || !adding.isEmpty()) {
			addToQueue();
			active = queue.poll();
			if (active != null) {
				active.waitingTime = time - active.comeTime;
				/*
				 * System.out.println("Czas zgłoszenia wynosi " +
				 * aktywny.momentZgloszenia);
				 * System.out.println("Czas trwania wynosi " +
				 * aktywny.dlugoscFazyProcesora);
				 */

				if (time < active.comeTime) {
					time += (active.comeTime - time);
				}
				time += active.phaseLenght;
				ended.add(active);
			} else {
				time++;
			}
		}
		return summary();
	}

	public void addToQueue() {
		Iterator<Proces> iter = adding.iterator();
		while (iter.hasNext()) {
			Proces proc = iter.next();
			if (proc.comeTime <= time) {
				queue.offer(proc);
				iter.remove();
			}
		}
	}

	public double summary() {
		int numberOfProcess = 0;
		double waitingTime = 0;
		for (Proces proc : ended) {
			// System.out.println(proc.czasOczekiwania);
			numberOfProcess++;
			waitingTime += proc.waitingTime;
		}
		/*
		 * System.out.println("SJF"); System.out.println("Liczba procesow:  " +
		 * liczbaProcesow); System.out.println("Łączny czas wykonywania:  " +
		 * czas); System.out.println("Sredni czas oczekiwania:  " +
		 * CzasOczekiwania / liczbaProcesow);
		 */

		return waitingTime / numberOfProcess;
	}
}
