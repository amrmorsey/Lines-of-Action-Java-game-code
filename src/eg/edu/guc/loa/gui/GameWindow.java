package eg.edu.guc.loa.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import eg.edu.guc.loa.engine.Board;
import eg.edu.guc.loa.engine.Player;

import eg.edu.guc.loa.engine.Point;

public class GameWindow extends JFrame implements MouseListener, ActionListener {
	
	
	private WelcomeWindow restart;
	private static final long serialVersionUID = 1L;
	private JPanel gamePanel;
	private RightSidePanel rightPanel;
	private Board game;
	
	// 3lshan lma negy nrsm el board y3mlha one by one by different colors 
	private int x = 0;
	private int y = 0;
	
	private CheckerButton[][] tem = new CheckerButton[8][8];
	
	//width of button and its height
	public static final int WIDTH = 127;
	public static final int HEIGHT = 92;

	//to dedicate the first checker clicked
	private static boolean firstHit = true;
	
	private static Point clickedPoint;
	

	public GameWindow(WelcomeWindow h, Player white, Player black) {
		super("Lines Of Action");
		restart = h;
		setLayout(null); // 3ashan n3raf nzabatha bra7tna
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenDimension);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		this.setIconImage(new ImageIcon(this.getClass().getResource(
				"Game-Center-icon.png")).getImage());

		game = new Board();
		game.getBlackPlayer().setName(black.getName());
		game.getWhitePlayer().setName(white.getName());
		
		Player [] players = new Player [2];
		
		players[0] = game.getWhitePlayer();
		players[1] = game.getBlackPlayer();
		
		rightPanel = new RightSidePanel(this, h, this.getContentPane()
				.getSize(), players);
		
		rightPanel.addMouseListener(this);

		gamePanel = new JPanel();
		gamePanel.setLayout(null);
		gamePanel.setSize(this.getContentPane().getSize());
		updateBoard();
		
		this.getContentPane().add(rightPanel);
		this.getContentPane().add(gamePanel);
		this.validate();
		this.repaint();
	}

	public void updateBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {

				tem[i][j] = new CheckerButton(game.getColor(new Point(j, i)),
						x, y, new Point(i, j));

				tem[i][j].addMouseListener(this);
				x += WIDTH;
				gamePanel.add(tem[i][j]);

			}
			y += HEIGHT;
			x = 0;

		}
		firstHit = true;
		x = 0;
		y = 0;
		gamePanel.validate();
		gamePanel.repaint();
		rightPanel.validate();
		rightPanel.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		Object temo = arg0.getSource();
		Point start = ((CheckerButton) temo).getPosition();
		if (game.getTurn() == game.getColor(new Point(start.getY(), start
				.getX()))) {
			if (firstHit) {
				ArrayList<Point> possibleMoves = game
						.getPossibleMoves(new Point(start.getY(), start.getX()));
				clickedPoint = start;
				CheckerButton.setOneButtonClicked(!CheckerButton
						.isOneButtonClicked());
				perform(possibleMoves, start);
				firstHit = !firstHit;
				return;
			} else {
				if (clickedPoint.compareTo(start) == 0) {
					deSelect(start);
					return;
				}
			}
		} else {
			if (game.move(new Point(clickedPoint.getY(), clickedPoint.getX()),
					new Point(start.getY(), start.getX()))) {
				back();
				tem = new CheckerButton[8][8];
				updateBoard();
				if (game.isGameOver()) {
					if (game.getWinner() == 1) {
						JOptionPane.showMessageDialog(null, game
								.getWhitePlayer().getName() + " Wins !!",
								"Congratulations", JOptionPane.PLAIN_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, game
								.getBlackPlayer().getName() + " Wins !!",
								"Congratulations", JOptionPane.PLAIN_MESSAGE);
					}
					int x = JOptionPane.showConfirmDialog(null,
							"Do you want to play again ??",
							"Confirmation Message", 0);
					if (x == 0) {
						this.setVisible(false);
						restart.setVisible(true);
					} else {
						System.exit(0);
					}
				}
			}
		}
	}

	public void deSelect(Point start) {
		ArrayList<Point> possibleMoves = game.getPossibleMoves(new Point(start
				.getY(), start.getX()));
		CheckerButton.setOneButtonClicked(!CheckerButton.isOneButtonClicked());
		perform(possibleMoves, start);
		firstHit = !firstHit;
	}

	public void back() {
		rightPanel.getBlackCheckers().setText(
				(game.getBlackPlayer().eatenCheckers()));
		rightPanel.getWhiteCheckers().setText(
				(game.getWhitePlayer().eatenCheckers()));

		if (game.getTurn() == 1) {
			rightPanel.getNames().setText("White Turn");
		} else {
			rightPanel.getNames().setText("Black Turn");
		}

		for (int i = 0; i < tem.length; i++) {
			for (int j = 0; j < tem[i].length; j++) {

				tem[i][j].setClicked(false);
				tem[i][j].setHighlighted(false);
				CheckerButton.setOneButtonClicked(false);
				tem[i][j].validate();
				tem[i][j].repaint();

				gamePanel.remove(tem[i][j]);
				
				rightPanel.validate();
				rightPanel.repaint();
				this.validate();
				this.repaint();
			}
		}
	}

	public void perform(ArrayList<Point> p, Point start) {
		if (!CheckerButton.isOneButtonClicked()) {
			deselect(p, start);
			return;
		}
		for (int i = 0; i < p.size(); i++) {
			Point temp = p.get(i);
			tem[temp.getY()][temp.getX()].setClicked(true);
			tem[temp.getY()][temp.getX()].validate();
			tem[temp.getY()][temp.getX()].repaint();
		}

	}

	public void deselect(ArrayList<Point> p, Point start) {
		for (int i = 0; i < p.size(); i++) {
			Point temp = p.get(i);
			tem[temp.getY()][temp.getX()].setClicked(false);
			tem[temp.getY()][temp.getX()].validate();
			tem[temp.getY()][temp.getX()].repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		if (RightSidePanel.isNewGameAction()) {
			setVisible(false);
			dispose();
			restart.setVisible(true);
		}
		if (arg0.getSource() instanceof CheckerButton) {
			Object temo = arg0.getSource();
			Point start = ((CheckerButton) temo).getPosition();
			ArrayList<Point> highlighted = game.getPossibleMoves(new Point(
					start.getY(), start.getX()));
			highlightButton(highlighted);
		}
	}

	public void highlightButton(ArrayList<Point> p) {
		if (CheckerButton.isOneButtonClicked()) {
			return;
		}
		for (int i = 0; i < p.size(); i++) {
			Point temp = p.get(i);
			tem[temp.getY()][temp.getX()].setHighlighted(true);
			tem[temp.getY()][temp.getX()].validate();
			tem[temp.getY()][temp.getX()].repaint();
		}

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		if (RightSidePanel.isNewGameAction()) {
			setVisible(false);
			dispose();
			restart.setVisible(true);
		}
		if (arg0.getSource() instanceof CheckerButton) {
			Object temo = arg0.getSource();
			Point start = ((CheckerButton) temo).getPosition();
			ArrayList<Point> highlighted = game.getPossibleMoves(new Point(
					start.getY(), start.getX()));

			deHighlightButton(highlighted);
		}
	}

	public void deHighlightButton(ArrayList<Point> p) {
		if (CheckerButton.isOneButtonClicked()) {
			return;
		}
		for (int i = 0; i < p.size(); i++) {
			Point temp = p.get(i);
			tem[temp.getY()][temp.getX()].setHighlighted(false);
			tem[temp.getY()][temp.getX()].validate();
			tem[temp.getY()][temp.getX()].repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	public JPanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(JPanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public RightSidePanel getRightPanel() {
		return rightPanel;
	}

	public void setRightPanel(RightSidePanel rightPanel) {
		this.rightPanel = rightPanel;
	}

	public Board getGame() {
		return game;
	}

	public void setGame(Board game) {
		this.game = game;
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

	public CheckerButton[][] getTem() {
		return tem;
	}

	public void setTem(CheckerButton[][] tem) {
		this.tem = tem;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}
}
