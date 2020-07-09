import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Random;
import java.util.logging.Logger;

import javax.swing.JPanel;

public class Diagram_Panel extends JPanel {
	private Rectangle btnNew = new Rectangle(10, 10, 82, 22);

	private int state;

	private Random rnd = new Random();

	private int[][] vertices;

	private int[][] params;

	private int[][] linked;

	public Diagram_Panel() {

		setBackground(Color.WHITE);

		addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseMoved(MouseEvent me) {

				System.out.println("mouseMoved" + btnNew.height + "......" + me.getY() + "....." + btnNew.y);

				if (me.getX() >= btnNew.x && me.getX() <= btnNew.x + btnNew.width && me.getY() >= btnNew.y
						&& me.getY() <= btnNew.y + btnNew.height) {

					if (state != 1) {

						state = 1;

						repaint();

					}

				} else {

					if (state != 0) {

						state = 0;

						repaint();

					}

				}

			}

			// 阉锋牗瀚?
			@Override
			public void mouseDragged(MouseEvent me) {

				System.out.println("mouseDragged" + btnNew.height);

				if (state == 2 || state == 1) {

					if (me.getX() >= btnNew.x && me.getX() <= btnNew.x + btnNew.width && me.getY() >= btnNew.y
							&& me.getY() <= btnNew.y + btnNew.height)

						if (state != 2)

							state = 2;

						else

						if (state != 1)

							state = 1;

					repaint();

				}

			}

		});

		addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent me) {

				System.out.println("mousePressed" + state);

				if (state == 1) {

					state = 2;

					newGraph();

				}

			}

			@Override
			public void mouseReleased(MouseEvent me) {

				System.out.println("mouseReleased" + btnNew.x);

				if (me.getX() >= btnNew.x && me.getX() <= btnNew.x + btnNew.width && me.getY() >= btnNew.y
						&& me.getY() <= btnNew.y + btnNew.height)

					state = 1;

				else

					state = 0;

				repaint();

			}

		});

	}

	private void mouseOver(Rectangle btn, String str, Graphics g) {

		Color tmp = g.getColor();

		g.setColor(Color.GRAY);

		g.fillRect(btn.x, btn.y, btn.width + 1, btn.height + 1);

		g.setColor(Color.WHITE);

		g.drawLine(btn.x, btn.y, btn.x, btn.y + btn.height - 1);

		g.drawLine(btn.x, btn.y, btn.x + btn.width - 1, btn.y);

		g.drawString(str, btn.x + 10, btn.y + 15);

		g.setColor(tmp);

	}

	private void mouseOff(Rectangle btn, String str, Graphics g) {

		Color tmp = g.getColor();

		g.setColor(Color.BLACK);

		g.drawRect(btn.x + 1, btn.y + 1, btn.width, btn.height);

		g.setColor(Color.WHITE);

		g.fillRect(btn.x, btn.y, btn.width, btn.height);

		g.setColor(Color.BLACK);

		g.drawRect(btn.x, btn.y, btn.width, btn.height);

		g.drawString(str, btn.x + 10, btn.y + 15);

		g.setColor(tmp);

	}

	private void mouseDown(Rectangle btn, String str, Graphics g) {

		Color tmp = g.getColor();

		g.setColor(Color.DARK_GRAY);

		g.fillRect(btn.x + 1, btn.y + 1, btn.width + 1, btn.height + 1);

		g.setColor(Color.WHITE);

		g.drawLine(btn.x + 2, btn.y + btn.height + 1, btn.x + btn.width + 1, btn.y + btn.height + 1);

		g.drawLine(btn.x + btn.width + 1, btn.y + 2, btn.x + btn.width + 1, btn.y + btn.height + 1);

		g.drawString(str, btn.x + 11, btn.y + 16);

		g.setColor(tmp);

	}

	@Override
	public void paint(Graphics g) {

		super.paint(g);

		switch (state) {

		case 0:

			mouseOff(btnNew, "New Graph", g);

			break;

		case 1:

			mouseOver(btnNew, "New Graph", g);

			break;

		case 2:

			mouseDown(btnNew, "New Graph", g);

			break;

		}

		for (int i = 0; i < vertices.length; i++)

			for (int j = i + 1; j < vertices.length; j++)

				if (linked[i][j] > 0) {

					g.drawLine(vertices[i][0] + 5, vertices[i][1] + 5, vertices[j][0] + 5, vertices[j][1] + 5);

					g.drawString(linked[i][j] + "", (vertices[i][0] + vertices[j][0]) / 2,
							(vertices[i][1] + vertices[j][1]) / 2);

				}

		g.setColor(Color.WHITE);

		for (int i = 0; i < vertices.length; i++)

			g.fillOval(vertices[i][0], vertices[i][1], 10, 10);

		g.setColor(Color.BLACK);

		for (int i = 0; i < vertices.length; i++) {

			g.drawOval(vertices[i][0], vertices[i][1], 10, 10);

			g.drawString((char) (i + 'A') + "(" + linked[i][0] + "," + linked[i][1] + ")", vertices[i][0] - 5,
					vertices[i][1] - 5);

		}

	}

	public void newGraph() {

		vertices = new int[rnd.nextInt(5) + 3][2];

		params = new int[vertices.length][2];

		linked = new int[vertices.length][vertices.length];

		for (int i = 0; i < vertices.length; i++) {

			while (true) {

				vertices[i][0] = rnd.nextInt((int) (getWidth() * 0.6)) + 100;

				vertices[i][1] = rnd.nextInt((int) (getHeight() * 0.6)) + btnNew.height + 100;

				int j = 0;

				for (; j < i; j++)

					if (Math.abs(vertices[j][0] - vertices[i][0]) < 50
							&& Math.abs(vertices[j][1] - vertices[i][1]) < 50)

						break;

				if (j == i)

					break;

			}

		}

		for (int i = 0; i < vertices.length; i++)

			for (int j = i + 1; j < vertices.length; j++)

				if (rnd.nextInt(100) > 20)

					linked[i][j] = rnd.nextInt(10) + 1;

		for (int i = 0; i < vertices.length; i++) {

			linked[i][0] = rnd.nextInt(10) + 1;

			linked[i][1] = rnd.nextInt(10) + 1;

		}

		repaint();

	}
}
