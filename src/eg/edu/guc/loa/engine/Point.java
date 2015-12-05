package eg.edu.guc.loa.engine;

public class Point implements Comparable<Point> {

	private int x;
	private int y;

	public Point() {
		x = 0;
		y = 0;
	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void move(int xDelta, int yDelta) {
		x += xDelta;
		y += yDelta;
	}

	public String draw() {
		return "(" + x + ", " + y + ")";
	}

	public int compareTo(Point p) {
		if (this.getX() == p.getX() && this.getY() == p.getY()) {
			return 0;
		}
		return 1;
	}
}
