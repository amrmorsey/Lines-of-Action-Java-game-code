package eg.edu.guc.loa.engine;

public class Player {
	private String name;
	private int color;
	private boolean turn;
	private Checker[] myCheckers = new Checker[12];

	public Player(int color, boolean turn) {
		this.color = color;
		this.turn = turn;
	}

	public Player(String name, int color) {
		this.name = name;
		this.color = color;
		if (color == 1) {
			setTurn(true);
		} else {
			setTurn(false);
		}

	}

	// setters
	public void setName(String name) {
		this.name = name;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	public void setMyCheckers(Checker[] myCheckers) {
		this.myCheckers = myCheckers;
	}

	// getters
	public String getName() {
		return name;
	}

	public int getColor() {
		return color;
	}

	public boolean isTurn() {
		return turn;
	}

	public Checker[] getMyCheckers() {
		return myCheckers;
	}

	public void addChecker(Point p) {
		for (int i = 0; i < getMyCheckers().length; i++) {
			if (getMyCheckers()[i] == null) {
				getMyCheckers()[i] = new Checker(this.getColor(), p);
				return;
			}
		}
	}

	public void removeChecker(Point p) {
		for (int i = 0; i < getMyCheckers().length; i++) {
			if (getMyCheckers()[i].getPosition().compareTo(
					new Point(p.getY(), p.getX())) == 0) {
				getMyCheckers()[i] = null;
			}
		}
		int noOfCheckers = 0;
		for (int i = 0; i < getMyCheckers().length; i++) {
			if (getMyCheckers()[i] != null) {
				noOfCheckers++;
			}
		}

		Checker[] tem = new Checker[noOfCheckers];
		int count = 0;
		for (int i = 0; i < getMyCheckers().length; i++) {
			if (getMyCheckers()[i] != null) {
				tem[count] = getMyCheckers()[i];
				count++;
			}
		}

		setMyCheckers(tem);
	}

	public String eatenCheckers() {
		int x = getMyCheckers().length;
		String tem = "12";
		switch (x) {
		case 0:
			tem = " 0";
			break;
		case 1:
			tem = " 1";
			break;
		case 2:
			tem = " 2";
			break;
		case 3:
			tem = " 3";
			break;
		case 4:
			tem = " 4";
			break;
		case 5:
			tem = " 5";
			break;
		case 6:
			tem = " 6";
			break;
		case 7:
			tem = " 7";
			break;
		case 8:
			tem = " 8";
			break;
		case 9:
			tem = " 9";
			break;
		case 10:
			tem = "10";
			break;
		case 11:
			tem = "11";
			break;
		case 12:
			tem = "12";
			break;
		default:
			break;
		}
		return tem;
	}
}
