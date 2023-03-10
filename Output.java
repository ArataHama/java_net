package ex01;

// ファイル出力を行う

import java.io.*;
public class Output {
    // 見出し行作成
    public Output(){
        try {
            // オブジェクト生成
            PrintWriter pt = new PrintWriter(
                             new BufferedWriter(
                             new OutputStreamWriter(
                             new FileOutputStream("C:\\Users\\admin\\Desktop\\javac2\\src\\ex01\\log.csv"), "UTF-8")));

            pt.print("宛先IP");
            pt.print(",");
            pt.print("送信元IP");
            pt.print(",");
            pt.print("ホップ数");
            pt.print(",");
            pt.print("ノードに入った時間");
            pt.print(",");
            pt.print("経過時間");
            pt.println();

            pt.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }   

    // データを記述(追記)
    public void OutputSend(Packet p){
        try{
            FileWriter fw = new FileWriter("C:\\Users\\admin\\Desktop\\javac2\\src\\ex01\\log.csv", true);
            PrintWriter pt = new PrintWriter(new BufferedWriter(fw));

            pt.print(p.getAddressIP());
            pt.print(",");
            pt.print(p.getSenderIP());
            pt.print(",");
            pt.print(p.getPacket());
            pt.print(",");
            pt.print(p.getTimestamp());
            pt.print(",");
            pt.print(p.getOldtimestamp());
            pt.println();

            pt.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    //テスト用logファイル(現在はどのNICに送られているかをlogを送り出している)
    public void testlog(String t){
         try{
            FileWriter fw = new FileWriter("C:\\Users\\admin\\Desktop\\javac2\\src\\ex01\\log_sendpacket.csv", true);
            PrintWriter pt = new PrintWriter(new BufferedWriter(fw));
            int num = Integer.parseInt(t,2);
            pt.print(num);
            pt.println();
        
            pt.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}