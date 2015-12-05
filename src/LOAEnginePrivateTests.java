import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import eg.edu.guc.loa.engine.Board;
import eg.edu.guc.loa.engine.Point;


public class LOAEnginePrivateTests{
	Board board;
	
	@Before
	public void before(){
		board=new Board();
	}
	
	@Test(timeout=1000)
	public void testMoveToEmptyPlaceNorth() {
		board.move(new Point(2, 7), new Point(2, 5));
		assertTrue("The cell [7][2] should be able to move in north direction", board.getColor(new Point(2, 7))==0 && board.getColor(new Point(2, 5))==1);
	}
	
	@Test(timeout=1000)
	public void testMoveToEmptyPlaceNorthEast() {
		board.move(new Point(3, 7), new Point(5, 5));
		assertTrue("The cell [7][3] should be able to move in north-east direction", board.getColor(new Point(3, 7))==0 && board.getColor(new Point(5, 5))==1);
	}
	
	@Test(timeout=1000)
	public void testMoveToEmptyPlaceEast() {
		board.move(new Point(1, 7), new Point(7, 7));
		assertTrue("The cell [7][1] should be able to move in east direction", board.getColor(new Point(1, 7))==0 && board.getColor(new Point(7, 7))==1);
	}
	
	@Test(timeout=1000)
	public void testMoveToEmptyPlaceSouthEast() {
		board.move(new Point(4, 0), new Point(6, 2));
		assertTrue("The cell [0][4] should be able to move in south-east direction", board.getColor(new Point(4, 0))==0 && board.getColor(new Point(6, 2))==1);
	}
	
	@Test(timeout=1000)
	public void testMoveToEmptyPlaceSouth() {
		board.move(new Point(2, 0), new Point(2, 2));
		assertTrue("The cell [0][2] should be able to move in south direction", board.getColor(new Point(2, 0))==0 && board.getColor(new Point(2, 2))==1);
	}
	
	@Test(timeout=1000)
	public void testMoveToEmptyPlaceSouthWest() {
		board.move(new Point(5, 0), new Point(3, 2));
		assertTrue("The cell [0][5] should be able to move in south-east direction", board.getColor(new Point(5, 0))==0 && board.getColor(new Point(3, 2))==1);
	}
	
	@Test(timeout=1000)
	public void testMoveToEmptyPlaceWest() {
		board.move(new Point(6, 7), new Point(0, 7));
		assertTrue("The cell [7][6] should be able to move in west direction", board.getColor(new Point(6, 7))==0 && board.getColor(new Point(0, 7))==1);
	}
	
	@Test(timeout=1000)
	public void testMoveToEmptyPlaceNorthWest() {
		board.move(new Point(3, 7), new Point(1, 5));
		assertTrue("The cell [7][3] should be able to move in north-west direction", board.getColor(new Point(3, 7))==0 && board.getColor(new Point(1, 5))==1);
	}
	
	@Test(timeout=1000)
	public void testLandOnOwnPiece() {
		board.move(new Point(6,0), new Point(6,2)); //White's turn
		board.move(new Point(0,6), new Point(2,6)); //Black's turn
		assertFalse("Should not allow landing on the player's own piece", board.move(new Point(4,0), new Point(6,2)));
	}
	
	@Test(timeout=1000)
	public void testLandOnOpponentPiece() {
		board.move(new Point(5, 0), new Point(7, 2));
		assertTrue("The white piece in cell [0][5] should land on the black piece in cell [2][7]", board.getColor(new Point(5, 0))==0 && board.getColor(new Point(7, 2))==1);
	}
	
	@Test(timeout=1000)
	public void testMoveFromEmptyPlace() {
		assertFalse("The cell [1][2] is empty", board.move(new Point(2, 1), new Point(4, 3)));
	}

	@Test(timeout=1000)
	public void testInvalidMove1() {
		assertFalse("Should not allow movement not along the 8 lines of action", board.move(new Point(4, 0), new Point(5, 2)));
	}
	
	@Test(timeout=1000)
	public void testInvalidMove2() {
		assertFalse("Should not jump a number of cells less than the number of pieces in this line of action", board.move(new Point(4, 0), new Point(4, 1)));
		assertFalse("Should not jump a number of cells less than the number of pieces in this line of action", board.move(new Point(4, 0), new Point(5, 1)));
		assertFalse("Should not jump a number of cells less than the number of pieces in this line of action", board.move(new Point(4, 0), new Point(3, 1)));
		assertFalse("Should not jump a number of cells less than the number of pieces in this line of action", board.move(new Point(0, 2), new Point(1, 2)));
	}
	
	@Test(timeout=1000)
	public void testInvalidMove3() {
		assertFalse("Should not jump a number of cells more than the number of pieces in this line of action", board.move(new Point(4, 7), new Point(4, 4)));
		assertFalse("Should not jump a number of cells more than the number of pieces in this line of action", board.move(new Point(3, 7), new Point(6, 4)));
		assertFalse("Should not jump a number of cells more than the number of pieces in this line of action", board.move(new Point(4, 7), new Point(1, 4)));
		assertFalse("Should not jump a number of cells more than the number of pieces in this line of action", board.move(new Point(7, 2), new Point(4, 2)));
	}
	
	@Test(timeout=1000)
	public void testNotAllowingMoveWhenNotInYourTurn() {
		board.move(new Point(0, 4), new Point(2, 4)); // White's turn
		board.move(new Point(4, 0), new Point(4, 2)); // White's turn
		assertFalse(
				"Should not allow moving a white piece when it is the turn of the black player",
				board.move(new Point(5, 0), new Point(5, 2))); // Black's turn
	}
	
	@Test(timeout=1000)
	public void testChangingTurnAfterCorrectMove() {
		board.move(new Point(1, 0), new Point(1, 2)); // White's turn
		assertTrue(board.move(new Point(0, 5), new Point(2, 5))); // Black's turn
		assertTrue(board.move(new Point(3, 0), new Point(3, 2))); // White's turn
	}
	
	@Test(timeout=1000)
	public void testKeepingTurnAfterWrongMove() {
		board.move(new Point(1, 0), new Point(1, 3)); // White's turn
		assertTrue(board.move(new Point(4, 7), new Point(4, 5))); // White's turn
	}
	
	@Test(timeout=1000)
	public void testJumpOverMyPieces(){
		assertTrue(board.move(new Point(1, 0), new Point(7, 0))); //White's turn
	}
	
	@Test(timeout=1000)
	public void testJumpOverOpponent(){
		board.move(new Point(1, 0), new Point(1, 2)); //White's turn
		board.move(new Point(7, 6), new Point(5, 6)); //Black's turn
		assertFalse("Should not allow jumping over opponent's pieces", board.move(new Point(5, 7), new Point(5, 4)));
	}

	@Test(timeout=1000)
	public void testGetPossibleMoves(){
		ArrayList<Point> points= board.getPossibleMoves(new Point(0, 5)); //White's turn
		assertEquals("A Black player has no available moves", 0, points.size());
		
		points= board.getPossibleMoves(new Point(4, 0));
		assertEquals("There should be 3 available moves from the cell [4][0]", 3, points.size()); //White's turn
		assertTrue("The cell [2][4] is an available move", checkContainsPoint(points, new Point(4, 2)));
		assertTrue("The cell [2][6] is an available move", checkContainsPoint(points, new Point(6, 2)));
		assertTrue("The cell [2][2] is an available move", checkContainsPoint(points, new Point(2, 2)));
		
		points= board.getPossibleMoves(new Point(4, 7));
		assertEquals("There should be 3 available moves from the cell [7][4]", 3, points.size()); //White's turn
		assertTrue("The cell [5][4] is an available move", checkContainsPoint(points, new Point(4, 5)));
		assertTrue("The cell [5][6] is an available move", checkContainsPoint(points, new Point(6, 5)));
		assertTrue("The cell [5][2] is an available move", checkContainsPoint(points, new Point(2, 5)));
		
	}
	
	@Test(timeout=1000)
	public void testGetPossibleMovesOpponentOnEastPath(){
		ArrayList<Point> points;
		
		// Moves
		board.move(new Point(3,7),new Point(3,5)); //White's turn
		board.move(new Point(7,5),new Point(4,5)); //Black's turn
		points = board.getPossibleMoves(new Point(3,5));
		// Check Possible Moves Opponent on on Right Path
		assertEquals("There should be 5 available moves from the cell [5][3]", 5, points.size()); //White's turn
		assertTrue("The cell [3][3] is an available move", checkContainsPoint(points, new Point(3,3)));
		assertTrue("The cell [7][3] is an available move", checkContainsPoint(points, new Point(3,7)));
		assertTrue("The cell [5][0] is an available move", checkContainsPoint(points, new Point(0,5)));
		assertTrue("The cell [2][6] is an available move", checkContainsPoint(points, new Point(6,2)));
		assertTrue("The cell [2][0] is an available move", checkContainsPoint(points, new Point(0,2)));
	}
	
	@Test(timeout=1000)
	public void testGetPossibleMovesOpponentOnWestPath(){
		ArrayList<Point> points;
		board.move(new Point(4,7),new Point(4,5)); //White's turn
		board.move(new Point(0,5),new Point(3,5)); //Black's turn
		points = board.getPossibleMoves(new Point(4,5));
		assertEquals("There should be 5 available moves from the cell [4][5]", 5, points.size()); //White's turn
		assertTrue("The cell [3][4] is an available move", checkContainsPoint(points, new Point(4,3)));
		assertTrue("The cell [7][4] is an available move", checkContainsPoint(points, new Point(4,7)));
		assertTrue("The cell [5][7] is an available move", checkContainsPoint(points, new Point(7,5)));
		assertTrue("The cell [2][7] is an available move", checkContainsPoint(points, new Point(7,2)));
		assertTrue("The cell [2][1] is an available move", checkContainsPoint(points, new Point(1,2)));


	}
	
	@Test(timeout=1000)
	public void testGetPossibleMovesOpponentOnSouthPath(){
		ArrayList<Point> points;
		board.move(new Point(2,7),new Point(2,5)); //White's turn
		board.move(new Point(0,3),new Point(2,3)); //Black's turn
		
		points = board.getPossibleMoves(new Point(2,5));
		assertEquals("There should be 4 available moves from the cell [5][2",4,points.size()); // Player1's turn
		assertTrue("The cell [5][5] is an available move", checkContainsPoint(points, new Point(5,5)));
		assertTrue("The cell [6][1] is an available move", checkContainsPoint(points, new Point(1,6)));
		assertTrue("The cell [4][3] is an available move", checkContainsPoint(points, new Point(3,4)));
		assertTrue("The cell [3][0] is an available move", checkContainsPoint(points, new Point(0,3)));

	}
	
	@Test(timeout=1000)
	public void testGetPossibleMovesOpponentOnNorthPath(){
		ArrayList<Point> points;
		board.move(new Point(5,7),new Point(5,5)); //White's turn
		board.move(new Point(7,3),new Point(5,3)); //Black's turn
		points = board.getPossibleMoves(new Point(5,5));
		assertEquals("There should be 4 available moves from the cell [5][5",4,points.size()); // Player1's turn
		assertTrue("The cell [5][2] is an available move", checkContainsPoint(points, new Point(2,5)));
		assertTrue("The cell [6][6] is an available move", checkContainsPoint(points, new Point(6,6)));
		assertTrue("The cell [3][7] is an available move", checkContainsPoint(points, new Point(7,3)));
		assertTrue("The cell [4][4] is an available move", checkContainsPoint(points, new Point(4,4)));

	}
	
	@Test(timeout=1000)
	public void testGetPossibleMovesOpponentOnSouthEastPath(){
		ArrayList<Point> points;
		
		board.move(new Point(2,0),new Point(0,2)); //White's turn
		board.move(new Point(0,4),new Point(2,4)); //Black's turn
		points = board.getPossibleMoves(new Point(0,2));
		assertEquals("There should be 2 available moves from the cell [2][0",2,points.size()); // Player1's turn
		assertTrue("The cell [2][2] is an available move", checkContainsPoint(points, new Point(2,2)));
		assertTrue("The cell [1][1] is an available move", checkContainsPoint(points, new Point(1,1)));

		
	}
	
	@Test(timeout=1000)
	public void testGetPossibleMovesOpponentOnSouthWestPath(){
		ArrayList<Point> points;
		board.move(new Point(4,0),new Point(4,2)); //White's turn
		board.move(new Point(0,4),new Point(2,4)); //Black's turn
		points = board.getPossibleMoves(new Point(4,2));
		assertEquals("There should be 5 available moves from the cell [2][4",5,points.size()); // Player1's turn
		assertTrue("The cell [0][4] is an available move", checkContainsPoint(points, new Point(4,0)));
		assertTrue("The cell [4][4] is an available move", checkContainsPoint(points, new Point(4,4)));
		assertTrue("The cell [2][7] is an available move", checkContainsPoint(points, new Point(7,2)));
		assertTrue("The cell [2][1] is an available move", checkContainsPoint(points, new Point(1,2)));
		assertTrue("The cell [5][7] is an available move", checkContainsPoint(points, new Point(7,5)));

		
	}
	
	@Test(timeout=1000)
	public void testGetPossibleMovesOpponentOnNorthEastPath(){
		ArrayList<Point> points;
		board.move(new Point(5,7),new Point(3,5)); //White's turn
		board.move(new Point(7,3),new Point(5,3)); //Black's turn
		points = board.getPossibleMoves(new Point(3,5));
		assertEquals("There should be 5 available moves from the cell [5][3",5,points.size()); // Player1's turn
		assertTrue("The cell [2][3] is an available move", checkContainsPoint(points, new Point(3,2)));
		assertTrue("The cell [5][6] is an available move", checkContainsPoint(points, new Point(6,5)));
		assertTrue("The cell [5][0] is an available move", checkContainsPoint(points, new Point(0,5)));
		assertTrue("The cell [7][5] is an available move", checkContainsPoint(points, new Point(5,7)));
		assertTrue("The cell [3][1] is an available move", checkContainsPoint(points, new Point(1,3)));

		
	}
	@Test(timeout=1000)
	public void testGetPossibleMovesOpponentOnNorthWestPath(){
		ArrayList<Point> points;
		board.move(new Point(4,7),new Point(4,5)); //White's turn
		board.move(new Point(0,3),new Point(2,3)); //Black's turn
		points = board.getPossibleMoves(new Point(4,5));
		assertEquals("There should be 5 available moves from the cell [5][4",5,points.size()); // Player1's turn
		assertTrue("The cell [3][4] is an available move", checkContainsPoint(points, new Point(4,3)));
		assertTrue("The cell [7][4] is an available move", checkContainsPoint(points, new Point(4,7)));
		assertTrue("The cell [5][7] is an available move", checkContainsPoint(points, new Point(7,5)));
		assertTrue("The cell [5][1] is an available move", checkContainsPoint(points, new Point(1,5)));
		assertTrue("The cell [2][7] is an available move", checkContainsPoint(points, new Point(7,2)));

	}
	
	

	
	@Test(timeout=1000)
	public void testGameOverInitial() {
		assertFalse(board.isGameOver());
	}
	
	@Test(timeout=1000)
	
	public void testGameOverAfterSomeMoves() {
		board.move(new Point(1, 0), new Point(3, 2)); // White's turn
		board.move(new Point(0, 5), new Point(2, 5)); // Black's turn
		assertFalse(board.isGameOver());
	}
	
	@Test(timeout=1000)
	public void testGameOverWhiteConnected() {
		play();
		assertTrue(board.move(new Point(6,0), new Point(7,1))); // White's turn
		assertTrue(board.move(new Point(5,1), new Point(3,1))); // Black's turn
		assertTrue(board.move(new Point(2,4), new Point(3,5))); // White's turn
		assertTrue("The white player connects the pieces",board.isGameOver());
	}

	@Test(timeout=1000)
	public void testGetWinnerWhiteConnected() {
		play();
		assertTrue(board.move(new Point(6,0), new Point(7,1))); // White's turn
		assertTrue(board.move(new Point(5,1), new Point(3,1))); // Black's turn
		assertTrue(board.move(new Point(2,4), new Point(3,5))); // White's turn
		assertEquals("White player wins", 1, board.getWinner()); // White (Player 1) wins
	}
	
	@Test(timeout=1000)
	public void testGameOverBlackOnePiece() {
		play();
		assertTrue(board.move(new Point(2,7), new Point(0,5))); // White's turn
		assertTrue(board.move(new Point(3,0), new Point(2,1))); // Black's turn
		assertTrue(board.move(new Point(2,4), new Point(2,1))); // White's turn
		assertTrue("The white player eats up all the black pieces", board.isGameOver());
	}

	@Test(timeout=1000)
	public void testGetWinnerBlackOnePiece() {
		play();
		assertTrue(board.move(new Point(2,7), new Point(0,5))); // White's turn
		assertTrue(board.move(new Point(3,0), new Point(2,1))); // Black's turn
		assertTrue(board.move(new Point(2,4), new Point(2,1))); // White's turn
		assertEquals("Black player wins", 2, board.getWinner()); // Black (Player 2) wins
	}
	
	
	
	public void play() {
		assertTrue(board.move(new Point(5,0), new Point(7,2))); // White's turn
		assertTrue(board.move(new Point(0,3), new Point(2,3))); // Black's turn
		assertTrue(board.move(new Point(2,0), new Point(2,3))); // White's turn
		assertTrue(board.move(new Point(7,5), new Point(5,5))); // Black's turn
		assertTrue(board.move(new Point(5,7), new Point(5,5))); // White's turn
		assertTrue(board.move(new Point(0,4), new Point(2,2))); // Black's turn
		assertTrue(board.move(new Point(4,0), new Point(2,2))); // White's turn
		assertTrue(board.move(new Point(7,4), new Point(6,4))); // Black's turn
		assertTrue(board.move(new Point(6,7), new Point(6,4))); // White's turn
		assertTrue(board.move(new Point(7,6), new Point(5,6))); // Black's turn
		assertTrue(board.move(new Point(2,3), new Point(5,6))); // White's turn
		assertTrue(board.move(new Point(7,1), new Point(5,1))); // Black's turn
		assertTrue(board.move(new Point(3,0), new Point(4,1))); // White's turn
		assertTrue(board.move(new Point(7,3), new Point(6,3))); // Black's turn
		assertTrue(board.move(new Point(4,1), new Point(6,3))); // White's turn
		assertTrue(board.move(new Point(0,2), new Point(1,3))); // Black's turn
		assertTrue(board.move(new Point(1,0), new Point(1,3))); // White's turn
		assertTrue(board.move(new Point(0,6), new Point(2,6))); // Black's turn
		assertTrue(board.move(new Point(1,3), new Point(2,4))); // White's turn
		assertTrue(board.move(new Point(0,1), new Point(1,0))); // Black's turn
		assertTrue(board.move(new Point(2,2), new Point(2,6))); // White's turn
		assertTrue(board.move(new Point(1,0), new Point(3,0))); // Black's turn
	}
	
	@Test(timeout=1000)
	public void testGameOverStupidWhite() {
		stupidWhite();
		assertTrue("The white player eats a black piece that makes the black player connected", board.isGameOver());
	}

	@Test(timeout=1000)
	public void testGetWinnerStupidWhite() {
		stupidWhite();
		assertEquals("Black player wins", 2, board.getWinner()); // Black (Player 2) wins
	}
	public void stupidWhite() {
		board.move(new Point(1, 0), new Point(3, 2)); // White's turn
		board.move(new Point(7, 1), new Point(5, 3)); // Black's turn
		board.move(new Point(4, 7), new Point(4, 5)); // White's turn
		board.move(new Point(7, 5), new Point(4, 5)); // Black's turn
		board.move(new Point(5, 0), new Point(7, 2)); // White's turn
		board.move(new Point(4, 5), new Point(7, 2)); // Black's turn
		board.move(new Point(6, 0), new Point(4, 2)); // White's turn
		board.move(new Point(0, 1), new Point(2, 3)); // Black's turn
		board.move(new Point(2, 7), new Point(0, 5)); // White's turn
		board.move(new Point(0, 3), new Point(4, 3)); // Black's turn
		board.move(new Point(0, 5), new Point(1, 6)); // White's turn
		board.move(new Point(0, 6), new Point(2, 4)); // Black's turn
		board.move(new Point(3, 2), new Point(3, 5)); // White's turn
		board.move(new Point(0, 2), new Point(0, 0)); // Black's turn
		board.move(new Point(1, 7), new Point(4, 4)); // White's turn
		board.move(new Point(0, 4), new Point(4, 4)); // Black's turn
		board.move(new Point(3, 7), new Point(3, 4)); // White's turn
		board.move(new Point(2, 4), new Point(4, 2)); // Black's turn
		board.move(new Point(5, 7), new Point(3, 7)); // White's turn
		board.move(new Point(2, 3), new Point(6, 3)); // Black's turn
		board.move(new Point(6, 7), new Point(4, 7)); // White's turn
		board.move(new Point(7, 6), new Point(5, 4)); // Black's turn
		board.move(new Point(4, 0), new Point(0, 0)); // White's turn
	}

	@Test(timeout=1000)
	public void testGameOverBummerPlayer() {
		bummerPlayer();
		assertTrue("The white player eats a black piece that makes both players connected", board.isGameOver());
	}

	@Test(timeout=1000)
	public void testGetWinnerBummerPlayer() {
		bummerPlayer();
		assertEquals("White player wins", 1, board.getWinner()); // White (Player 1) wins
	}
	
	public void bummerPlayer() {
		board.move(new Point(4, 0), new Point(4, 2)); // White's turn
		board.move(new Point(7, 3), new Point(5, 5)); // Black's turn
		board.move(new Point(2, 0), new Point(0, 2)); // White's turn
		board.move(new Point(5, 5), new Point(2, 5)); // Black's turn
		board.move(new Point(0, 2), new Point(1, 1)); // White's turn
		board.move(new Point(0, 5), new Point(2, 7)); // Black's turn
		board.move(new Point(3, 7), new Point(3, 5)); // White's turn
		board.move(new Point(0, 3), new Point(3, 6)); // Black's turn
		board.move(new Point(5, 7), new Point(5, 5)); // White's turn
		board.move(new Point(0, 4), new Point(2, 4)); // Black's turn
		board.move(new Point(3, 0), new Point(3, 3)); // White's turn
		board.move(new Point(0, 1), new Point(2, 3)); // Black's turn
		board.move(new Point(1, 7), new Point(4, 4)); // White's turn
		board.move(new Point(7, 1), new Point(4, 4)); // Black's turn
		board.move(new Point(5, 5), new Point(4, 6)); // White's turn
		board.move(new Point(7, 4), new Point(6, 3)); // Black's turn
		board.move(new Point(1, 0), new Point(3, 2)); // White's turn
		board.move(new Point(7, 5), new Point(5, 3)); // Black's turn
		board.move(new Point(6, 7), new Point(4, 5)); // White's turn
		board.move(new Point(0, 6), new Point(4, 6)); // Black's turn
		board.move(new Point(4, 5), new Point(2, 3)); // White's turn
		board.move(new Point(4, 6), new Point(4, 2)); // Black's turn
		board.move(new Point(6, 0), new Point(4, 0)); // White's turn
		board.move(new Point(7, 6), new Point(7, 4)); // Black's turn
		board.move(new Point(3, 5), new Point(3, 1)); // White's turn
		board.move(new Point(2, 4), new Point(1, 3)); // Black's turn
		board.move(new Point(4, 7), new Point(6, 5)); // White's turn
		board.move(new Point(2, 5), new Point(4, 5)); // Black's turn
		board.move(new Point(6, 5), new Point(4, 3)); // White's turn
		board.move(new Point(6, 3), new Point(6, 2)); // Black's turn
		board.move(new Point(4, 3), new Point(3, 4)); // White's turn
		board.move(new Point(7, 4), new Point(6, 3)); // Black's turn
		board.move(new Point(1, 1), new Point(1, 3)); // White's turn
	}
	
	@Test(timeout=1000)
	public void testGameOverBummer2() {
		bummer2();
		assertTrue("The black player eats a white piece that makes both players connected (one white piece)", board.isGameOver());
	}

	@Test(timeout=1000)
	public void testGetWinnerBummer2() {
		bummer2();
		assertEquals("Black player wins", 2, board.getWinner()); // Black (Player 2) wins
	}
	
	public void bummer2(){
		//Black wins by connecting his pieces by eating the 2nd last white piece
		board.move(new Point(6,0), new Point(6,2)); //White's turn
		board.move(new Point(7,2), new Point(5,0)); //Black's turn
		board.move(new Point(6,2), new Point(6,4)); //White's turn
		board.move(new Point(7,5), new Point(5,7)); //Black's turn
		board.move(new Point(6,4), new Point(4,2)); //White's turn
		board.move(new Point(7,1), new Point(5,3)); //Black's turn
		board.move(new Point(3,0), new Point(3,2)); //White's turn
		board.move(new Point(5,3), new Point(2,3)); //Black's turn
		board.move(new Point(4,7), new Point(4,4)); //White's turn
		board.move(new Point(7,4), new Point(4,4)); //Black's turn
		board.move(new Point(4,2), new Point(6,4)); //White's turn
		board.move(new Point(7,3), new Point(4,3)); //Black's turn
		board.move(new Point(6,7), new Point(3,4)); //White's turn
		board.move(new Point(0,4), new Point(2,6)); //Black's turn
		board.move(new Point(3,7), new Point(5,5)); //White's turn
		board.move(new Point(4,4), new Point(1,7)); //Black's turn
		board.move(new Point(3,2), new Point(1,2)); //White's turn
		board.move(new Point(2,3), new Point(2,7)); //Black's turn
		board.move(new Point(1,2), new Point(1,5)); //White's turn
		board.move(new Point(4,3), new Point(1,0)); //Black's turn
		board.move(new Point(1,5), new Point(1,2)); //White's turn
		board.move(new Point(0,2), new Point(2,0)); //Black's turn
		board.move(new Point(5,5), new Point(4,4)); //White's turn
		board.move(new Point(5,7), new Point(4,6)); //Black's turn
		board.move(new Point(3,4), new Point(3,3)); //White's turn
		board.move(new Point(4,6), new Point(6,4)); //Black's turn
		board.move(new Point(4,4), new Point(2,4)); //White's turn
		board.move(new Point(7,6), new Point(5,4)); //Black's turn
		board.move(new Point(2,4), new Point(1,3)); //White's turn
		board.move(new Point(5,4), new Point(3,4)); //Black's turn
		board.move(new Point(3,3), new Point(2,2)); //White's turn
		board.move(new Point(2,6), new Point(1,5)); //Black's turn
		board.move(new Point(2,2), new Point(0,2)); //White's turn
		board.move(new Point(3,4), new Point(1,4)); //Black's turn
		board.move(new Point(4,0), new Point(4,1)); //White's turn
		board.move(new Point(1,5), new Point(0,4)); //Black's turn
		board.move(new Point(1,2), new Point(3,2)); //White's turn
		board.move(new Point(6,4), new Point(4,2)); //Black's turn
		board.move(new Point(1,3), new Point(3,1)); //White's turn
		board.move(new Point(4,2), new Point(2,4)); //Black's turn
		board.move(new Point(3,2), new Point(3,0)); //White's turn
		board.move(new Point(5,0), new Point(5,1)); //Black's turn
		board.move(new Point(4,1), new Point(4,2)); //White's turn
		board.move(new Point(5,1), new Point(5,2)); //Black's turn
		board.move(new Point(4,2), new Point(4,3)); //White's turn
		board.move(new Point(5,2), new Point(3,0)); //Black's turn
		board.move(new Point(4,3), new Point(5,2)); //White's turn
		board.move(new Point(3,0), new Point(5,2)); //Black's turn
		board.move(new Point(3,1), new Point(3,2)); //White's turn
		board.move(new Point(5,2), new Point(4,1)); //Black's turn
		board.move(new Point(3,2), new Point(3,1)); //White's turn
		board.move(new Point(4,1), new Point(3,0)); //Black's turn
		board.move(new Point(3,1), new Point(3,3)); //White's turn
		board.move(new Point(2,4), new Point(0,2)); //Black's turn
	}

	@Test(timeout=1000) 
	public void testGameOverStupidBlack() {
		stupidBlack();
		assertTrue("The black player eats up all white pieces", board.isGameOver());
	}

	@Test(timeout=1000)
	public void testGetWinnerStupidBlack() {
		stupidBlack();
		assertEquals("White player wins", 1, board.getWinner()); // White (Player 1) wins
	}
	
	public void stupidBlack() {
		// Black eats up all white but one and so loses
		board.move(new Point(6, 0), new Point(6, 2)); // White's turn
		board.move(new Point(7, 2), new Point(5, 0)); // Black's turn
		board.move(new Point(4, 0), new Point(4, 2)); // White's turn
		board.move(new Point(0, 2), new Point(2, 0)); // Black's turn
		board.move(new Point(1, 0), new Point(1, 2)); // White's turn
		board.move(new Point(7, 5), new Point(5, 7)); // Black's turn
		board.move(new Point(6, 2), new Point(4, 0)); // White's turn
		board.move(new Point(2, 0), new Point(4, 2)); // Black's turn
		board.move(new Point(4, 0), new Point(6, 2)); // White's turn
		board.move(new Point(4, 2), new Point(1, 2)); // Black's turn
		board.move(new Point(6, 7), new Point(6, 5)); // White's turn
		board.move(new Point(5, 0), new Point(3, 0)); // Black's turn
		board.move(new Point(4, 7), new Point(4, 6)); // White's turn
		board.move(new Point(7, 3), new Point(4, 6)); // Black's turn
		board.move(new Point(6, 2), new Point(5, 1)); // White's turn
		board.move(new Point(0, 1), new Point(2, 3)); // Black's turn
		board.move(new Point(6, 5), new Point(4, 5)); // White's turn
		board.move(new Point(0, 5), new Point(2, 7)); // Black's turn
		board.move(new Point(1, 7), new Point(1, 5)); // White's turn
		board.move(new Point(1, 2), new Point(1, 4)); // Black's turn
		board.move(new Point(4, 5), new Point(2, 5)); // White's turn
		board.move(new Point(7, 1), new Point(5, 1)); // Black's turn
		board.move(new Point(2, 5), new Point(3, 4)); // White's turn
		board.move(new Point(7, 4), new Point(3, 4)); // Black's turn
		board.move(new Point(1, 5), new Point(4, 2)); // White's turn
		board.move(new Point(5, 1), new Point(4, 1)); // Black's turn
		board.move(new Point(4, 2), new Point(3, 2)); // White's turn
		board.move(new Point(2, 3), new Point(2, 1)); // Black's turn
		board.move(new Point(3, 2), new Point(4, 2)); // White's turn
		board.move(new Point(5, 7), new Point(5, 6)); // Black's turn
		board.move(new Point(3, 7), new Point(5, 7)); // White's turn
		board.move(new Point(4, 6), new Point(4, 3)); // Black's turn
		board.move(new Point(5, 7), new Point(7, 7)); // White's turn
		board.move(new Point(2, 7), new Point(3, 6)); // Black's turn
		board.move(new Point(7, 7), new Point(6, 6)); // White's turn
		board.move(new Point(7, 6), new Point(7, 5)); // Black's turn
		board.move(new Point(4, 2), new Point(5, 2)); // White's turn
		board.move(new Point(3, 6), new Point(4, 5)); // Black's turn
		board.move(new Point(5, 2), new Point(4, 2)); // White's turn
		board.move(new Point(0, 6), new Point(3, 6)); // Black's turn
		board.move(new Point(4, 2), new Point(5, 2)); // White's turn
		board.move(new Point(3, 6), new Point(6, 6)); // Black's turn
	}
	
	
	
	
	
	
	public static boolean checkContainsPoint(ArrayList<Point> points, Point point){
		for(Point p: points){
			if(p.getX()==point.getX() && p.getY()==point.getY())
				return true;
		}
		return false;
	}
}