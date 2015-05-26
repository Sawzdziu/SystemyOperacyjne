import java.util.Comparator;

public class FCFSComparator implements Comparator<Proces> {
	public int compare(Proces a, Proces b) {
		int c = 0;
		if (a.comeTime > b.comeTime) {
			c = 1;
		}
		if (a.comeTime < b.comeTime) {
			c = -1;
		}
		return c;
	}
}