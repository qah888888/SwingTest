import java.awt.Color;

import javax.swing.JFrame;

public class Diagram extends JFrame {

	public Diagram() {

		super("Diagram -- shuangwhywhy");

		setSize(600, 500);

		setLocationRelativeTo(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Diagram_Panel dp = new Diagram_Panel();

		dp.setBackground(Color.WHITE);

		setContentPane(dp);

		setVisible(true);

		dp.newGraph();

	}
}
