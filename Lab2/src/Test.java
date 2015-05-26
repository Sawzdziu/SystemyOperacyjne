import java.util.ArrayList;
import java.util.Arrays;

public class Test {

	private static final int NUMBER_OF_APPLICATION = 20;
	private static final int HEAD_POSITION = 53;
	static TimeComparator comparator = new TimeComparator();

	public static ArrayList<Application> createApplication() {
		ArrayList<Application> applicationArray = new ArrayList<Application>();
		for (int i = 0; i < NUMBER_OF_APPLICATION; i++) {
			applicationArray.add(new Application(i));
		}
		return applicationArray;
	}

	private static ArrayList<Application> copyArray(ArrayList<Application> app) {
		ArrayList<Application> application = new ArrayList<Application>();
		for (Application a : app) {
			application.add(a);
		}
		return application;
	}

	public static void main(String[] args) {
		int distanceFCFS = 0;
		int distanceSCAN = 0;
		int distanceCSCAN = 0;
		int distanceSSTF = 0;

		int distanceEDFFCFS = 0;
		int distanceFDSCAN = 0;
		int distanceFDCSCAN = 0;
		int distanceEDFSSTF = 0;

		Application a = new Application(0, 98);
		Application b = new Application(1, 183);
		Application c = new Application(2, 37);
		Application d = new Application(3, 122);
		Application e = new Application(4, 14);
		Application f = new Application(5, 124);
		Application g = new Application(6, 65);
		Application h = new Application(7, 67);

		ArrayList<Application> test = new ArrayList<Application>();
		test.add(a);
		test.add(b);
		test.add(c);
		test.add(d);
		test.add(e);
		test.add(f);
		test.add(g);
		test.add(h);

		ArrayList<Application> array = createApplication();
		array.sort(comparator);
		System.out.println(array);

		System.out
				.println("===============================================================");
		System.out.println("\t\tAlgorytm FCFS");
		System.out
				.println("===============================================================");
		FCFS fcfs = new FCFS();
		FCFS edfFCFS = new FCFS();
		distanceFCFS = fcfs.run(copyArray(array), HEAD_POSITION);
		distanceEDFFCFS = edfFCFS.runEDF(copyArray(array), HEAD_POSITION);
		System.out.println("Odleglosc FCFS: " + distanceFCFS);
		System.out.println("Odleglosc EDF-FCFS: " + distanceEDFFCFS);

		System.out
				.println("===============================================================");
		System.out.println("\t\tAlgorytm SSTF");
		System.out
				.println("===============================================================");

		SSTF sstf = new SSTF();
		SSTF edfSSTF = new SSTF();
		distanceSSTF = sstf.run(copyArray(array), HEAD_POSITION);
		distanceEDFSSTF = edfSSTF.runEDF(copyArray(array), HEAD_POSITION);
		System.out.println("Odleglosc SSTF: " + distanceSSTF);
		System.out.println("Odleglosc EDF-SSTF: " + distanceEDFSSTF);

		System.out
				.println("===============================================================");
		System.out.println("\t\tAlgorytm SCAN");
		System.out
				.println("===============================================================");

		SCAN scan = new SCAN();
		SCAN edfSCAN = new SCAN();
		distanceSCAN = scan.run(copyArray(array), HEAD_POSITION, "Left");
		distanceFDSCAN = edfSCAN.runFD(copyArray(array), HEAD_POSITION);
		System.out.println("Odleglosc SCAN: " + distanceSCAN);
		System.out.println("Odleglosc FD-SCAN: " + distanceFDSCAN);

		System.out
				.println("===============================================================");
		System.out.println("\t\tAlgorytm C-SCAN");
		System.out
				.println("===============================================================");

		CSCAN cscan = new CSCAN();
		CSCAN edfCSCAN = new CSCAN();
		distanceCSCAN = cscan.run(copyArray(array), HEAD_POSITION, "Right");
		distanceFDCSCAN = edfCSCAN.runFD(copyArray(array), HEAD_POSITION);
		System.out.println("Odleglosc CSCAN: " + distanceCSCAN);
		System.out.println("Odleglosc FD-CSCAN: " + distanceFDCSCAN);
	}

}
