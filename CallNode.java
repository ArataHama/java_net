package ex01;
import java.util.*;
import java.nio.file.Path;

 // クラスの概要: ノードを呼ぶクラス

public class CallNode {
    // ノードを呼ぶためのLinkedList
    private LinkedList<NIC> nic = new LinkedList<NIC>();

	private LinkedList<RouteInfo> RoutingTable = new LinkedList<RouteInfo>();

	private LinkedList<ARPInfo> ArpTable = new LinkedList<ARPInfo>();
    
    private LinkedList<String> subSetMask = new LinkedList<String>();

    addTable a = new addTable();
    
    Path path = null;



    private MaskHop masks = new MaskHop(0, 100, 2, 3);

	public CallNode(){ 
    
        
    }
    /**
     * ノードを呼びスレッドを起動ためのメソッドです．
     */
    public void run() {
        for(int i=0; i<100; i++){
            RoutingTable = a.getRouteInfos();
            LinkedList<String> subSetMask = new LinkedList<String>();

            subSetMask = masks.makeMap(i);

            LinkedList<NIC> nic = new LinkedList<NIC>();

            LinkedList<ARPInfo> arptable = a.getArpInfos(); 


            ARPInfo info = arptable.get(i*2);
  
            NIC testnic = info.getNICRecord();
            nic.add(testnic);

            info = arptable.get(i*2+1);
            nic.add(info.getNICRecord());
            String name = String.valueOf(i);

            NWCM t = new NWCM(name, nic, RoutingTable, arptable, subSetMask);
            Thread th = new Thread(t);
            th.start();
        }
    }
}