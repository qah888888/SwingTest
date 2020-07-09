import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import javafx.stage.WindowEvent;

public class UserManager {
	public static String passWord = "123456";
	public JFrame jf;
	Connection conn;
	Statement stam;

	public void changePassword() {
		jf = new JFrame("用户管理");
		jf.setSize(400, 350);
		jf.setLocation(200, 175);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);

		JPanel panel = new JPanel(null);

		JLabel label = new JLabel("修改密码");
		Font font = new Font("宋体", Font.BOLD, 18);
		label.setFont(font);
		label.setBounds(150, 10, 100, 30);
		label.setForeground(Color.BLACK);
		panel.add(label);

		JLabel label1 = new JLabel("用户名：");
		Font font1 = new Font("宋体", Font.BOLD, 18);
		label1.setFont(font1);//
		label1.setBounds(80, 70, 100, 30);
		label1.setForeground(Color.BLACK);//
		panel.add(label1);
		JLabel account = new JLabel(SwingMain.userName.getText());
		account.setFont(font1);
		account.setBounds(150, 70, 100, 30);
		account.setForeground(Color.BLACK);//
		panel.add(account);

		JLabel label2 = new JLabel("原密码："); 
		Font font2 = new Font("宋体", Font.BOLD, 18);
		label2.setFont(font2);
		label2.setBounds(80, 100, 100, 30);
		label2.setForeground(Color.BLACK);
		panel.add(label2);
		JPasswordField passwordField = new JPasswordField(15);
		passwordField.setBounds(150, 104, 130, 25);
		panel.add(passwordField);

		JLabel label3 = new JLabel("新密码："); //
		Font font3 = new Font("宋体", Font.BOLD, 18);
		label3.setFont(font3);//
		label3.setBounds(80, 130, 100, 30);
		label3.setForeground(Color.BLACK);//
		panel.add(label3);
		JPasswordField passwordField2 = new JPasswordField(15);
		passwordField2.setBounds(150, 134, 130, 25);
		panel.add(passwordField2);

		JLabel label4 = new JLabel("确认密码：");
		Font font4 = new Font("宋体", Font.BOLD, 18);
		label4.setFont(font3);//
		label4.setBounds(60, 160, 100, 30);
		label4.setForeground(Color.BLACK);//
		panel.add(label4);
		JPasswordField passwordField3 = new JPasswordField(15);
		passwordField3.setBounds(150, 164, 130, 25);
		panel.add(passwordField3);

		JButton btn01 = new JButton("确定");
		btn01.setFont(font4);
		btn01.setLocation(150, 220);
		btn01.setSize(70, 30);
		panel.add(btn01);

		btn01.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String passWord1 = String.valueOf(passwordField.getPassword());
				String passWord2 = String.valueOf(passwordField2.getPassword());
				String passWord3 = String.valueOf(passwordField3.getPassword());

				try {
					
					conn = DBconnected.getConnection();
					
					stam = conn.createStatement();

					String sql1 = "select * from users where userName='" + account.getText() + "'";
					
					ResultSet rs = stam.executeQuery(sql1);
					String sqlpassword = null;
					while (rs.next()) {
						sqlpassword = rs.getString("password");
					}

					if (!passWord1.equals(sqlpassword)) {
						JOptionPane.showMessageDialog(null, "原密码错误！");
					} else {
						if (passWord2.equals(passWord3) && passWord2 != null) {

							
							String sql = "update users set password='" + passWord2 + "'" + " where userName='"
									+ account.getText() + "'";

							
							int num = stam.executeUpdate(sql);
							if (num != 0) {
								JOptionPane.showMessageDialog(null, "修改成功");
							}

							jf.dispose();

							MainPanel.jf.setEnabled(true);
							MainPanel.jf.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(null, "新密码两次输入不一致，请重新输入！");
						}
					}

				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} finally {
					DBconnected.result(conn, stam);
				}

			}
		});

		jf.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosed(e);
				MainPanel.jf.setEnabled(true);
//				MainPanel.jf.setVisible(true);
			}
		});

		jf.setContentPane(panel);

	}
}
