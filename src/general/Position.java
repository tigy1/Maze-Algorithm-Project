package general;

public class Position {
	private int x;
	private int y;
	private String val;
	private Position prev;
	
	public Position(int x, int y, String val) {
		this.x = x;
		this.y = y;
		this.val = val;
		prev = null;
	}

	public Position() {
		// TODO Auto-generated constructor stub
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public Position getPrev() {
		return prev;
	}

	public void setPrev(Position prev) {
		this.prev = prev;
	}
}
