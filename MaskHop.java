package ex01;

import java.util.LinkedList;

// クラスの概要: マスクを作ります．

public class MaskHop {

	private int nodeName = 0;//呼びだしたノード
	private int nodes = 0;//いくつのノード作る予定か
	private int nodeSplit = 0;//分割数
	private double ipSplit = 0;//分割単位
	private int times = 2;//ノードの段数
	private LinkedList<String> MaskList = new LinkedList<String>();//これは作っておいてあるだけmakeの中で作るべきすべてに適応されてしまう。
	private LinkedList<Integer> MaskLists = new LinkedList<Integer>();
	
	
	public MaskHop(int Names, int nodes, int splits, int times){
		//コンストラクタ　	
		//nodeはsplitsで割り切れる値でなければならない
		this.nodeName = Names;//ノードの名前
		this.nodes = nodes;//総ノード数
		this.nodeSplit = splits;//分割単位
		//this.ipSplit = Math.round((double)this.nodes / (double)this.nodeSplit);
		this.ipSplit = splits;
		this.times = times;//分割回数
	}

	public LinkedList<String> makeMap(int Name){
		//maskを生成する
		LinkedList<String> MaskList = new LinkedList<String>();
		LinkedList<Integer> tmpList = new LinkedList<Integer>();
		this.nodeName = Name;
		//int nameid = this.nodeName/(int)this.ipSplit;//何番目のグループか調べる
		//System.out.println((int)this.ipSplit*2);
		int test = (int)this.nodes*2;
		int oldgrop = -1;
		System.out.println();
		for(int i=0; i<test; i++){
			MaskList.add(i+"");
		}

		for (int x=1; x<times; x++){
			int nameid = (this.nodeName/((int)this.ipSplit * x));//段数におおじてその段数の中でのグループidを出す
			//System.out.println("nameid");
			//System.out.println(nameid);

			for(int j=0; j<test; j++){
				int temp = (j/((int)this.ipSplit*2*x));//何番目のグループのノードか調べる
				if (oldgrop != temp){
					oldgrop = temp;
					tmpList.add(j);
				}

				if(temp == nameid){
					String maskint = MaskList.get(j);

					MaskList.set(j,maskint);
				
				}else{
					int maskint = temp * ((int)this.ipSplit*2*x);
					MaskList.set(j,maskint+"");
				}	
			}
		}
		return MaskList;
	}			
}