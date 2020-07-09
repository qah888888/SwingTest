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
		Container conn = jf.getContentPane(); // �õ����ڵ�����
		conn.setLayout(null); // ���ò��ַ�ʽΪ�� Ҳ���Ǿ��Բ���
		jf.setSize(1050, 800);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// ����������壬ָ������Ϊ null����ʹ�þ��Բ���
		JPanel panel = new JPanel(null);
//		panel.setPreferredSize(new Dimension(1050, 90));
		panel.setBounds(0, 0, 1050, 90);
		panel.setBackground(Color.decode("#B8BBDE"));
		jf.add(panel);

		// ������ť
		JButton btn01 = new JButton("�û�����", new ImageIcon(ClassLoader.getSystemResource("images/userManage.png")));
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
		// ���ð�ť������
		btn01.setLocation(5, 5);
		// ���ð�ť�Ŀ��
		btn01.setSize(100, 80);
		btn01.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		panel.add(btn01);

		// ������ť
		JButton btn02 = new JButton("��������", new ImageIcon(ClassLoader.getSystemResource("images/topo.png")));
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

		// ���ð�ť�Ľ���(����Ϳ��)�����ð�ť������Ϊ(50, 100)�����Ϊ 100, 50
		btn02.setBounds(110, 5, 100, 80);
		btn02.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		panel.add(btn02);

		// �����������
		JPanel jPanel2 = new JPanel(null);
		jPanel2.setBackground(Color.black);
		jPanel2.setBounds(223, 100, 800, 646);
		jf.add(jPanel2);

		//����������
		JPanel jPanel3 = new JPanel();
		jPanel3.setBackground(Color.white);
		jPanel3.setBounds(0, 100, 210, 646);
		jf.add(jPanel3);
		
		// ����һ�� JList ʵ��
		JList<String> list = new JList<String>();
		// ����һ����ѡ��С
		list.setPreferredSize(new Dimension(200, 100));
		// dan
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// ����ѡ�����ݣ��ڲ����Զ���װ�� ListModel ��,��̬�������
//		list.setListData(new String[] { "�豸1", "�豸2", "�豸3", "�豸4" });
		//��̬�������
		DefaultListModel dlm = new DefaultListModel<String>();
		dlm.addElement("�豸1");
		dlm.addElement("�豸2");
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
		dlm.addElement("�豸3");
//		dlm.remove(0);
		list.setModel(dlm);

		// ���ѡ��ѡ��״̬���ı�ļ�����
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
//				// ��ȡ��ѡ�е�ѡ������				
//				int selectedIndex = list.getSelectedIndex();				
//				// ��ȡѡ�����ݵ� ListModel
//				ListModel<String> listModel = list.getModel();
				
				//valueChanged()�������Ǳ�ִ������,�����ִ��һ��,����ͷ�ִ��һ�Ρ�
   				//����ע����valueChanged()������ӵĲ������������ִ�л�Ӱ�����Ļ�,������JList.getValueIsAdjusting()�жϡ�
				//�������getValueIsAdjusting() ����True. ����ͷ�,getValueIsAdjusting() ����False��
				if (list.getValueIsAdjusting()) {
					//��ȡѡ�е�����ֵ
					String selectedValue = list.getSelectedValue();
					System.out.println("ѡ�У�" +selectedValue);
				}
				
		
			}
		});
		// ����Ĭ��ѡ����
		list.setSelectedIndex(0);
		// ��ӵ������������
		jPanel3.add(list);

		
		
		// ��ʾ����
//		jf.setContentPane(panel);
		jf.setVisible(true);

	}
}
