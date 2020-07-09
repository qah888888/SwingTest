import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.ComponentUI;

import org.jb2011.lnf.beautyeye.ch19_list.BEListUI;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

public class MainPanel {
	public static JFrame jf;

	public void getMainPl() {
		jf = new JFrame();
		Container conn = jf.getContentPane(); // 得到窗口的容器
		conn.setLayout(null); // 设置布局方式为空 也就是绝对布局
		jf.setSize(1050, 800);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// 创建条形面板，指定布局为 null，则使用绝对布局
		JPanel panel = new JPanel(null);
//		panel.setPreferredSize(new Dimension(1050, 90));
		panel.setBounds(0, 0, 1050, 90);
		panel.setBackground(Color.decode("#B8BBDE"));
		jf.add(panel);

		// 创建按钮
		JButton btn01 = new JButton("用户管理", new ImageIcon(ClassLoader.getSystemResource("images/userManage.png")));
		btn01.setHorizontalTextPosition(SwingConstants.CENTER);
		btn01.setVerticalTextPosition(SwingConstants.BOTTOM);

		btn01.setBorderPainted(false);
		btn01.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new UserManager().changePassword();
				jf.setEnabled(false);
			}
		});
		// 设置按钮的坐标
		btn01.setLocation(5, 5);
		// 设置按钮的宽高
		btn01.setSize(100, 80);
		btn01.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		panel.add(btn01);

		// 创建按钮
		JButton btn02 = new JButton("网络拓扑", new ImageIcon(ClassLoader.getSystemResource("images/topo.png")));
		btn02.setHorizontalTextPosition(SwingConstants.CENTER);
		btn02.setVerticalTextPosition(SwingConstants.BOTTOM);

		btn02.setBorderPainted(false);
		btn02.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				new Diagram();
				new NetworkTopo().getNetTopo();
				jf.setEnabled(false);
			}
		});

		// 设置按钮的界限(坐标和宽高)，设置按钮的坐标为(50, 100)，宽高为 100, 50
		btn02.setBounds(110, 5, 100, 80);
		btn02.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		panel.add(btn02);

		// 创建播放面板
		JPanel jPanel2 = new JPanel(null);
		jPanel2.setBackground(Color.black);
		jPanel2.setBounds(223, 100, 800, 646);
		jf.add(jPanel2);

		//创建左侧面板
		JPanel jPanel3 = new JPanel();
		jPanel3.setBackground(Color.white);
		jPanel3.setBounds(0, 100, 210, 646);
		jf.add(jPanel3);
		
		// 创建一个 JList 实例
		JList<String> list = new JList<String>();
		// 设置一下首选大小
		list.setPreferredSize(new Dimension(200, 100));
		// dan
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// 设置选项数据（内部将自动封装成 ListModel ）,静态添加数据
//		list.setListData(new String[] { "设备1", "设备2", "设备3", "设备4" });
		//动态添加数据
		DefaultListModel dlm = new DefaultListModel<String>();
		dlm.addElement("设备1");
		dlm.addElement("设备2");
//		dlm.addListDataListener(new ListDataListener() {
//			
//			@Override
//			public void intervalRemoved(ListDataEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void intervalAdded(ListDataEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void contentsChanged(ListDataEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
		dlm.addElement("设备3");
//		dlm.remove(0);
		list.setModel(dlm);

		// 添加选项选中状态被改变的监听器
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
//				// 获取被选中的选项索引				
//				int selectedIndex = list.getSelectedIndex();				
//				// 获取选项数据的 ListModel
//				ListModel<String> listModel = list.getModel();
				
				//valueChanged()方法总是被执行两次,鼠标点击执行一次,鼠标释放执行一次。
   				//所以注意在valueChanged()里面添加的操作，如果两次执行会影响结果的话,可以用JList.getValueIsAdjusting()判断。
				//鼠标点击，getValueIsAdjusting() 返回True. 鼠标释放,getValueIsAdjusting() 返回False。
				if (list.getValueIsAdjusting()) {
					//获取选中的内容值
					String selectedValue = list.getSelectedValue();
					System.out.println("选中：" +selectedValue);
				}
				
		
			}
		});
		// 设置默认选中项
		list.setSelectedIndex(0);
		// 添加到内容面板容器
		jPanel3.add(list);

		
		
		// 显示窗口
//		jf.setContentPane(panel);
		jf.setVisible(true);

	}
}
