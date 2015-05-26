import java.util.Comparator;

public class TimeComparator implements Comparator<Application> {

	@Override
	public int compare(Application o1, Application o2) {
		if (o1.getCameTime() > o2.getCameTime()) {
			return 1;
		} else if (o1.getCameTime() < o2.getCameTime()) {
			return -1;
		}
		return 0;
	}

}
