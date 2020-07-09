import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.mysql.cj.exceptions.RSAException; 

public class SwingMain extends JFrame{
	static Connection connection;
	static Statement statement;
	public static JTextField userName;

	public static void main(String[] args) {
		
		 try
		    {
			//
		        BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
		        UIManager.put("RootPane.setupButtonVisible", false);
		       
		        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		        
		      //
		        UIManager.put("TabbedPane.tabAreaInsets"
		            , new javax.swing.plaf.InsetsUIResource(3,20,2,20));
		        
		    }
		    catch(Exception e)
		    {
		        //TODO exception
		    }
		
		
//		login();
		new MainPanel().getMainPl();
//		new Diagram();
		
	}
	

	public static void login() {
		JFrame jf = new JFrame("登录");
		jf.setBounds(250, 400, 320, 270);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		
		JPanel panel01 = new JPanel();
		panel01.add(new JLabel("用户名："));
		userName = new JTextField(15);
		panel01.add(userName);

	
		JPanel panel02 = new JPanel();
		panel02.add(new JLabel("密  码："));
		JPasswordField passwordField = new JPasswordField(15);
		panel02.add(passwordField);

		JPanel panel03 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton loginButton = new JButton("登录");
		JButton registButton = new JButton("注册");
		panel03.add(loginButton);
		panel03.add(registButton);
		

		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
               
                if (userName.getText().length() == 0) {
                	JOptionPane.showMessageDialog(null, "请输入用户名!");
				}else {
					 try {
		                	
							 connection = DBconnected.getConnection();
							 statement = connection.createStatement();
							
							String sql="select * from users where userName='"+userName.getText()+"'";
				            
				            ResultSet rs = statement.executeQuery(sql);  
				            if (!rs.next()) {
				            	JOptionPane.showMessageDialog(null, "用户名不存在，请先注册！");
							}else {
								String sqlpassword = rs.getString("password");
					           
				                if (String.valueOf(passwordField.getPassword()).equals(sqlpassword)) {
				                	     				               
				                	jf.dispose();
				                	new MainPanel().getMainPl();
				               
								}else {
									JOptionPane.showMessageDialog(null, "密码错误，请重新输入！");
								}
							}
				           
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}finally {
							DBconnected.result(connection, statement);
							
						}
				}
             
			}
		});

		registButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jf.dispose();
				new Register().addMan(); 
				
				
			}
		});

		//
		Box vBox = Box.createVerticalBox();
		
		vBox.add(Box.createVerticalGlue());
		vBox.add(panel01);
		vBox.add(panel02);
		vBox.add(panel03);

		jf.setContentPane(vBox);

//		jf.pack();
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);

	}

}









