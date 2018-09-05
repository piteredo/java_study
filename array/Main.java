package java_study.array;

public class Main {
	public static void main(String[] args){


		//配列
		//オブジェクト同様 new で初期化する
		//配列の変数そのものはポインタ
		//javascriptのpushのようなものはない => あとから長さは変更できない

		final int[] a;
		//System.out.println(a); 初期化前のためこれはコンパイルエラー。まだアドレスの位置取りもされてない？
		a = new int[10];
		System.out.println(a); //初期化されたので、アドレス表示される
		System.out.println(a[0]); //初期値 0 が表示
		a[0] = 1;
		System.out.println(a[0]); //配列はfinalでも中身は変えられる



		//これはOK=あとからnew 生成
		int[] b;
		b = new int[10];

		//これもOK=宣言と同時に代入
		int[] c = {1, 2, 3, 4, 5};

		//これはNG
		//int[] d;
		//d = {1, 2, 3, 4, 5};
		//
		//こうやるしかない？
		int[] d;
		d = new int[5];
		d[0] = 1;
		d[1] = 2;
		d[2] = 3;
		d[3] = 4;
		d[4] = 5;


		//二次元配列
		int[][] g = new int[10][10];
		int[][] h = {{1,2,3},{4,5,6}};
		int[][] j = new int[2][]; //上位配列だけ生成して、あとから下位配列を生成もOK
		j[0] = new int[2];
		j[1] = new int[2];


		//length
		//配列の length は属性である。()不要
		//String, StringBufferの length() はメソッドなので()が必要
		int[] e = {2,3,4,5};
		System.out.println(e.length);
		String f = "2345";
		System.out.println(f.length());


		arrayCopy();
	}

	public static void arrayCopy(){

		//配列のコピー


		//シャローコピー
		//各要素の参照情報(アドレス)をコピーするので、中身を変えるとどちらにも反映されてします

		//clone()
		//親クラス java.lang.Object のメソッド
		//なので戻り値が Object型 なので、配列の型にキャストする必要がある
		StringBuffer[] array1 = new StringBuffer[2];
		array1[0] = new StringBuffer("A");
		array1[1] = new StringBuffer("B");
		StringBuffer[] array2 = (StringBuffer[])array1.clone();
		array1[0].replace(0, 1, "C");
		System.out.println(array2[0]); //mutableだと C になってしまう シャローコピー
		System.out.println("--");

		//arraycopy
		//Systemクラスのメソッド
		//arraycopy(コピー元配列, コピー元の開始インデックス, コピー先配列, コピー先の開始インデックス, コピー長さ)
		StringBuffer[] from = {
			new StringBuffer("1"),
			new StringBuffer("2"),
			new StringBuffer("3"),
			new StringBuffer("4"),
			new StringBuffer("5"),
			new StringBuffer("6"),
			new StringBuffer("7")
		};
		StringBuffer[] to = new StringBuffer[10];
		from[6].replace(0, 1, "8");
		System.arraycopy(from, 2, to, 4, 5);
		for(StringBuffer i : to){
			System.out.println(i); //mutableだと 7 が 8 になってしまう シャローコピー
		}
		System.out.println("--");


		//ディープコピー
		//forループで、新たにインスタンスを生成する形でコピーすれば完全なコピーになる
		StringBuffer[] deepFrom = {
			new StringBuffer("abc"),
			new StringBuffer("efg")
		};
		StringBuffer[] deepTo = new StringBuffer[2];
		for(int j=0; j<deepFrom.length; j++){
			deepTo[j] = new StringBuffer(deepFrom[j].toString()); //toStrings 有り無しの違いは？
		}
		deepFrom[0].replace(0, 2, "ggg");
		System.out.println(deepTo[0]); //コピー元の影響を受けない　ディープコピー
	}
}
