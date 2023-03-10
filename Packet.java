package ex01;


// クラスの概要:必要な変数の定義し、値を取得出来るようにする
 

public class Packet{

    public String addressIP;     //宛先IP
    public String senderIP;      //送信元IP
    public String date;          //データ
    public int Packet = 0;       //ホップ数
    public long timestamp = 0;   //ノードに入った時間
    public long oldtimestamp = 0;//経過時間


    //宛先IPの値を取得するメソッド
    public String getAddressIP(){
       return addressIP;
    }

    //宛先IPを設定するメソッド
    public void setAddressIP(String addressIP){
      this.addressIP = addressIP;
    }


    //送信元IPの値を取得するメソッド
    public String getSenderIP(){
        return senderIP;
    }

    //送信元IPを設定するメソッド
    public void setSenderIP(String senderIP){
        this.senderIP = senderIP;
    }


    //データの値を取得するメソッド
    public String getDate(){
        return date;
    }

    //データを設定するメソッド
    public void setDate(String date){
        this.date = date;
    }


    //パケットの値を取得するメソッド
    public int getPacket() {
        return Packet;
    }

    //パケットを設定するメソッド
    public void setPacket(){
        this.Packet += 1;
    }


    //ノードに入った時間の値を取得するメソッド
    public long getTimestamp() {
        return timestamp;
    }

    //ノードに入った時間を設定するメソッド
    public void setTimestamp(long intime){
        this.timestamp = timestamp + intime;
    }


    //経過時間の値を取得するメソッド
    public long getOldtimestamp() {
        return oldtimestamp;
    }
    
    //経過時間を設定するメソッド
    public void setOldtimestamp(long oldtimestamp){
        this.oldtimestamp = oldtimestamp;
    }    
}