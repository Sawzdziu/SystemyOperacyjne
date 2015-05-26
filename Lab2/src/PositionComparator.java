import java.util.Comparator;

public class PositionComparator implements Comparator<Application> {

	public int compare(Application a, Application b) {
		if (a.getBlock() > b.getBlock()) {

			return 1;
		} else if (a.getBlock() < b.getBlock()) {
			return -1;
		} else
			return 0;
	}

}
