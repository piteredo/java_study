package java_study.array;

public class Main {
	public static void main(String[] args){


		//配列
		//オブジェクトである。 new で初期化する
		//配列の変数は hashCode というもの。アドレスとは違う模様(下記
		//あとから長さは変更できない。(javascriptのpushのようなものはない
		//？クラスではなく、配列を扱うメソッドは他の色々なクラスに分散している(？

		final int[] a;
		//System.out.println(a); 初期化前のためこれはコンパイルエラー。まだメモリの確保がされてない？
		a = new int[10];
		System.out.println(a); //初期化されたので、hashCode が表示される(下記
		System.out.println(a[0]); //未代入の要素は初期値 0 を表示
		a[0] = 1;
		System.out.println(a[0]); //配列は final でも要素は変えられる



		//初期化いろいろ

		//これはOK=あとからnew 生成
		int[] b;
		b = new int[10];

		//これもOK=宣言と同時に代入
		int[] c = {1, 2, 3, 4, 5};

		//これはNG
		//int[] d;
		//d = {1, 2, 3, 4, 5};

		//これならOK
		//右辺だけで中身ありの配列を定義するときはこの書き方 (？
		int[] d;
		d = new int[]{1, 2, 3, 4, 5};



		//二次元配列
		int[][] g = new int[10][10];
		int[][] h = {{1, 2, 3}, {4, 5, 6}};
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
		exHashCode();
	}

	public static void arrayCopy(){

		//配列のコピー


		//シャローコピー
		//要素の参照情報(アドレス)をコピーするので、中身を変えるとどちらにも反映されてしまう

		//clone()
		//java.lang.Object のメソッド
		//なので戻り値が Object型 なので、配列要素の型にキャストする必要がある
		StringBuffer[] array1 = new StringBuffer[2];
		array1[0] = new StringBuffer("A");
		array1[1] = new StringBuffer("B");
		StringBuffer[] array2 = (StringBuffer[])array1.clone();
		array1[0].replace(0, 1, "C");
		System.out.println(array2[0]); //こっちも C になってしまう シャローコピー
		System.out.println("--");

		//arraycopy()
		//java.lang.System のメソッド
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
		System.arraycopy(from, 2, to, 4, 5);
		from[6].replace(0, 1, "8");
		for(StringBuffer i : to){
			System.out.println(i); //こっちも 7 が 8 になってしまう シャローコピー
		}
		System.out.println("--");


		//ディープコピー
		//forループで、各要素のインスタンス新たにを生成する形でコピーすれば完全なコピーになる
		StringBuffer[] deepFrom = {
			new StringBuffer("abc"),
			new StringBuffer("efg")
		};
		StringBuffer[] deepTo = new StringBuffer[2];
		for(int j=0; j<deepFrom.length; j++){
			deepTo[j] = new StringBuffer(deepFrom[j]); //toStrings 有り無しの違いは？
		}
		deepFrom[0].replace(0, 2, "ggg");
		System.out.println(deepTo[0]); //コピー元の影響を受けない　ディープコピー
	}

	public static void exHashCode() {

		// hashCodeに関するメモ

		// アドレスではない
		// 要素を元に生成された暗号(？)のようなもの。数値。
		// ハッシュ値を求めて返すのがハッシュ関数 「hashCode()」
		// 同じ値のものには必ず同じハッシュ値を返す。しかし違う値のものにも同じハッシュ値を返すことがある。
		// なので、equals での比較が true のとき、各要素の hashCode は同値でなくてはならない
		// しかし、equals の比較が false のとき、hashCode の値が異なるという訳ではない (ハッシュ値を根拠に比較している訳ではない(？)

		String[] ab = new String[10];
		System.out.println( ab ); // [L + クラス名(java.lang.String); + @ + hashCode(16進数) を表示
		System.out.println( ab.hashCode() ); // java.lang.Object のメソッド。int値で hashCode を返す

		String lolo = "gggg";
		StringBuffer gogo = new StringBuffer("gggg");
		System.out.println(lolo.hashCode());
		System.out.println(gogo.hashCode()); //この時点で２つの要素は違う hashCode

		System.out.println( lolo.equals(gogo) ); //型違いでfalse

		System.out.println( gogo.toString().hashCode() ); //toStringするとhashCodeが lolo と同値に。しかしアドレスは確実に違うはず。
		System.out.println( lolo.equals(gogo.toString()) ); //Stringかつ同じ文字列なので true
	}
}
