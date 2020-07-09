import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Register extends JFrame {

	private int LOGIN_WIDTH = 360;
	private int LOGIN_HEIGTH = 350;
	private JTextField userName;
	private JPasswordField password;
	private JButton btn1, btn2;
	private JLabel label3, label4;
	
	Connection conn;
	Statement stam;

	public void addMan() {

		setTitle("注册");
		setBounds(100, 50, LOGIN_WIDTH, LOGIN_HEIGTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);

		JPanel panel = new JPanel(null);

		JLabel label1 = new JLabel("用户名："); //
		Font font1 = new Font("宋体", Font.BOLD, 18);
		label1.setFont(font1);//
		label1.setBounds(80, 40, 100, 30);
		label1.setForeground(Color.BLACK);//
		panel.add(label1);
		JTextField userName = new JTextField(15);
		userName.setBounds(150, 45, 130, 25);
		panel.add(userName);

		JLabel label2 = new JLabel("密  码："); //
		Font font2 = new Font("宋体", Font.BOLD, 18);
		label2.setFont(font2);//
		label2.setBounds(80, 90, 100, 30);
		label2.setForeground(Color.BLACK);//
		panel.add(label2);
		JPasswordField passwordField2 = new JPasswordField(15);
		passwordField2.setBounds(150, 95, 130, 25);
		panel.add(passwordField2);

		JLabel label3 = new JLabel("确认密码：");
		Font font3 = new Font("宋体", Font.BOLD, 18);
		label3.setFont(font2);//
		label3.setBounds(63, 140, 100, 30);
		label3.setForeground(Color.BLACK);//
		panel.add(label3);
		JPasswordField passwordField3 = new JPasswordField(15);
		passwordField3.setBounds(150, 145, 130, 25);
		panel.add(passwordField3);

		btn1 = new JButton("注  册");
		btn1.setFont(font3);
		btn1.setBounds(85, 210, 90, 35);
//	     btn3.setIcon(new ImageIcon(Login.class.getResource("/images/insist.png")));
		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == btn1) {

					String passWord2 = String.valueOf(passwordField2.getPassword());
					String passWord3 = String.valueOf(passwordField3.getPassword());

					if (userName.getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "请输入用户名！");
					} else if (!passWord2.equals(passWord3)) {
						JOptionPane.showMessageDialog(null, "两次密码输入不一致，请重新输入！");
					} else {
						try {

							
							conn = DBconnected.getConnection();
							
							stam = conn.createStatement();
							
							
							String sql1="select * from users where userName='"+userName.getText()+"'";
				            
				            ResultSet rs = stam.executeQuery(sql1);  
				            if (rs.next()) {
				            	JOptionPane.showMessageDialog(null, "用户名已存在，请修改！");
							}else {
								String sql2 = "insert into users values('" + userName.getText() + "','" + passWord3
										+ "')";
								
								stam.execute(sql2);
								JOptionPane.showMessageDialog(null, "注册成功！");
								dispose(); 
								SwingMain.login();
							}
				            
							
						} catch (Exception e1) {
							e1.printStackTrace();
						} finally {
							DBconnected.result(conn, stam);
						}
					}

				}

			}
		});

		panel.add(btn1);

		btn2 = new JButton("退  出");
		btn2.setFont(font3);
		btn2.setBounds(200, 210, 90, 35);
//	     btn4.setIcon( new ImageIcon(Login.class.getResource("/images/exit.png")));
		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == btn2) {

					dispose();
				}

			}
		});

		panel.add(btn2);
		setContentPane(panel);

	}

}
