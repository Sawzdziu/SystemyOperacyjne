import java.util.Random;

public class Application {

	private final int MAX_BLOCK = 400;
	private int block;
	private int number;
	private int cameTime;
	private int deadlineTime;
	private boolean done;
	private boolean priority;

	public Application(int number) {
		Random random = new Random();
		this.block = random.nextInt(MAX_BLOCK + 1);
		this.cameTime = random.nextInt(MAX_BLOCK + 1);
		this.number = number;
		this.priority = getRandomBoolean();
		this.done = false;
		this.deadlineTime = this.cameTime + 300;
	}

	public Application(int number, int block) {
		this.block = block;
		this.number = number;
	}


	public int getBlock() {
		return this.block;
	}

	public boolean getDone() {
		return this.done;
	}

	public void setDone() {
		this.done = true;
	}

	public int getDeadlineTime() {
		return this.deadlineTime;
	}

	public static boolean getRandomBoolean() {
		return Math.random() < 0.5;
	}

	public boolean getPriority() {
		return this.priority;
	}

	public int getCameTime() {
		return this.cameTime;
	}

	public String toString() {
		return "Numer: " + this.number + " Pozycja: " + this.block
				+ " Czas zgloszenia: " + this.cameTime + " Priority: "
				+ this.priority + " Deadline: " + this.deadlineTime;
	}

}
