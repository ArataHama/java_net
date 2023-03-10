package ex01;

import java.util.LinkedList;

// クラスの概要: 通信（送受信）するをするためのクラスです．

public class NIC {
    // IPアドレス
    public String ipAddress;
    // MACアドレス
    public String macAddress;
    // NICが持つ受信キュー
    public LinkedList<Packet> packets = new LinkedList<Packet>();
    
    public NIC(String ipAddress, String macAddress) {
        this.ipAddress = ipAddress;
        this.macAddress = macAddress;
    }

    /**
     * 次のNICに送信するためのメソッドです．
     */
    public void send(Packet p, NIC toNic) {
       
        toNic.receive(p);
    }
    /**
     * パケットを受け取るためのメソッドです．
     */
    public void receive(Packet p) {
        p.setPacket();
        packets.add(p);
    }
    /**
     * パケットをキューに追加するためのメソッドです．
     */
    public LinkedList<Packet> kyu(){
        return this.packets;
    }
    /**
     * NICクラスの持つキューをクリアする
     */
    public void kyu_clear(){
        this.packets.clear();
    }
    /**
     * IPアドレス
     */
    public String getIp(){
        return this.ipAddress;
    }
    /**
     * MACアドレス
     */
    public String getMac(){
        return this.macAddress;
    }
}