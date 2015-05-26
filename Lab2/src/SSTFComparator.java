import java.util.Comparator;

public class SSTFComparator implements Comparator<Application> {
	private int headPosition;

	public SSTFComparator(int head) {
		this.headPosition = head;
	}

	@Override
	public int compare(Application o1, Application o2) {
		int a = Math.abs(o1.getBlock() - this.headPosition);
		int b = Math.abs(o2.getBlock() - this.headPosition);
		if (a < b) {
			return -1;
		} else if (a > b) {
			return 1;
		}
		return 0;
	}
	
	public int getHeadPosition(){
		return this.headPosition;
	}
}
