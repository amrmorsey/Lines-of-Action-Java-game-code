package eg.edu.guc.loa.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import eg.edu.guc.loa.engine.Player;

public class WelcomeWindow extends JFrame implements ActionListener,
		MouseListener {

	private static final long serialVersionUID = 1L;
	private JButton start;
	private JLabel player1, player2;
	private JPanel namesPanel;
	private GameWindow mainGame;
	private JTextField player1Name, player2Name;
	private String white, black;
	private JPanel upPanel;
	
	private Icon on;
	private Icon off;

	public WelcomeWindow() {
		super("Setting Window");
		this.setIconImage(new ImageIcon(this.getClass().getResource(
				"Game-Center-icon.png")).getImage());
		setLayout(null);
		setBounds(490, 150, 500, 500);
		setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		upPanel = new JPanel();
		upPanel.setLayout(null);
		upPanel.setBounds(15, 20, this.getWidth() - 35,
				(int) (this.getHeight() * .45));
		upPanel.setBorder(BorderFactory.createRaisedSoftBevelBorder());

		setConfigNamesPanel();
		this.getContentPane().setBackground(new Color(25, 25, 25));
		upPanel.setBackground(Color.white);

		this.getContentPane().add(upPanel);
		this.getContentPane().add(namesPanel);
		this.getContentPane().add(start);

		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == start) {
			white = player1Name.getText();
			black = player2Name.getText();
			if (!white.equalsIgnoreCase("") && !black.equalsIgnoreCase("")
					&& !white.equalsIgnoreCase("White Player Name")
					&& !black.equalsIgnoreCase("Black Player Name")) {
				this.setVisible(false);
				dispose();
				setMainGame(new GameWindow(this, new Player(white, 1),
						new Player(black, 2)));
			} else {
				JOptionPane.showMessageDialog(null,
						"You should enter players names !!", "Message Dialog",
						JOptionPane.PLAIN_MESSAGE);
			}

		}

	}

	public void setConfigNamesPanel() {
		on = new ImageIcon(this.getClass().getResource(
				"Preferences-On-icon.png"));
		off = new ImageIcon(this.getClass().getResource(
				"Preferences-OFF-icon.png"));
		namesPanel = new JPanel();
		namesPanel.setLayout(new GridLayout(2, 2));
		namesPanel.setBounds(15, 250, this.getWidth() - 35,
				(int) (this.getHeight() * 0.25));
		namesPanel
				.setBorder(BorderFactory.createTitledBorder("Player's Names"));

		namesPanel.setBackground(new Color(204, 204, 204));
		player1 = new JLabel("  White Player Name :");
		player1.setIcon(off);
		player1Name = new JTextField("White Player Name");
		player1Name.setLayout(null);
		player2 = new JLabel("  Black Player Name :");
		player2.setIcon(off);
		player1Name.setFont(new Font(player1Name.getText(), 20, 20));
		player1Name.setBounds(new Rectangle(100, 50));

		player2Name = new JTextField("Black Player Name");
		player2Name.setLayout(null);
		player2Name.setFont(new Font(player1Name.getText(), 20, 20));
		player2Name.setBounds(new Rectangle(100, 50));

		namesPanel.add(player1);
		namesPanel.add(player1Name);
		namesPanel.add(player2);
		namesPanel.add(player2Name);

		player1Name.repaint();
		namesPanel.repaint();

		player1Name.addMouseListener(this);
		player2Name.addMouseListener(this);

		start = new JButton("Start");
		start.setBounds(20, 400, 200, 25);
		start.addActionListener(this);

	}

	public static void main(String[] args) {
		new WelcomeWindow();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == player1Name) {
			player1Name.setText("");
			player1.setIcon(on);
		} else if (arg0.getSource() == player2Name) {
			player2Name.setText("");
			player2.setIcon(on);
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		player1.setIcon(off);
		player2.setIcon(off);

	}

	public void setMainGame(GameWindow mainGame) {
		this.mainGame = mainGame;
	}

	public GameWindow getMainGame() {
		return mainGame;
	}
}
