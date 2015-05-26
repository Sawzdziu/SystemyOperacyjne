import java.util.ArrayList;
import java.util.PriorityQueue;

public class FCSF {

	int time = 0;
	FCFSComparator comparator = new FCFSComparator();
	PriorityQueue<Proces> queue = new PriorityQueue<Proces>(comparator);
	Proces active = null;
	ArrayList<Proces> ended = new ArrayList<Proces>();

	public FCSF(ArrayList<Proces> queue) {
		for (Proces proc : queue) {
			this.queue.offer(proc);
		}
	}

	public double go() {
		while (queue.isEmpty() != true) {
			active = queue.poll();
			if ((time - active.comeTime) < 0) {
				active.waitingTime = 0;
			} else {
				active.waitingTime = time - active.comeTime;
			}
			/*
			 * System.out.println("Czas trwania wynosi " +
			 * aktywny.dlugoscFazyProcesora);
			 * System.out.println("Czas zgłoszenia wynosi " +
			 * aktywny.momentZgloszenia);
			 */
			if (time < active.comeTime) {
				time += (active.comeTime - time);
			}
			time = time + active.phaseLenght;
			ended.add(active);
		}
		return summary();
	}

	public double summary() {
		int numberOfProcess = 0;
		double waitingTime = 0;
		for (Proces proc : ended) {
			numberOfProcess++;
			waitingTime += proc.waitingTime;
		}

		/*
		 * System.out.println("FCFS"); System.out.println("Liczba procesow:  " +
		 * liczbaProcesow); System.out.println("Łączny czas wykonywania:  " +
		 * time); System.out.println("Sredni czas oczekiwania:  " +
		 * CzasOczekiwania / liczbaProcesow);
		 */

		return waitingTime / numberOfProcess;
	}
}
