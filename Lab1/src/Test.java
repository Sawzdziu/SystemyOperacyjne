import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * 
 * @author sawzdziu
 *
 *         Klasa testująca algorytmy
 */

public class Test {

	static int iloscCiagowTestowych = 1;
	static int iloscWatkowWCiagu = 10;
	static int minDlugoscWatku = 10;
	static int maxDlugoscWatku = 20;
	static int maxOpoznienieWatku = 10;
	static int kwantCzasu = 5;

	public static void main(String[] args) {

		double czasFCFS = 0;
		double czasSJF = 0;
		double czasSJFw = 0;
		double czasRR = 0;

		Proces e1 = new Proces(1, 1, 5);
		Proces e2 = new Proces(2, 5, 12);
		Proces e3 = new Proces(3, 7, 15);
		Proces e4 = new Proces(4, 5, 10);
		ArrayList<Proces> proc = new ArrayList<Proces>();
		proc.add(e1);
		proc.add(e2);
		proc.add(e3);
		proc.add(e4);

		for (int i = 0; i < iloscCiagowTestowych; i++) {
			ArrayList<Proces> list = generuj();
			//ArrayList<Proces> list = wczytajPlik("linijka.txt");
			FCSF fcfs = new FCSF(kopiujTablice(list));
			czasFCFS += fcfs.go();
			SJF sjf = new SJF(kopiujTablice(list));
			czasSJF += sjf.go();
			SJFW sjfw = new SJFW(kopiujTablice(list));
			czasSJFw += sjfw.go();
			RR rr = new RR();
			czasRR += rr.go(kopiujTablice(list));
			// System.out.println("Koniec testu: " + i);
		}
		System.out.println("Czas FCFS: " + (czasFCFS / 1));
		System.out.println("Czas SJF: " + (czasSJF / 1));
		System.out.println("Czas SJFw: " + (czasSJFw / 1));
		System.out.println("Czas RR: " + (czasRR / 1));
	}

	public static ArrayList<Proces> generuj() {
		ArrayList<Proces> procesy = new ArrayList<Proces>();
		Random rnd = new Random();
		int mean = 50;
		int stder = 15;
		procesy.add(new Proces(0, 0, 43));
		for (int i = 1; i < iloscWatkowWCiagu; i++) {
			int PID = i;
			int czasZgl = Math.abs(mean + (int) (stder * rnd.nextGaussian()));
			int dlugoscP = Math.abs(mean + (int) (stder * rnd.nextGaussian())) + 1;
			// Proces p = new Proces(PID, czasZgl, dlugoscP);
			procesy.add(new Proces(PID, czasZgl, dlugoscP));
			// System.out.println(p.toString());
		}
		return procesy;
	}

	private static ArrayList<Proces> kopiujTablice(ArrayList<Proces> proc) {
		ArrayList<Proces> wyn = new ArrayList<Proces>();
		for (Proces pro : proc) {
			wyn.add(pro);
		}
		return wyn;
	}

	public static ArrayList<Proces> wczytajPlik(String nazwa) {
		File plik = new File(nazwa);
		ArrayList<Proces> kolejka = new ArrayList<Proces>();
		try {
			BufferedReader odczyt = new BufferedReader(new FileReader(plik));
			String s = odczyt.readLine();
			StringTokenizer tok = new StringTokenizer(s, ",;", true);
			while (tok.hasMoreTokens()) {
				int pid = Integer.parseInt(tok.nextToken());
				tok.nextToken();
				int ready = Integer.parseInt(tok.nextToken());
				tok.nextToken();
				int time = Integer.parseInt(tok.nextToken());
				tok.nextToken();
				kolejka.add(new Proces(pid, ready, time));

			}
		} catch (IOException e) {
			System.out.println("Błąd IO");
		}
		return kolejka;
	}

}
