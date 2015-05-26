import java.util.ArrayList;
import java.util.Iterator;

public class RR {
	int time = 0;
	private int quantOfTime = 10;
	ArrayList<Proces> adding = new ArrayList<Proces>();
	ArrayList<Proces> queue = new ArrayList<Proces>();
	Proces active = null;
	ArrayList<Proces> ended = new ArrayList<Proces>();

	public RR() {
	}

	public double go(ArrayList<Proces> add) {
		this.adding = add;
		int quantCounter = quantOfTime;
		while (!queue.isEmpty() || !adding.isEmpty()) {
			addToQueue(time);

			if (queue.size() > 0) {
				active = queue.get(0);
				if (active.getRemainingTime() != 0 && quantCounter != 0) {
					active.setRemainingTime(active.getRemainingTime() - 1);
					quantCounter--;
					for (int i = 1; i < queue.size(); i++) {
						queue.get(i).waitingTime++;
					}
				}
				if (active.getRemainingTime() != 0 && quantCounter == 0) {
					quantCounter = quantOfTime;
					//System.out.println("Kwant się skończył");
					queue.remove(active);
					queue.add(queue.size(), active);
				}
				/*
				 * System.out.println("Czas: " + time);
				 * System.out.println("Active: " + active);
				 * System.out.println();
				 */
			}
			time++;
			if (active != null && active.getRemainingTime() == 0) {
				ended.add(active);
				queue.remove(active);
				quantCounter = quantOfTime;
			}
		}
		return summary();
	}

	public void addToQueue(int timeGlobal) {
		Iterator<Proces> iter = adding.iterator();
		Proces proc = null;
		while (iter.hasNext()) {
			proc = iter.next();
			if (proc.comeTime <= time) {
				queue.add(proc);
				iter.remove();
			}
		}
	}

	public double summary() {
		int numberOfProcess = 0;
		double waitingTime = 0;
		for (Proces proc : ended) {
			numberOfProcess++;
			waitingTime += proc.waitingTime;

		}
		/*
		 * System.out.println("RR"); System.out.println("Liczba procesow:  " +
		 * liczbaProcesow); System.out.println("Łączny czas wykonywania:  " +
		 * czas); System.out.println("Sredni czas oczekiwania:  " +
		 * CzasOczekiwania / liczbaProcesow);
		 */

		return waitingTime / numberOfProcess;
	}

}