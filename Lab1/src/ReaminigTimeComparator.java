import java.util.Comparator;

public class ReaminigTimeComparator implements Comparator<Proces> {
	public int compare(Proces a, Proces b) {
		int c = 0;
		if (a.remainingTime > b.remainingTime) {
			c = 1;
		}
		if (a.remainingTime < b.remainingTime) {
			c = -1;
		}
		return c;
	}
}