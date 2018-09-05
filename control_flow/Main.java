package java_study.control_flow;

public class Main {
	public static void main(String[] args){

		//制御文(抜粋)


		//do while
		//while の条件に関わらずまず１回 do の中身を実行する
		int i = 6;
		do{
			System.out.println(i);
		}	while (i <= 5);
		System.out.println("--");


		//拡張for文
		//配列・コレクションの中身を順番に参照する。javascriptのfor in
		int[] numbers = {2, 4, 6, 8, 10};
		for(int n : numbers){
			System.out.println(n);
		}
		System.out.println("--");


		//break文・continue文
		//指定したラベルの制御文まで抜けることが出来る
		//指定なければ最も内側の制御文だけ抜ける
		//
		//break文は switch for while do_while を終了させる
		//continue文は for while do?while その回の途中でスキップして次回ループへ
		int[][] xy = {{2, 3, 4, 5}, {6, 7, 8, 9}};
		for(int[] x : xy){
			for(int y : x){
				if(y == 4){
					continue; //ラベル無いので内側だけ抜ける
					//continue なら "5" が出る
					//break なら "5" はでない
				}
				System.out.println(y);
			}
		}
		System.out.println("--");

		here: //抜けるすぐ外側にラベル
		for(int[] x2 : xy){
			for(int y2 : x2){
				if(y2 == 4){
					continue here; //ここにラベル指定
					//break ラベル有るので二重ループおしまい
					//concinue ラベル直下のループを繰り返すので "4, 5" だけ出ない
				}
				System.out.println(y2);
			}
		}


		//三項演算子
		//boolean ? trueの場合の式 : falseの場合の式
		int nn = 80;
		String result = nn>50 ? "OK" : "NG"; //データ型は揃える
		System.out.println(result);
		//
		int jj = 80;
		String result2;
		if(jj>50){ //これと同義
			result2 = "OK";
		}
		else{
			result2 = "NG";
		}
		System.out.println(result2);



		ex();
	}

	//return 文
	public static void ex(){
		return;
		//void のメソッドは、値を返さない return 文で締める
		//そしてこれは省略可能なので、書かなくても良い
	}
}
