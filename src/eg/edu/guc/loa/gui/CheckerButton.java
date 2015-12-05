package eg.edu.guc.loa.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

import eg.edu.guc.loa.engine.Point;

public class CheckerButton extends JButton implements MouseListener,
		ActionListener {

	private static final long serialVersionUID = 1L;
	public static final Color WHITE = Color.WHITE;
	public static final Color BLACK = Color.BLACK;
	public static final Color BLANK = Color.RED;
	private Color checkerColor;
	private int x;
	private int y;
	private Point position;
	private Color background1 = new Color(255, 181, 127);
	private Color background2 = new Color(127, 54, 0);
	private boolean highlighted;
	
	private boolean clicked;
	private static boolean oneButtonClicked = false;
	
	private static int border = 0;
	private static int count = 0;
	

	private Border lowerBevel = BorderFactory.createLoweredBevelBorder();
	private Border raiseBevel = BorderFactory.createRaisedBevelBorder();

	public CheckerButton(int color, int x, int y, Point p) {

		this.x = x;
		this.y = y;
		setLayout(null);
		this.addMouseListener(this);
		this.grabFocus(); //neb2a ns2al a7mad 
		if (color == 1) {
			checkerColor = WHITE;
		} else if (color == 2) {
			checkerColor = BLACK;
		} else {
			checkerColor = BLANK;
		}

		this.setContentAreaFilled(false); //same
		setOpaque(true); //to set background
		setBounds(x, y, 127, 92);
		if (border % 2 == 0) {

			setBackground(background2);
			border++;
		} else {
			setBackground(background1);
			border++;
		}
		count++;
		if (count % 8 == 0) {
			border++;
		}

		position = p;
		highlighted = false;
		clicked = false;
	}

	public void paint(Graphics g) { //same
		Graphics2D checker = (Graphics2D) g;

		checker.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		checker.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		checker.setStroke(new BasicStroke(2 * this.getWidth() / 100));

		super.paint(checker);

		Ellipse2D c = new Ellipse2D.Double(this.getWidth() / 2 - 40,
				this.getHeight() / 2 - 40, 80, 80);

		if (oneButtonClicked && clicked) {

			checker.setColor(BLANK);
			checker.fill(c);
			return;
		}

		if (highlighted) {
			this.setBorder(raiseBevel);

			checker.setColor(BLANK);
			checker.fill(c);
			return;
		}

		if (checkerColor == BLANK) {
			this.setBorder(lowerBevel);
			return;
		}
		if (checkerColor == WHITE || checkerColor == BLACK) {
			this.setBorder(lowerBevel);
			checker.setColor(checkerColor);
			checker.fill(c);
			return;
		}

	}

	public Point getPosition() {
		return position;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		setFocusable(true);
		grabFocus();
		setEnabled(true);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		setFocusable(false);
		grabFocus();
		setEnabled(false);

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public Color getCheckerColor() {
		return checkerColor;
	}

	public void setCheckerColor(Color checkerColor) {
		this.checkerColor = checkerColor;
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

	public Color getBackground1() {
		return background1;
	}

	public void setBackground1(Color background1) {
		this.background1 = background1;
	}

	public Color getBackground2() {
		return background2;
	}

	public void setBackground2(Color background2) {
		this.background2 = background2;
	}

	public boolean isHighlighted() {
		return highlighted;
	}

	public void setHighlighted(boolean highlighted) {
		this.highlighted = highlighted;
	}

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	public Border getLowerBevel() {
		return lowerBevel;
	}

	public void setLowerBevel(Border lowerBevel) {
		this.lowerBevel = lowerBevel;
	}

	public Border getRaiseBevel() {
		return raiseBevel;
	}

	public void setRaiseBevel(Border raiseBevel) {
		this.raiseBevel = raiseBevel;
	}

	public void setPosition(Point position) {
		this.position = position;
	}
	public static boolean isOneButtonClicked() {
		return oneButtonClicked;
	}

	public static void setOneButtonClicked(boolean oneButtonClicked) {
		CheckerButton.oneButtonClicked = oneButtonClicked;
	}

	public static int getStaticBorder() {
		return border;
	}

	public static void setStaticBorder(int border) {
		CheckerButton.border = border;
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		CheckerButton.count = count;
	}

}
