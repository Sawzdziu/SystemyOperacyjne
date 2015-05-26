import static java.lang.Math.*;

public class Test {

	private static final int NUMBER_OF_APPEALS = 1000;
	private static final int NUMBER_OF_PAGES = 20;
	private static final int NUMBER_OF_FRAMES = 15;
	static int[] appealsArray;
	static int appeal;
	static int range;

	public static int[] generateAppeals() {
		appealsArray = new int[NUMBER_OF_APPEALS];
		range = (int) (0.1 * NUMBER_OF_PAGES);
		appeal = (int) (random() * NUMBER_OF_PAGES);// [0,iloscStron - 1]

		for (int i = 1; i < NUMBER_OF_APPEALS; i++) {
			int jump = (int) (random() * 100);// [0,99]
			if (jump == 0) {
				longJump();
			} else if (jump < 10) {
				shortJump();// [1,9]
			} else {
				next();
			}
			appealsArray[i] = appeal;
		}
		
		return appealsArray;
	}

	public static void next() {
		if (appeal == 0) {
			appeal++;
		} else if (appeal == NUMBER_OF_PAGES - 1) {// zakres żeby nie ywszło po
													// za
			appeal--;
		} else {
			int lucky = (int) (random() * 2);// los [0,1]
			if (lucky == 0) {
				appeal++;
			} else {
				appeal--;
			}
		}
	}

	public static void shortJump() {
		if (appeal < 2) {
			shortJumpRight();// 0,1
		} else if (appeal > NUMBER_OF_PAGES - 3) {
			shortJumpLeft();
		} else {
			int lucky = (int) (Math.random() * 2);// [0,1]
			if (lucky == 0) {
				shortJumpRight();
			} else {
				shortJumpLeft();
			}
		}
	}

	public static void shortJumpRight() {
		appeal = min((int) (random() * range + appeal + 2), NUMBER_OF_PAGES - 1);// dla
																					// granicznych
																					// moze
																					// wyjsc
																					// poza
																					// zakres
		if(appeal > NUMBER_OF_PAGES){
			appeal = NUMBER_OF_PAGES - 1;
		}
	}

	public static void shortJumpLeft() {
		appeal = max(0, (int) (random() * range + appeal - 3));
	}

	public static void longJump() {
		if (appeal < range + 2) {
			longJumpRight();
		} else if (appeal > NUMBER_OF_PAGES - 2 - range) {
			longJumpLeft();
		} else {
			int lucky = (int) (Math.random() * 2);// [0,1]
			if (lucky == 0) {
				longJumpRight();
			} else {
				longJumpLeft();
			}
		}
	}

	public static void longJumpRight() {
		appeal = min((int) (random() * (NUMBER_OF_PAGES - appeal - 2 - range)
				+ appeal + 2 + range), NUMBER_OF_PAGES - 1);
		if(appeal > NUMBER_OF_PAGES){
			appeal = NUMBER_OF_PAGES - 1;
		}
	}

	public static void longJumpLeft() {
		appeal = max(0, (int) (random() * (appeal - range - 1)));
	}

	public static void main(String[] args) {
		
		int[] array = generateAppeals();

		int[] ciagOdwolan = new int[] { 1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5 };
		int[] alruCiag = new int[] { 1, 2, 3, 4, 5, 3, 1, 5, 2 };

		FIFO fifo = new FIFO(array, NUMBER_OF_FRAMES, NUMBER_OF_PAGES);
		fifo.algorithm();
		System.out.println("Fifo " + fifo.numberOfLostPages);

		OPT opt = new OPT(array, NUMBER_OF_FRAMES, NUMBER_OF_PAGES);
		opt.algorithm();
		System.out.println("Optymalny: " + opt.numberOfLostPages);

		LRU lru = new LRU(array, NUMBER_OF_FRAMES, NUMBER_OF_PAGES);
		lru.algorithm();
		System.out.println("LRU " + lru.numberOfLostPages);

		ALRU alru = new ALRU(array, NUMBER_OF_FRAMES, NUMBER_OF_PAGES);
		alru.algorithm();
		System.out.println("ALRU " + alru.numberOfLostPages);

		RAND rand = new RAND(array, NUMBER_OF_FRAMES, NUMBER_OF_PAGES);
		rand.algorithm();
		System.out.println("Rand: " + rand.numberOfLostPages);

	}
}
