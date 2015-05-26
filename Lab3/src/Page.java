
public class Page {

	private int time;
	private int bit;
	
	public Page(){
		time = -1;
		bit = 0;
	}
	
	public Page(Page page){
		this.time = page.time;
		this.bit = page.bit;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getBit() {
		return bit;
	}

	public void setBit(int bit) {
		this.bit = bit;
	}
	
}
