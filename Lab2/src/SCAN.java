import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SCAN {

	private final int MAX_BLOCK = 400;
	private int distance;
	private int headPosition;
	private int size;
	private int abandonDeadline;
	private int doneDeadlines;
	private int time = 0;
	private String direction;
	Application active = null;
	ArrayList<Application> queue;
	ArrayList<Application> applicationArray;
	ArrayList<Application> leftArray;
	ArrayList<Application> rightArray;
	PositionComparator comparator = new PositionComparator();

	public int run(ArrayList<Application> array, int headPosition,
			String direction) {
		this.leftArray = sortLeft(array, headPosition);
		this.rightArray = sortRight(array, headPosition);
		// System.out.println(leftArray);
		// System.out.println(rightArray);
		this.headPosition = headPosition;
		this.direction = direction;
		this.size = array.size();
		if (this.direction.equals("Left")) {
			for (Application scan : leftArray) {
				distance += Math.abs(this.headPosition - scan.getBlock());
				this.headPosition = scan.getBlock();
			}
			for (Application scan : rightArray) {
				distance += Math.abs(this.headPosition - scan.getBlock());
				this.headPosition = scan.getBlock();
			}
		} else if (this.direction.equals("Right")) {
			for (Application scan : rightArray) {
				distance += Math.abs(this.headPosition - scan.getBlock());
				this.headPosition = scan.getBlock();
			}
			for (Application scan : leftArray) {
				distance += Math.abs(this.headPosition - scan.getBlock());
				this.headPosition = scan.getBlock();
			}
		}
		return distance;
	}

	public int runFD(ArrayList<Application> array, int headPosition) {
		this.applicationArray = new ArrayList<Application>(array);
		// System.out.println(applicationArray);
		this.queue = new ArrayList<Application>();
		this.headPosition = headPosition;
		int direct = -1;
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
						// System.out.println("Opuszczone: " + this.active);
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
						// System.out.println("Wykonane: " + this.active);
						this.doneDeadlines++;
						queue.remove(this.active);
					}
				} else {
					int max = queue.get(0).getBlock();
					int min = queue.get(0).getBlock();
					Iterator<Application> it = queue.iterator();
					while (it.hasNext()) {
						Application app = it.next();
						if (app.getBlock() == this.headPosition) {
							it.remove();
						}
						if (app.getBlock() < min) {
							min = app.getBlock();
						}
						if (app.getBlock() > max) {
							max = app.getBlock();
						}
					}
					if (max < this.headPosition) {
						direct = -1;
					}
					if (min > this.headPosition) {
						direct = 1;
					}
					this.headPosition += direct;
					distance++;
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

	public ArrayList<Application> sortLeft(ArrayList<Application> array,
			int position) {
		ArrayList<Application> leftArray = new ArrayList<Application>();
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i).getBlock() <= position) {
				leftArray.add(array.get(i));
			}
		}
		leftArray.sort(comparator.reversed());
		return leftArray;
	}

	public ArrayList<Application> sortRight(ArrayList<Application> array,
			int position) {
		ArrayList<Application> rightArray = new ArrayList<Application>();
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i).getBlock() > position) {
				rightArray.add(array.get(i));
			}
		}
		rightArray.sort(comparator);
		return rightArray;
	}
}
