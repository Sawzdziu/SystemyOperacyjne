import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Random;


public class Systems {
	
	Processor procesorArray[];
	ArrayList<Task> taskArray;
	Random random;
	int time;
	int verge;//prog - p
	int chanceTime;
	int minVerge;//min prog - r
	int numberOfTask;
	TimeComparator comparator = new TimeComparator();
	PriorityQueue<Task> queue = new PriorityQueue<Task>(comparator);
	
	Systems(int p, int r, int z, int N){
		this.verge = p;
		this.minVerge = r;
		this.chanceTime = z;
		this.numberOfTask = N;
		procesorArray = new Processor[10];
		comparator = new TimeComparator();
		random = new Random();
	}
	
	public ArrayList<Task> createArrayTask(){
		ArrayList<Task>	 array = new ArrayList<Task>();
		for(int i = 0; i < numberOfTask;i++){
			array.add(new Task());
		}
		taskArray = array;
		return array;
	}
	
	public void addToQueue() {
		while (taskArray.iterator().hasNext()) {
			Task task = taskArray.iterator().next();
			if (task.comeTime <= time) {
				queue.offer(task);
				taskArray.iterator().remove();
			}
		}
	}
	
	
	public void first(){
		while(!queue.isEmpty() || !taskArray.isEmpty()){
			addToQueue();
		}
	}
	
}
