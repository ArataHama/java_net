package ex01;


// クラスの概要: String → int → String(二進数)

public class conversion {

    /**
     * 二進数のStringにするためのメソッドです．
     * @param ad String型のIPアドレス
     */
    public String Conversion(String ad) {

        int num = Integer.parseInt(ad); // 受け取ったStringをint変換
        String s = Integer.toString(num, 2); // 二進数・String変換
        while(s.length() <= 8) { // 桁数を統一
            s = "0" + s;
        }
        return s;
    }
        
}