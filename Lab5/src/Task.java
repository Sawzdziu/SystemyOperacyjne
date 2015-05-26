import java.util.Random;

public class Task {

	int comeTime;
	int endTime;
	int workLoad;

	Task(int comeTime, int endTime, int workLoad) {
		this.comeTime = comeTime;
		this.endTime = endTime;
		this.workLoad = workLoad;
	}

	Task() {
		Random random = new Random();
		this.comeTime = random.nextInt(250);
		this.endTime = random.nextInt(250) + comeTime;
		this.workLoad = random.nextInt(80);
	}

	public String toString() {
		return "ComeTime: " + comeTime + " EndTime: " + endTime
				+ " Obciazenie: " + workLoad;
	}
}
