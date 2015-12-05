import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


import eg.edu.guc.loa.engine.Board;
import eg.edu.guc.loa.engine.Point;


public class PublicTest {
	Board board;
	
	@Before
	public void before(){
		board=new Board();
	}
	
	@Test(timeout = 5000)
	public void testInitialSetting() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if((i==0 || i==7) && (j>0 && j<7))
					assertEquals("The cell ("+i+","+j+") should belong to the white player", 1, board.getColor(new Point(i, j)));
				else if((j==0 || j==7) && (i>0 && i<7))
					assertEquals("The cell ("+i+","+j+") should belong to the black player", 2, board.getColor(new Point(i, j)));
				else
					assertEquals("The cell ("+i+","+j+") should be empty", 0, board.getColor(new Point(i, j)));
			}
		}
	}

	@Test(timeout=1000)
	public void testMoveToEmptyPlaceNorth() {
		board.move(new Point(7, 4), new Point(5, 4));
		assertTrue("The cell (7,4) should be able to move in north direction", board.getColor(new Point(7, 4))==0 && board.getColor(new Point(5, 4))==1);
	}
	
	@Test(timeout=1000)
	public void testMoveToEmptyPlaceNorthEast() {
		board.move(new Point(7, 1), new Point(5, 3));
		assertTrue("The cell (7,1) should be able to move in north-east direction", board.getColor(new Point(7, 1))==0 && board.getColor(new Point(5, 3))==1);
	}
	
	@Test(timeout=1000)
	public void testMoveToEmptyPlaceEast() {
		board.move(new Point(0, 1), new Point(0, 7));
		assertTrue("The cell (0,1) should be able to move in east direction", board.getColor(new Point(0, 1))==0 && board.getColor(new Point(0, 7))==1);
	}
	
	@Test(timeout=1000)
	public void testMoveToEmptyPlaceSouthEast() {
		board.move(new Point(0, 2), new Point(2, 4));
		assertTrue("The cell (0,2) should be able to move in south-east direction", board.getColor(new Point(0, 2))==0 && board.getColor(new Point(2, 4))==1);
	}
	
	@Test(timeout=1000)
	public void testMoveToEmptyPlaceSouth() {
		board.move(new Point(0, 1), new Point(2, 1));
		assertTrue("The cell (0,1) should be able to move in south direction", board.getColor(new Point(0, 1))==0 && board.getColor(new Point(2, 1))==1);
	}
	
	@Test(timeout=1000)
	public void testMoveToEmptyPlaceSouthWest() {
		board.move(new Point(0, 6), new Point(2, 4));
		assertTrue("The cell (0,6) should be able to move in south-east direction", board.getColor(new Point(0, 6))==0 && board.getColor(new Point(2, 4))==1);
	}
	
	@Test(timeout=1000)
	public void testMoveToEmptyPlaceWest() {
		board.move(new Point(0, 6), new Point(0, 0));
		assertTrue("The cell (0,6) should be able to move in west direction", board.getColor(new Point(0, 6))==0 && board.getColor(new Point(0, 0))==1);
	}
	
	@Test(timeout=1000)
	public void testMoveToEmptyPlaceNorthWest() {
		board.move(new Point(7, 6), new Point(5, 4));
		assertTrue("The cell (7,6) should be able to move in north-west direction", board.getColor(new Point(7, 6))==0 && board.getColor(new Point(5, 4))==1);
	}
	
	@Test(timeout=1000)
	public void testLandOnOwnPiece() {
		board.move(new Point(0,3), new Point(2,3)); //White's turn
		board.move(new Point(3,0), new Point(3,2)); //Black's turn
		board.move(new Point(0,1), new Point(3,4)); //White's turn
		board.move(new Point(1,0), new Point(1,2)); //Black's turn
		assertFalse("Should not allow landing on the player's own piece", board.move(new Point(0, 4), new Point(3, 4)));
	}
	
	@Test(timeout=1000)
	public void testLandOnOpponentPiece() {
		board.move(new Point(0, 2), new Point(2, 0));
		assertTrue("The white piece in cell (0,2) should land on the black piece in cell (2,0)", board.getColor(new Point(0, 2))==0 && board.getColor(new Point(2, 0))==1);
	}
	
	@Test(timeout=1000)
	public void testMoveFromEmptyPlace() {
		assertFalse("The cell (1,4) is empty", board.move(new Point(1, 4), new Point(3, 4)));
	}

	@Test(timeout=1000)
	public void testInvalidMove1() {
		assertFalse("Should not allow movement not along the 8 lines of action", board.move(new Point(0, 1), new Point(2, 5)));
	}
	
	@Test(timeout=1000)
	public void testInvalidMove2() {
		assertFalse("Should not jump a number of cells less than the number of pieces in this line of action", board.move(new Point(0, 6), new Point(1, 6)));
	}
	
	@Test(timeout=1000)
	public void testInvalidMove3() {
		assertFalse("Should not jump a number of cells more than the number of pieces in this line of action", board.move(new Point(0, 6), new Point(3, 6)));
	}
	
	@Test(timeout=1000)
	public void testNotAllowingMoveWhenNotInYourTurn() {
		board.move(new Point(0, 2), new Point(2, 2)); // White's turn
		assertFalse(
				"Should not allow moving a white piece when it is the turn of the black player",
				board.move(new Point(7, 5), new Point(5, 5))); // Black's turn
	}
	
	@Test(timeout=1000)
	public void testChangingTurnAfterCorrectMove() {
		board.move(new Point(7, 3), new Point(5, 3)); // White's turn
		assertTrue(board.move(new Point(3, 0), new Point(3, 2))); // Black's turn
		assertTrue(board.move(new Point(0, 6), new Point(2, 6))); // White's turn
	}
	
	@Test(timeout=1000)
	public void testKeepingTurnAfterWrongMove() {
		board.move(new Point(0, 4), new Point(2, 5)); // White's turn
		assertTrue(board.move(new Point(0, 4), new Point(2, 4))); // White's turn
	}
	
	
	@Test(timeout=1000)
	public void testJumpOverOpponent(){
		board.move(new Point(0, 2), new Point(2, 2)); //White's turn
		assertTrue(board.move(new Point(3, 0), new Point(3, 2))); //Black's turn
		assertFalse("Should not allow jumping over opponent's pieces", board.move(new Point(2, 2), new Point(5, 2)));
	}

	@Test(timeout=1000)
	public void testGameOverInitial() {
		assertFalse(board.isGameOver());
	}
	
	@Test(timeout=1000)
	
	public void testGameOverAfterSomeMoves() {
		board.move(new Point(0, 1), new Point(2, 3)); // White's turn
		board.move(new Point(5, 0), new Point(5, 2)); // Black's turn
		assertFalse(board.isGameOver());
	}
	
	@Test(timeout=1000)
	public void testGameOver() {
		play();
		assertTrue(board.isGameOver());
	}

	@Test(timeout=1000)
	public void testGetWinner() {
		play();
		assertEquals("White player wins", 1, board.getWinner()); // White (Player 1) wins
	}
	
	@Test(timeout=1000)
	public void testGetPossibleMoves(){
		ArrayList<Point> points= board.getPossibleMoves(new Point(5, 0)); //White's turn
		assertEquals("A Black player has no available moves", 0, points.size());
		
		points= board.getPossibleMoves(new Point(0, 4));
		assertEquals("There should be 3 available moves", 3, points.size()); //White's turn
		assertTrue("The cell (2,4) is an available move", checkContainsPoint(points, new Point(2, 4)));
		assertTrue("The cell (2,6) is an available move", checkContainsPoint(points, new Point(2, 6)));
		assertTrue("The cell (2,2) is an available move", checkContainsPoint(points, new Point(2, 2)));
		
		points= board.getPossibleMoves(new Point(7, 4));
		assertEquals("There should be 3 available moves", 3, points.size()); //White's turn
		assertTrue("The cell (5,4) is an available move", checkContainsPoint(points, new Point(5, 4)));
		assertTrue("The cell (5,6) is an available move", checkContainsPoint(points, new Point(5, 6)));
		assertTrue("The cell (5,2) is an available move", checkContainsPoint(points, new Point(5, 2)));
		
	}
	public void play() {
		board.move(new Point(0,3), new Point(2,3)); // White's turn
		board.move(new Point(3,0), new Point(3,2)); // Black's turn
		board.move(new Point(0,1), new Point(3,4)); // White's turn
		board.move(new Point(2,7), new Point(0,5)); // Black's turn
		board.move(new Point(0,2), new Point(3,2)); // White's turn
		board.move(new Point(1,7), new Point(1,5)); // Black's turn
		board.move(new Point(7,5), new Point(4,5)); // White's turn
		board.move(new Point(4,0), new Point(4,3)); // Black's turn
		board.move(new Point(0,4), new Point(1,3)); // White's turn
		board.move(new Point(5,0), new Point(7,2)); // Black's turn
		board.move(new Point(7,4), new Point(5,4)); // White's turn
		board.move(new Point(0,5), new Point(0,3)); // Black's turn
		board.move(new Point(0,6), new Point(0,4)); // White's turn
		board.move(new Point(2,0), new Point(2,2)); // Black's turn
		board.move(new Point(7,3), new Point(5,5)); // White's turn
		board.move(new Point(0,3), new Point(0,1)); // Black's turn
		board.move(new Point(7,1), new Point(5,1)); // White's turn
		board.move(new Point(1,0), new Point(3,0)); // Black's turn
		board.move(new Point(7,6), new Point(6,6)); // White's turn
		board.move(new Point(3,0), new Point(2,1)); // Black's turn
		board.move(new Point(5,1), new Point(4,0)); // White's turn
		board.move(new Point(6,0), new Point(4,0)); // Black's turn
	}
	
	public static boolean checkContainsPoint(ArrayList<Point> points, Point point){
		for(Point p: points){
			if(p.getX()==point.getX() && p.getY()==point.getY())
				return true;
		}
		return false;
	}
}
