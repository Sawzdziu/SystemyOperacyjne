import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class SSTF {

	private final int MAX_BLOCK = 400;
	private int minDifference;
	private int distance;
	private int headPosition;
	private int index;
	private int doneDeadlines;
	private int abandonDeadline;
	private int time = 0;
	Application active = null;
	ArrayList<Application> applicationArray;
	ArrayList<Application> queue;

	public int run(ArrayList<Application> array, int headPosition) {
		this.applicationArray = new ArrayList<Application>(array);
		//System.out.println(applicationArray);
		this.headPosition = headPosition;
		this.index = 0;
		this.minDifference = MAX_BLOCK;
		while (!applicationArray.isEmpty()) {
			for (Application sstf : applicationArray) {
				int help = Math.abs(this.headPosition - sstf.getBlock());
				boolean done = sstf.getDone();
				if ((this.minDifference > help) && !done) {
					this.minDifference = help;
					this.index = applicationArray.indexOf(sstf);
				}
			}
			//System.out.println(applicationArray.get(this.index));
			this.distance += this.minDifference;
			this.minDifference = MAX_BLOCK;
			applicationArray.get(index).setDone();
			this.headPosition = applicationArray.get(this.index).getBlock();
			this.applicationArray.remove(this.index);
		}
		return this.distance;
	}

	public int runEDF(ArrayList<Application> array, int headPosition) {
		this.applicationArray = new ArrayList<Application>(array);
		//System.out.println(applicationArray);
		this.queue = new ArrayList<Application>();
		this.headPosition = headPosition;
		while (!applicationArray.isEmpty() || !queue.isEmpty()) {
			addToQueue();
			if (queue.isEmpty()) {
				time++;
			} else {
				SSTFComparator comparator = new SSTFComparator(
						this.headPosition);
				this.queue.sort(comparator);
				this.active = queue.get(0);
				//System.out.println(this.active);
				if (this.active.getPriority()) {
					if (Math.abs(this.active.getBlock() - this.headPosition)
							+ time > this.active.getDeadlineTime()) {
						//System.out.println("Opuszczone: " + this.active);
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
						//System.out.println("Wykonane: " + this.active);
						this.doneDeadlines++;
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
		System.out.println("Wykonane deadliny: " + this.doneDeadlines);
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
