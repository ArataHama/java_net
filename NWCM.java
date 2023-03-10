package ex01;

import java.util.LinkedList;
import java.util.Random;


// クラスの概要: ノードです．

public class NWCM implements Runnable{
	//ノードである

	private String Hostname;

	private LinkedList<Packet> Recebe = new LinkedList<Packet>();

	private LinkedList<NIC> Nic = new LinkedList<NIC>();

	private LinkedList<RouteInfo> RoutingTable = new LinkedList<RouteInfo>();

	private LinkedList<ARPInfo> ARPTable = new LinkedList<ARPInfo>();
	
	private Output out = new Output();

	private LinkedList<String> mask = new LinkedList<String>();


	//
	//ノードの情報を初期化する
	//Hostname= 名前, Nic= ノードが持っているnic２つ, routingtable= 全ノードのデータ, arptable= パケットの宛先きめのテーブル 
	//

	public NWCM (String name, LinkedList<NIC> nic, LinkedList<RouteInfo> routingtable, LinkedList<ARPInfo> arptable, LinkedList<String> subnetmask){
		this.Hostname = name;
		this.Nic = nic;
		this.RoutingTable = routingtable;
		this.ARPTable = arptable;
		this.mask = subnetmask;
		}
		
	// 受け取ったパケットを入れるためメゾット
	public void getRecebe(Packet p){
		this.Recebe.addFirst(p);
	}
	//パケット作成 
	public Packet MakePacket(){
		Packet p = new Packet();
		NIC id = this.Nic.getFirst();
		String ip = id.getIp();
		System.out.println("--makepaket---");
		System.out.println(ip);

		Random rand = new Random();
		int i = 0;

		i = rand.nextInt(this.RoutingTable.size());

		RouteInfo tempRout = this.RoutingTable.get(i);

		long starttime = System.currentTimeMillis();
		
		p.setAddressIP(tempRout.getNwAddress());
		p.setSenderIP(ip);
		p.setDate(String.valueOf(this.Hostname));
		p.setTimestamp(starttime);
		return p;
	}
	/**
	 * @param p
	 */

	@Override
	public void run(){
		outer:
		while(true){
			//nicの中に来ている受信キューから抜きとる
			Packet packed;//paketto
			try {
				Thread.sleep(0);
				} catch (InterruptedException e) {
				e.printStackTrace();
				}
				//System.out.print("ホスト");
				//System.out.println(this.Hostname);
			
			for(int i=0; i<this.Nic.size();i++){
				//キューから抜き取る
				LinkedList<Packet> kyu = this.Nic.get(i).kyu();
				if(kyu.size() > 0){
					System.out.println("nya"+this.Hostname);
					this.Recebe.addAll(kyu);
					this.Nic.get(i).kyu_clear();
					System.out.println(this.Recebe);
					System.out.println("------------------------getpacket-----------------------------------");
					//out.testlog(this.Hostname);
					System.out.println(this.Hostname+"Host");
					System.out.println(kyu);			
				}
			}
				
			if (this.Recebe.size() > 0){
				packed = this.Recebe.getFirst();//作る
				this.Recebe.removeFirst();
				System.out.println("------------------------------------------------------------------------------------------------------------");
				//packed.setPacket(); 

				for(int j=0; j<this.Nic.size();j++){
					String ip = packed.getAddressIP();
					
					NIC tmp_nic = this.Nic.get(j);
					//System.out.println(tmp_nic.getIp());
					//System.out.println("--up-------dn--");
					//System.out.println(ip);
					
					if(this.Hostname.equals("0")){
					//	out.OutputSend(packed);	
					}
					if(ip.equals(tmp_nic.getIp())){//一致確認

						System.out.println("---------------------------equal----------------------------------"+tmp_nic.getIp());//------------------------------------
						int numip = Integer.parseInt(packed.getAddressIP(),2);
						System.out.println(numip);
						int numsp = Integer.parseInt(packed.getSenderIP(),2);
						System.out.println(numsp);
						System.out.println(packed.getAddressIP());
						System.out.println(packed.getSenderIP());
						System.out.println(packed.getDate());
						System.out.println("======hop数=====");
						System.out.println(packed.getPacket());
						long endtime = System.currentTimeMillis();
						long starttime = packed.getTimestamp();
						long timeElapsed = endtime - starttime;
						packed.setOldtimestamp(timeElapsed);


						
						out.OutputSend(packed);

						System.out.println("-----------------------dataoutpost-----------------------------------");
						continue outer;
					}
				}
			}else{
				int i= 0;
				Random Rand = new Random();
				i = Rand.nextInt(100);
				if(i >= 99){    ///5割で新しいパケットを生み出すループするたび
					packed = MakePacket();
					try {
						Thread.sleep(500);
					}
					catch (InterruptedException e) {
						e.printStackTrace();//
					}
				}else{
					try {
						Thread.sleep(0);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					continue; //パケットを作らないのなら先頭へ（ここ待たせてもいいかも少々）
				}
			}
			//受信用のキューにパケットが入っているかどうか調べろ
			//合ったらそれから1こもしくはときたまパケットをつくるべ
			
			String ip = packed.getAddressIP();//パケットのIPを取ってくる

			String sendip = new String();//最長一致した宛先およびNWIP

			
			


			
			for(int i=0; i<this.RoutingTable.size(); i++){
				RouteInfo routeInfo = RoutingTable.get(i);
				conversion con = new conversion();
				String NWadresss = routeInfo.getNwAddress();//数字ipv4
				String NextHop = con.Conversion(this.mask.get(i));//テーブルの次の戦争先
				int ttt = Integer.parseInt(NextHop,2);
						//System.out.println(ttt);

				//String NextHop = routeInfo.getNextHop();
				// String longs = new String();
				int len = 0;//長い
				
				//for(int k=0; k<NWadresss.length();k++){
					//String tmp = ip.substring(k,k+1);//一番左の数字から当てはまっているか調べる
					//String nwtmp = NWadresss.substring(k, k+1);
					int numtip = Integer.parseInt(ip,2);
					//System.out.println(numtip);
					int numtnd= Integer.parseInt(NWadresss,2);
					//System.out.println(numtnd);

					int test = 0;

					test = numtip - numtnd;
					if(Math.abs(test) == 0){
						sendip = NextHop;
						System.out.println(numtip);
						System.out.println("wa");
						System.out.println(NextHop);
						break;
					}else{

					}
					/*/if(tmp.equals(nwtmp)){
						len+=1;
						if(longlen < len){
							sendip = NextHop;
							longlen = len;
						}else{
							
						}	
					}else{
						break;
					}/*/
				//}
			}
			for(int k=0; k<ARPTable.size(); k++){
				ARPInfo ain = ARPTable.get(k);
				String Table = ain.getARPIp();//ARPテーブル内の(転送先IP，転送先NICのMACアドレス，出口NICから成るレコード)
				if(Table.equals(sendip)){
					NIC fac = ain.getNICRecord();
					System.out.println("-----send----"+this.Hostname);
					int numn = Integer.parseInt(packed.getAddressIP(),2);
					System.out.println(numn);
					int num = Integer.parseInt(fac.getIp(),2);
					System.out.println(num);
					//System.out.println(sendip);
					//System.out.println("==========================");
					//System.out.println(ain.getMacAddress());
					int numm = Integer.parseInt(sendip,2);
					//System.out.println(fac.getIp());
					System.out.println(numm);
					//packed.setPacket();
					
					out.testlog(sendip);
					System.out.println("----send----");//-------------------------------------------
					fac.send(packed,fac);//この中で（ネクストホップのNICのreceiveメソッドを呼ぶ）(これsendのtonicイランかも)
				}
			}
		}	
	}
}