package ex01;

// ルーティングテーブルに各情報を追加する処理

import java.util.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class addTable implements Cloneable{
    private LinkedList<RouteInfo> RouteInfos = new LinkedList<RouteInfo>();
    private LinkedList<ARPInfo> ArpInfos = new LinkedList<ARPInfo>(); 
    Path path = null;
    conversion con = new conversion();
    
    // csvファイルに記述されている情報を各テーブルに追加
    public addTable(){
        //csvファイルを読み込むためのLinkedListの準備
        LinkedList<String> iplist = new LinkedList<String>();
        LinkedList<String> maclist = new LinkedList<String>();
        LinkedList<String> nexthop = new LinkedList<String>();
        System.out.println("te");

        try {
            //ファイル読み込み
            path = Paths.get("C:\\Users\\admin\\Desktop\\javac2\\src\\ex01\\Table1.csv");

            List<String> lines = Files.readAllLines(path, Charset.forName("UTF-8"));
            for(int i=1; i<lines.size(); i++){
                String[] data = lines.get(i).split(",");

                iplist.add(data[0]);
                maclist.add(data[1]);
                nexthop.add(data[2]);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        int test = iplist.size();
        
        for(int i=0;i<test; i++){
            RouteInfo tmp = new RouteInfo();
            tmp.setNwAddress(con.Conversion(iplist.get(i)));
            tmp.setNextHop(con.Conversion(nexthop.get(i)));
            tmp.setSubNetMask(null);
            this.RouteInfos.add(tmp);
        }

        for(int j=0;j<test; j++){
            ARPInfo arp = new ARPInfo();
            NIC nic = new NIC(con.Conversion(iplist.get(j)), con.Conversion(maclist.get(j)));
            arp.setARPIp(con.Conversion(iplist.get(j)));
            arp.setMacAddress(maclist.get(j));
            arp.setNICRecord(nic);
            this.ArpInfos.add(arp);
        }
    }

    public LinkedList<RouteInfo> getRouteInfos(){
        return this.RouteInfos;
    }
    public LinkedList<ARPInfo> getArpInfos(){
        return this.ArpInfos;
    }
    public void addList(LinkedList<String> list){
        for(int x=0;x<200; x++){
            RouteInfo tmp = new RouteInfo();
            String sub = list.get(x);
            tmp.setSubNetMask(sub);
            this.RouteInfos.add(x, tmp);
        }   
    }
}