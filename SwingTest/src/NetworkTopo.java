import javax.swing.JLayeredPane;

public class NetworkTopo {

	public void getNetTopo() {
		DrawGraph d = new DrawGraph("网络拓扑",new MyMouseAction());
		JLayeredPane p = d.createPane();
//		JLayeredPane p1 = d.createPane();
		d.setCurrentPane(p);
		
		TopoNode node1 = new TopoNode(0,"交换机");
//		node1.setXY(100, 200);
		node1.setInfo("111111");
		d.addTopoData(node1,7,10);
		
		TopoNode node2 = new TopoNode(1,"路由器");
		node2.setInfo("222222");
		d.addTopoData(node2,7,6);
		
		TopoNode node3 = new TopoNode(2,"终端1");
		node3.setInfo("111111");
		d.addTopoData(node3,3,2);
		
		TopoNode node4 = new TopoNode(2,"终端2");
		node4.setInfo("222222");
		d.addTopoData(node4,9,3);
		
		TopoNode node5 = new TopoNode(2,"终端3");
		node5.setInfo("333333");
		d.addTopoData(node5,5,14);
		
		TopoLink tLink = new TopoLink("3", node1, node2);
		tLink.setThickness(2);
		d.addTopoData(tLink);
		
		TopoLink tLink1 = new TopoLink("1", node3, node2);
		tLink1.setThickness(2);
		d.addTopoData(tLink1);
		
		TopoLink tLink2 = new TopoLink("2", node4, node2);
		tLink2.setThickness(2);
		d.addTopoData(tLink2);
		
		TopoLink tLink3 = new TopoLink("4", node5, node1);
		tLink3.setThickness(2);
		d.addTopoData(tLink3);
	}
}
