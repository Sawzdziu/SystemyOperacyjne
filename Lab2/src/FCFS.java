import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Random;

public class FCFS {

	private int time = 0;
	private int distance;
	private int abandonDeadline;
	private int allDeadlines;
	private int headPosition;
	ArrayList<Application> applicationArray;
	ArrayList<Application> queue;
	Application active = null;
	TimeComparator comparator = new TimeComparator();

	public int run(ArrayList<Application> array, int headPosition) {
		this.applicationArray = new ArrayList<Application>(array);
		this.headPosition = headPosition;
		for (Application fcfs : applicationArray) {
			this.distance += Math.abs(this.headPosition - fcfs.getBlock());
			this.headPosition = fcfs.getBlock();
		}
		return this.distance;
	}

	public int runEDF(ArrayList<Application> array, int headPosition) {
		this.applicationArray = new ArrayList<Application>(array);
	//	System.out.println(applicationArray);
		this.queue = new ArrayList<Application>();
		this.headPosition = headPosition;
		while (!applicationArray.isEmpty() || !queue.isEmpty()) {
			addToQueue();
			if (queue.isEmpty()) {
				time++;
			} else {
				this.active = queue.get(0);
				if (this.active.getPriority()) {
					if (Math.abs(this.active.getBlock() - this.headPosition) + time > this.active
							.getDeadlineTime()) {
					//	System.out.println("Opuszczone: " + this.active);
						this.abandonDeadline++;
						queue.remove(this.active);
						continue;
					}
					if (active.getBlock() > this.headPosition) {
						this.headPosition++;
						this.distance++;
					} else if (active.getBlock() < this.headPosition) {
						this.headPosition--;
						this.distance++;
					}
					if (active.getBlock() == this.headPosition) {
				//		System.out.println("Wykonane: " + this.active);
						this.allDeadlines++;
						queue.remove(this.active);
					}
				} else {
					if (active.getBlock() > this.headPosition) {
						this.headPosition++;
						this.distance++;
					} else if (active.getBlock() < this.headPosition) {
						this.headPosition--;
						this.distance++;
					}
					if (active.getBlock() == this.headPosition) {
						queue.remove(this.active);
					}
				}
				time++;
			}
		}
		System.out.println("Wykonane deadliny: " + this.allDeadlines);
		System.out.println("Opuszczone deadliny: " + this.abandonDeadline);
		return this.distance;
	}

	public void addToQueue() {
		Iterator<Application> iter = applicationArray.iterator();
		while (iter.hasNext()) {
			Application app = iter.next();
			if (app.getCameTime() == time) {
				if (app.getPriority()) {
					queue.add(0, app);
					iter.remove();
				} else {
					queue.add(app);
					iter.remove();
				}
			}
		}

	}
}