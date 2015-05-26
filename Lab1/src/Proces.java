public class Proces {

	int PID;
	int comeTime;
	int waitingTime;
	int phaseLenght;
	int remainingTime;

	public Proces(int PID, int momentZgloszenia, int dlugoscFazy) {
		this.PID = PID;
		this.comeTime = momentZgloszenia;
		this.phaseLenght = dlugoscFazy;
		this.waitingTime = 0;
		setRemainingTime(dlugoscFazy);
	}

	public String toString() {
		return "PID: " + PID + " Moment zgloszenia: " + comeTime
				+ " Dlugosc pracy: " + phaseLenght
				+ " Czas pozostały: " + remainingTime + " Czas oczekiwania: "
				+ waitingTime;
	}

	public void setRemainingTime(int czaspozostaly) {
		this.remainingTime = czaspozostaly;
	}

	public int getPID() {
		return PID;
	}

	public void setPID(int pID) {
		PID = pID;
	}

	public double getMomentZgloszenia() {
		return comeTime;
	}

	public void setMomentZgloszenia(int momentZgloszenia) {
		this.comeTime = momentZgloszenia;
	}

	public double getCzasOczekiwania() {
		return waitingTime;
	}

	public void setCzasOczekiwania(int czasoczekiwania) {
		this.waitingTime = czasoczekiwania;
	}

	public double getDlugoscFazyProcesora() {
		return phaseLenght;
	}

	public void setDlugoscFazyProcesora(int dlugoscFazyProcesora) {
		this.phaseLenght = dlugoscFazyProcesora;
	}
	
	public int getRemainingTime(){
		return remainingTime;
	}

}
