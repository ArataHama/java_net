package ex01;

import java.util.Timer;
import java.util.TimerTask;


/**
 * クラスの概要:スレッドを呼ぶクラス(CallNode)を呼び出す 
 * 5秒でプログラムを強制終了させる
 */

 public class Main{
    public static void main(String[] args) {

        //CallNodeをインスタンス化
        CallNode call = new CallNode();

        //CallNodeのrunを呼び出す
        call.run();

        
        TimerTask task = new TimerTask() {

            public void run(){
                //プログラムを強制終了
                System.exit(0);
            }
    
        };

        Timer timer = new Timer();

        //5秒後にrunを実行
        timer.schedule(task, 5000);
    }
    
 }
 