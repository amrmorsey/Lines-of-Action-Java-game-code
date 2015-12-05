package eg.edu.guc.loa.gui;

import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.border.TitledBorder;

import eg.edu.guc.loa.engine.Player;

public class RightSidePanel extends JPanel implements ActionListener {
	private JPanel firstPanel, whitePlayer, blackPlayer;
	private JLabel blackCheckers;
	private JLabel whiteCheckers;
	private Player white, black;

	private JPanel secondPanel;
	private JButton newGame, restart, exit;
	private static boolean newGameAction;
	private static boolean restartAction;
	private static final long serialVersionUID = 1L;

	private JPanel turnsPanel;
	private JLabel names;

	private GameWindow restartGame;
	private WelcomeWindow welcome;

	public RightSidePanel(GameWindow game, WelcomeWindow welcome, Dimension dimension,
			Player [] p) {
		
		restartGame = game;
		this.welcome = welcome;

		setNewGameAction(false);
		setRestartAction(false);
		setLayout(null);
		setBounds((int) dimension.getWidth()
				- (int) (dimension.getWidth() * 0.25), 0,
				(int) (dimension.getWidth() * 0.25),
				(int) dimension.getHeight());
		TitledBorder title = BorderFactory
				.createTitledBorder(
						BorderFactory.createLoweredSoftBevelBorder(),
						"Commentary Zone");
		title.setTitlePosition(TitledBorder.ABOVE_TOP);
		this.setBorder(title);
		white = p[0];
		black = p[1];

		setConfigFirstPanel();
		setConfigSecondPanel();
		setConfigThirdPanel();
		this.add(firstPanel);
		this.add(secondPanel);
		this.add(turnsPanel);

		this.repaint();
	}

	public void setConfigFirstPanel() {
		firstPanel = new JPanel();
		firstPanel.setLayout(null);
		firstPanel.setVisible(true);
		firstPanel.setBorder(BorderFactory
				.createTitledBorder("Number of Checkers :"));

		// c = (Icon) new ImageIcon(this.getClass().getResource(
		// "User-icon.png")).getImage();

		firstPanel.setBounds(20, 20, this.getWidth() - 40,
				(int) (this.getHeight() * 0.25));

		blackCheckers = new JLabel("12");
		blackCheckers.setBounds(10, 10, 100, 130);
		blackCheckers.setFont(new Font(blackCheckers.getText(), 100, 90));

		whiteCheckers = new JLabel("12");

		whiteCheckers.setBounds(10, 10, 100, 130);
		whiteCheckers.setFont(new Font(whiteCheckers.getText(), 100, 90));

		whitePlayer = new JPanel();
		whitePlayer
				.setBorder(BorderFactory.createTitledBorder(white.getName()));
		whitePlayer.setLayout(null);
		whitePlayer.setBounds((int) (firstPanel.getWidth() * 0.125) - 12, 20,
				120, 150);
		whitePlayer.setVisible(true);

		blackPlayer = new JPanel();
		blackPlayer.setLayout(null);

		blackPlayer
				.setBorder(BorderFactory.createTitledBorder(black.getName()));
		blackPlayer.setBounds(
				(int) (firstPanel.getWidth() * (0.125)) + 130 - 12, 20, 120,
				150);
		blackPlayer.setVisible(true);

		whitePlayer.add(whiteCheckers);
		blackPlayer.add(blackCheckers);
		firstPanel.add(blackPlayer);
		firstPanel.add(whitePlayer);
		firstPanel.repaint();
	}

	public void setConfigSecondPanel() {
		secondPanel = new JPanel();
		// secondPanel.setLayout(new GridLayout(2,2));
		secondPanel.setVisible(true);
		secondPanel.setBorder(BorderFactory.createLoweredBevelBorder());

		secondPanel.setBounds(20, 30 + firstPanel.getHeight(),
				this.getWidth() - 40, 50);

		newGame = new JButton("New Game");
		// newGame.setBounds(0, 100, 100, 30);
		newGame.addActionListener(this);
		newGame.setToolTipText("Play another Game with new Configurations");
		restart = new JButton("Restart");
		restart.addActionListener(this);
		restart.setToolTipText("Restart this Game with same Players");
		exit = new JButton("Exit");
		exit.addActionListener(this);
		exit.setToolTipText("Exit form current game");
		

		secondPanel.add(newGame);
		secondPanel.add(restart);
		secondPanel.add(exit);

	}

	public void setConfigThirdPanel() {
		turnsPanel = new JPanel();
		turnsPanel.setBounds(20,
				50 + firstPanel.getHeight() + secondPanel.getHeight(),
				this.getWidth() - 40, 100);
		turnsPanel.setVisible(true);
		turnsPanel.setBorder(BorderFactory.createLoweredBevelBorder());

		names = new JLabel("White Turn");
		names.setLayout(null);
		names.setFont(new Font(names.getText(), 30, 50));
		turnsPanel.add(names);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == newGame) {
			int x = JOptionPane.showConfirmDialog(null,
					"Are you sure you want to Start totally new game??",
					"Confirmation Message", 0);

			if (x == 0) {
				setNewGameAction(true);
			}
		} else if (e.getSource() == restart) {
			int x = JOptionPane.showConfirmDialog(null,
					"Are you sure you want to restart game??",
					"Confirmation Message", 0);
			if (x == 0) {
				restartGame.setVisible(false);
				new GameWindow(welcome, white, black);
			}
		} else if (e.getSource() == exit) {
			int x = JOptionPane.showConfirmDialog(null,
					"Are you sure you want to terminate ??",
					"Confirmation Message", 0);
			if (x == 0) {
				System.exit(0);
			}
		}
	}

	public static void setRestartAction(boolean restartAction) {
		RightSidePanel.restartAction = restartAction;
	}

	public static boolean isRestartAction() {
		return restartAction;
	}

	public static void setNewGameAction(boolean newGameAction) {
		RightSidePanel.newGameAction = newGameAction;
	}

	public static boolean isNewGameAction() {
		return newGameAction;
	}

	public JLabel getBlackCheckers() {
		return blackCheckers;
	}

	public void setBlackCheckers(JLabel blackCheckers) {
		this.blackCheckers = blackCheckers;
	}

	public JLabel getWhiteCheckers() {
		return whiteCheckers;
	}

	public void setWhiteCheckers(JLabel whiteCheckers) {
		this.whiteCheckers = whiteCheckers;
	}

	public JPanel getTurnsPanel() {
		return turnsPanel;
	}

	public void setTurnsPanel(JPanel turnsPanel) {
		this.turnsPanel = turnsPanel;
	}

	public JLabel getNames() {
		return names;
	}

	public void setNames(JLabel names) {
		this.names = names;
	}
}
