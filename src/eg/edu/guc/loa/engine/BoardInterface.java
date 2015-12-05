package eg.edu.guc.loa.engine;

import java.util.ArrayList;

public interface BoardInterface {
	boolean move(Point start, Point end);
	boolean isGameOver();
	int getWinner();
	int getColor(Point p);
	int getTurn();
	ArrayList<Point> getPossibleMoves(Point start);
}
