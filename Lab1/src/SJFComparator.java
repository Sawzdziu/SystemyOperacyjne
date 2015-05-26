import java.util.Comparator;

public class SJFComparator implements Comparator<Proces> {
	public int compare(Proces a, Proces b) {
		int c = 0;
		if (a.phaseLenght > b.phaseLenght) {
			c = 1;
		}
		if (a.phaseLenght < b.phaseLenght) {
			c = -1;
		}
		return c;
	}
}