package eg.edu.guc.loa.engine;

import java.util.ArrayList;

public class Board implements BoardInterface {
	private Checker[][] checkers = new Checker[8][8];
	private Player white;
	private Player black;

	public Board() {
		white = new Player(1, true);
		black = new Player(2, false);
		setStartConfig();
	}

	// getters
	public Checker[][] getCheckers() {
		return checkers;
	}

	public Player getWhitePlayer() {
		return white;
	}

	public Player getBlackPlayer() {
		return black;
	}

	public Checker getCheckerAt(Point p) {
		return getCheckers()[p.getY()][p.getX()];
	}

	// setters
	public void setCheckers(Checker[][] c) {
		checkers = c;
	}

	public void setWhitePlayer(Player p) {
		white = p;
	}

	public void setBlackPlayer(Player p) {
		black = p;
	}

	// Methods
	public void setStartConfig() {
		for (int i = 0; i < getCheckers().length; i++) {
			for (int j = 0; j < getCheckers()[i].length; j++) {
				if ((i == 0 || i == 7) && j > 0 && j < 7) {
					getCheckers()[i][j] = new Checker(1, new Point(i, j));
					white.addChecker(new Point(i, j));
				}
				if (i != 0 && i != 7 && (j == 0 || j == 7)) {
					getCheckers()[i][j] = new Checker(2, new Point(i, j));
					black.addChecker(new Point(i, j));
				}
			}
		}
	}

	public void changeTurn() {
		if (getWhitePlayer().isTurn()) {
			getWhitePlayer().setTurn(false);
			getBlackPlayer().setTurn(true);
		} else {
			getWhitePlayer().setTurn(true);
			getBlackPlayer().setTurn(false);
		}
	}

	// for number of checkers in row
	public int getNoOfCheckersInRow(int row) {
		int counter = 0;
		for (int i = 0; i < getCheckers()[row].length; i++) {
			if (getCheckers()[row][i] != null) {
				counter++;
			}
		}
		return counter;
	}

	public int getNoOfCheckersInColumn(int column) {
		int counter = 0;
		for (int i = 0; i < getCheckers()[column].length; i++) {
			if (getCheckers()[i][column] != null) {
				counter++;
			}
		}
		return counter;
	}

	// Cal no of checkers in diagonals

	// left Diagonal --> (\)
	public int getNoOfCheckersInLeftDiagonal(Point p) {
		int counter = 0;
		// to get all checkers before this point
		int column = p.getX();
		for (int row = p.getY(); column >= 0 && row >= 0; row--, column--) {
			if (getCheckerAt(new Point(column, row)) != null) {
				counter++;
			}
		}

		// to get all checkers after this point
		column = p.getX();
		for (int row = p.getY(); row < 8 && column < 8; row++, column++) {
			if (getCheckerAt(new Point(column, row)) != null) {
				counter++;
			}
		}
		return counter - 1;
	}

	// right Diagonal --> (/)
	public int getNoOfCheckersInRightDiagonal(Point p) {
		int counter = 0;
		// to get all checkers before this point
		int column = p.getX();
		for (int row = p.getY(); column < 8 && row >= 0; row--, column++) {
			if (getCheckerAt(new Point(column, row)) != null) {
				counter++;
			}
		}
		// to get all checkers after this point
		column = p.getX();
		for (int row = p.getY(); row < 8 && column >= 0; row++, column--) {
			if (getCheckerAt(new Point(column, row)) != null) {
				counter++;
			}
		}
		return counter - 1;
	}

	// check Possibility of moving
	// This returns null if it can not move to the position and if it can move
	// it return the point that checker should move to

	public Point canMoveRight(Point p) {
		if (getNoOfCheckersInRow(p.getY()) + p.getX() < 8) {
			for (int i = 1; i < getNoOfCheckersInRow(p.getY()); i++) {
				if (getCheckers()[p.getY()][p.getX() + i] != null
						&& getCheckers()[p.getY()][p.getX() + i].getColor() != getCheckers()[p
								.getY()][p.getX()].getColor()) {
					return null;
				}
			}
			if (getCheckers()[p.getY()][p.getX()
					+ getNoOfCheckersInRow(p.getY())] != null
					&& getCheckers()[p.getY()][p.getX()
							+ getNoOfCheckersInRow(p.getY())].getColor() == getCheckers()[p
							.getY()][p.getX()].getColor()) {
				return null;
			}
			return new Point(p.getX() + getNoOfCheckersInRow(p.getY()),
					p.getY());
		}
		return null;
	}

	public Point canMoveLeft(Point p) {
		if (p.getX() - getNoOfCheckersInRow(p.getY()) >= 0) {
			for (int i = 1; i < getNoOfCheckersInRow(p.getY()); i++) {
				if (getCheckers()[p.getY()][p.getX() - i] != null
						&& getCheckers()[p.getY()][p.getX() - i].getColor() != getCheckers()[p
								.getY()][p.getX()].getColor()) {
					return null;
				}
			}
			if (getCheckers()[p.getY()][p.getX()
					- getNoOfCheckersInRow(p.getY())] != null) {
				if (getCheckers()[p.getY()][p.getX()
						- getNoOfCheckersInRow(p.getY())].getColor() == getCheckers()[p
						.getY()][p.getX()].getColor()) {
					return null;
				}

			}
			return new Point(p.getX() - getNoOfCheckersInRow(p.getY()),
					p.getY());

		}
		return null;
	}

	public Point canMoveUp(Point p) {
		if (p.getY() - getNoOfCheckersInColumn(p.getX()) >= 0) {
			for (int i = 1; i < getNoOfCheckersInColumn(p.getX()); i++) {
				if (getCheckers()[p.getY() - i][p.getX()] != null
						&& getCheckers()[p.getY() - i][p.getX()].getColor() != getCheckers()[p
								.getY()][p.getX()].getColor()) {
					return null;
				}
			}
			if (getCheckers()[p.getY() - getNoOfCheckersInColumn(p.getX())][p
					.getX()] != null
					&& getCheckers()[p.getY()
							- getNoOfCheckersInColumn(p.getX())][p.getX()]
							.getColor() == getCheckers()[p.getY()][p.getX()]
							.getColor()) {
				return null;
			}
			return new Point(p.getX(), p.getY()
					- getNoOfCheckersInColumn(p.getX()));
		}
		return null;
	}

	public Point canMoveDown(Point p) {
		if (p.getY() + getNoOfCheckersInColumn(p.getX()) < 8) {
			for (int i = 1; i < getNoOfCheckersInColumn(p.getX()); i++) {
				if (getCheckers()[p.getY() + i][p.getX()] != null
						&& getCheckers()[p.getY() + i][p.getX()].getColor() != getCheckers()[p
								.getY()][p.getX()].getColor()) {
					return null;
				}
			}
			if (getCheckers()[p.getY() + getNoOfCheckersInColumn(p.getX())][p
					.getX()] != null
					&& getCheckers()[p.getY()
							+ getNoOfCheckersInColumn(p.getX())][p.getX()]
							.getColor() == getCheckers()[p.getY()][p.getX()]
							.getColor()) {
				return null;
			}
			return new Point(p.getX(), p.getY()
					+ getNoOfCheckersInColumn(p.getX()));
		}
		return null;
	}

	public Point canMoveUpRight(Point p) {
		if (p.getY() - getNoOfCheckersInRightDiagonal(p) >= 0
				&& p.getX() + getNoOfCheckersInRightDiagonal(p) < 8) {
			for (int i = 1; i < getNoOfCheckersInRightDiagonal(p); i++) {
				if (getCheckers()[p.getY() - i][p.getX() + i] != null
						&& getCheckers()[p.getY() - i][p.getX() + i].getColor() != getCheckers()[p
								.getY()][p.getX()].getColor()) {
					return null;
				}
			}
			if (getCheckers()[p.getY() - getNoOfCheckersInRightDiagonal(p)][p
					.getX() + getNoOfCheckersInRightDiagonal(p)] != null
					&& getCheckers()[p.getY()
							- getNoOfCheckersInRightDiagonal(p)][p.getX()
							+ getNoOfCheckersInRightDiagonal(p)].getColor() == getCheckers()[p
							.getY()][p.getX()].getColor()) {
				return null;
			}
			return new Point(p.getX() + getNoOfCheckersInRightDiagonal(p),
					p.getY() - getNoOfCheckersInRightDiagonal(p));
		}
		return null;
	}

	public Point canMoveDownLeft(Point p) {
		if (p.getY() + getNoOfCheckersInRightDiagonal(p) < 8
				&& p.getX() - getNoOfCheckersInRightDiagonal(p) >= 0) {
			for (int i = 1; i < getNoOfCheckersInRightDiagonal(p); i++) {
				if (getCheckers()[p.getY() + i][p.getX() - i] != null
						&& getCheckers()[p.getY() + i][p.getX() - i].getColor() != getCheckers()[p
								.getY()][p.getX()].getColor()) {
					return null;
				}
			}
			if (getCheckers()[p.getY() + getNoOfCheckersInRightDiagonal(p)][p
					.getX() - getNoOfCheckersInRightDiagonal(p)] != null
					&& getCheckers()[p.getY()
							+ getNoOfCheckersInRightDiagonal(p)][p.getX()
							- getNoOfCheckersInRightDiagonal(p)].getColor() == getCheckers()[p
							.getY()][p.getX()].getColor()) {
				return null;
			}
			return new Point(p.getX() - getNoOfCheckersInRightDiagonal(p),
					p.getY() + getNoOfCheckersInRightDiagonal(p));
		}
		return null;
	}

	public Point canMoveUpLeft(Point p) {
		if (p.getY() - getNoOfCheckersInLeftDiagonal(p) >= 0
				&& p.getX() - getNoOfCheckersInLeftDiagonal(p) >= 0) {
			for (int i = 1; i < getNoOfCheckersInLeftDiagonal(p); i++) {
				if (getCheckers()[p.getY() - i][p.getX() - i] != null
						&& getCheckers()[p.getY() - i][p.getX() - i].getColor() != getCheckers()[p
								.getY()][p.getX()].getColor()) {
					return null;
				}
			}
			if (getCheckers()[p.getY() - getNoOfCheckersInLeftDiagonal(p)][p
					.getX() - getNoOfCheckersInLeftDiagonal(p)] != null
					&& getCheckers()[p.getY()
							- getNoOfCheckersInLeftDiagonal(p)][p.getX()
							- getNoOfCheckersInLeftDiagonal(p)].getColor() == getCheckers()[p
							.getY()][p.getX()].getColor()) {
				return null;
			}
			return new Point(p.getX() - getNoOfCheckersInLeftDiagonal(p),
					p.getY() - getNoOfCheckersInLeftDiagonal(p));
		}
		return null;
	}

	public Point canMoveDownRight(Point p) {
		if (p.getY() + getNoOfCheckersInLeftDiagonal(p) < 8
				&& p.getX() + getNoOfCheckersInLeftDiagonal(p) < 8) {
			for (int i = 1; i < getNoOfCheckersInLeftDiagonal(p); i++) {
				if (getCheckers()[p.getY() + i][p.getX() + i] != null
						&& getCheckers()[p.getY() + i][p.getX() + i].getColor() != getCheckers()[p
								.getY()][p.getX()].getColor()) {
					return null;
				}
			}
			if (getCheckers()[p.getY() + getNoOfCheckersInLeftDiagonal(p)][p
					.getX() + getNoOfCheckersInLeftDiagonal(p)] != null
					&& getCheckers()[p.getY()
							+ getNoOfCheckersInLeftDiagonal(p)][p.getX()
							+ getNoOfCheckersInLeftDiagonal(p)].getColor() == getCheckers()[p
							.getY()][p.getX()].getColor()) {
				return null;
			}
			return new Point(p.getX() + getNoOfCheckersInLeftDiagonal(p),
					p.getY() + getNoOfCheckersInLeftDiagonal(p));
		}
		return null;
	}

	// Interface implemented methods
	public boolean move(Point start, Point end) {
		if (getCheckerAt(start) == null) {
			return false;
		}
		if (getTurn() == getCheckerAt(start).getColor()) {
			ArrayList<Point> possibleMoves = getPossibleMoves(start);
			for (int i = 0; i < possibleMoves.size(); i++) {
				if (end.compareTo(possibleMoves.get(i)) == 0) {
					if (getCheckerAt(end) != null) {
						if (getCheckerAt(end).getColor() == white.getColor()) {
							white.removeChecker(end);
						} else {
							black.removeChecker(end);
						}
					}
					if (getCheckerAt(start).getColor() == white.getColor()) {
						for (int j = 0; j < white.getMyCheckers().length; j++) {
							if (white.getMyCheckers()[j].getPosition()
									.compareTo(
											new Point(start.getY(), start
													.getX())) == 0) {
								white.getMyCheckers()[j] = new Checker(1,
										new Point(end.getY(), end.getX()));
							}
						}
					} else {
						for (int j = 0; j < black.getMyCheckers().length; j++) {
							if (black.getMyCheckers()[j].getPosition()
									.compareTo(
											new Point(start.getY(), start
													.getX())) == 0) {
								black.getMyCheckers()[j] = new Checker(2,
										new Point(end.getY(), end.getX()));
							}
						}
					}
					getCheckers()[end.getY()][end.getX()] = getCheckerAt(start);
					getCheckerAt(end).setPosition(
							new Point(end.getY(), end.getX()));
					getCheckers()[start.getY()][start.getX()] = null;
					if (isGameOver()) {
						System.out.println();
					} else {
						changeTurn();
					}
					return true;
				}
			}
		}
		return false;
	}

	public int getColor(Point p) {
		if (getCheckerAt(p) != null) {
			return getCheckerAt(p).getColor();
		}
		return 0;
	}

	public int getTurn() {
		if (getWhitePlayer().isTurn()) {
			return 1;
		}
		return 2;
	}

	public ArrayList<Point> getPossibleMoves(Point start) {
		ArrayList<Point> possibleMoves = new ArrayList<Point>();
		if (getCheckerAt(start) == null) {

			return possibleMoves;
		}

		if (getTurn() == getCheckerAt(start).getColor()) {
			possibleMoves.add(canMoveDown(start));
			possibleMoves.add(canMoveDownLeft(start));
			possibleMoves.add(canMoveDownRight(start));
			possibleMoves.add(canMoveUp(start));
			possibleMoves.add(canMoveUpLeft(start));
			possibleMoves.add(canMoveUpRight(start));
			possibleMoves.add(canMoveRight(start));
			possibleMoves.add(canMoveLeft(start));
			ArrayList<Point> result = new ArrayList<Point>();
			for (int i = 0; i < possibleMoves.size(); i++) {
				if (possibleMoves.get(i) != null) {
					result.add(possibleMoves.get(i));
				}
			}

			return result;
		}

		return possibleMoves;
	}

	// //////////////////////////////////////////
	// ////////////////////////////
	private int getNumOfPiecesForPlayer(int player) {
		int count = 0;
		for (int rowCounter = 0; rowCounter <= checkers.length - 1; rowCounter++) {
			for (int colCounter = 0; colCounter <= checkers.length - 1; colCounter++) {
				if (checkers[rowCounter][colCounter] != null
						&& checkers[rowCounter][colCounter].getColor() == player) {
					count++;
				}
			}
		}
		return count;
	}

	public boolean isGameOver() {
		if (isGameOver(white.getColor()) || isGameOver(black.getColor())) {
			return true;
		}
		return false;
	}

	public boolean isGameOver(int player) {
		Point playerPoint = getPlayerPoint(player);
		boolean[][] checked = new boolean[8][8];
		int connectedPieces = getConnectedPieces(playerPoint.getX(),
				playerPoint.getY(), player, checked);
		if (connectedPieces == getNumOfPiecesForPlayer(player)) {
			return true;
		}

		return false;
	}

	private Point getPlayerPoint(int player) {
		for (int rowCounter = 0; rowCounter <= 8 - 1; rowCounter++) {
			for (int colCounter = 0; colCounter <= 8 - 1; colCounter++) {
				if (checkers[rowCounter][colCounter] != null
						&& checkers[rowCounter][colCounter].getColor() == player) {
					return checkers[rowCounter][colCounter].getPosition();
				}
			}
		}
		return null;
	}

	public int getConnectedPieces(int row, int col, int player,
			boolean[][] checked) {
		// This position row col does not contain a connected piece if
		if (row < 0 || row > 8 - 1 // Outside board boundaries
				|| col < 0 || col > 8 - 1 // Outside board boundaries
				|| checkers[row][col] == null // No Piece at the given position
				|| checkers[row][col].getColor() != player // Piece at postion
															// belongs to
															// opponent
				|| checked[row][col]) { // Position was previously checked
			return 0;
		}
		checked[row][col] = true;
		// Check adjacent Positions (recursive call)
		return 1 + getConnectedPieces(row - 1, col, player, checked)
				+ getConnectedPieces(row - 1, col + 1, player, checked)
				+ getConnectedPieces(row, col + 1, player, checked)
				+ getConnectedPieces(row + 1, col + 1, player, checked)
				+ getConnectedPieces(row + 1, col, player, checked)
				+ getConnectedPieces(row + 1, col - 1, player, checked)
				+ getConnectedPieces(row, col - 1, player, checked)
				+ getConnectedPieces(row - 1, col - 1, player, checked);
	}

	public int getWinner() {
		// Check if current player is winner
		if (isGameOver(getTurn())) {
			return getTurn();
		}
		// Check if opponent is winner.
		int nextTurn = getNextTurn();
		if (isGameOver(nextTurn)) {
			return nextTurn;
		}

		// No winner yet.
		return 0;

	}

	private int getNextTurn() {
		// If turn is 1 returns 2 , if turn is 2 will return 1
		return 3 - getTurn();
	}
}
