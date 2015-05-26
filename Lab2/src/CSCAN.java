import java.util.ArrayList;
import java.util.Iterator;

public class CSCAN {

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
		this.headPosition = headPosition;
		this.direction = direction;
		this.size = array.size();
		if (this.direction.equals("Left")) {
			leftArray.sort(comparator.reversed());
			rightArray.sort(comparator.reversed());
			// System.out.println(leftArray);
			// System.out.println(rightArray);
			for (Application cscan : leftArray) {
				distance += Math.abs(this.headPosition - cscan.getBlock());
				this.headPosition = cscan.getBlock();
			}
			this.headPosition = MAX_BLOCK;
			for (Application cscan : rightArray) {
				distance += Math.abs(this.headPosition - cscan.getBlock());
				this.headPosition = cscan.getBlock();
			}
		} else if (this.direction.equals("Right")) {
			leftArray.sort(comparator);
			rightArray.sort(comparator);
			// System.out.println(leftArray);
			// System.out.println(rightArray);
			for (Application cscan : rightArray) {
				distance += Math.abs(this.headPosition - cscan.getBlock());
				this.headPosition = cscan.getBlock();
			}
			this.headPosition = 0;
			for (Application cscan : leftArray) {
				distance += Math.abs(this.headPosition - cscan.getBlock());
				this.headPosition = cscan.getBlock();
			}
		}
		return distance;
	}

	public int runFD(ArrayList<Application> array, int headPosition) {
		this.applicationArray = new ArrayList<Application>(array);
		// System.out.println(applicationArray);
		this.queue = new ArrayList<Application>();
		this.headPosition = headPosition;
		int direct = 1;
		while (!applicationArray.isEmpty() || !queue.isEmpty()) {
			addToQueue();
			if (queue.isEmpty()) {
				time++;
			} else {
				SSTFComparator comparator = new SSTFComparator(
						this.headPosition);
				this.queue.sort(comparator);
				this.active = queue.get(0);
				// System.out.println(this.active);
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
					}
					if (this.headPosition == MAX_BLOCK) {
						this.headPosition = 0;
					} else {
						this.headPosition += direct;
						distance++;
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

	public ArrayList<Application> sortLeft(ArrayList<Application> array,
			int position) {
		ArrayList<Application> leftArray = new ArrayList<Application>();
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i).getBlock() <= position) {
				leftArray.add(array.get(i));
			}
		}
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
		return rightArray;
	}
}
