package java_study.controlflow;

public class Main {
	public static void main(String[] args){

		//制御文 (抜粋)


		//do while
		//while の条件に関わらずまず１回 do の中身を実行する
		int i = 6;
		do{
			System.out.println(i);
		}	while (i <= 5);


		//拡張for文
		//配列・コレクションの中身を順番に参照する。javascript の for in
		int[] numbers = {2, 4, 6, 8, 10};
		for(int n : numbers){
			System.out.println(n);
		}


		//break文・continue文
		//指定したラベルの制御文まで抜けることが出来る
		//指定なければ最も内側の制御文だけ抜ける
		//
		//break文は switch for while do_while を[終了]させる
		//continue文は for while do?while [その回の途中でスキップして次回ループ]へ
		int[][] xy = {{2, 3, 4, 5}, {6, 7, 8, 9}};
		for(int[] x : xy){
			for(int y : x){
				if(y == 4){
					continue;
					//ラベル無いので一番内側だけから抜ける
					//continue [0][4]を実行する(5が表示)
					//break なら[0][4]も行わず[1][0]へ(5は非表示)
				}
				System.out.println(y);
			}
		}

		here: //抜けたいループのすぐ外側にラベル
		for(int[] x2 : xy){
			for(int y2 : x2){
				if(y2 == 4){
					continue here;
					//ラベルがあるので２重ループからまず出る
					//break [終了]なので出たままおしまい
					//concinue [ラベル直下のループを繰り返すので] [1][0]から再開 (4,5だけ非表示)
				}
				System.out.println(y2);
			}
		}


		//三項演算子
		//boolean ? trueの場合の式 : falseの場合の式
		int nn = 80;
		String result = nn>50 ? "OK" : "NG"; // データ型は揃えること
		System.out.println(result);

		int jj = 80;
		String result2;
		if(jj>50){ // これと同義
			result2 = "OK";
		}
		else{
			result2 = "NG";
		}
		System.out.println(result2);


		ex();
	}

	public static void ex(){
		//return 文
		//void のメソッドは、値を返さない return 文で締められている
		//ただしこれは省略可能なので、実際は書かなくて良い
		return;
	}
}
