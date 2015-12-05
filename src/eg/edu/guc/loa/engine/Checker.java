package eg.edu.guc.loa.engine;

public class Checker {
	private Point position;
	private int color;

	public Checker() {

	}

	public Checker(int color, Point position) {
		this.position = position;
		this.color = color;
	}

	// getters
	public int getColor() {
		return color;
	}

	public Point getPosition() {
		return position;
	}

	// setters
	public void setPosition(Point p) {
		position = p;
	}

	public void setColor(int c) {
		color = c;
	}

	public String toString() {
		return getColor() + "---"
		/* + "\nIt's Position on Board : " + getPosition().draw() */;
	}
}
