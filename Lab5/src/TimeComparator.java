import java.util.Comparator;


public class TimeComparator implements Comparator<Task>{

	@Override
	public int compare(Task o1, Task o2) {
		if(o1.comeTime > o2.comeTime){
			return 1;
		}else if(o1.comeTime < o2.comeTime){
			return -1;
		}else{
			return 0;
		}
	}

}
