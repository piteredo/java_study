package java_study.lambda;

import java.util.*;

class Main {
	public static void main(String[] args){

		//ラムダ式 (関数型インターフェイスの続きもあり

		localClass();
		localClassWithIf();
		noNameClass();
		lambdaExpression();

		exCollectionsSort();
		exStreamApi();
	}

	public static void localClass() {
		//ローカルクラス
		//インナークラス(内部クラス？)との違いとは
		class Local {
			public void sayHello() {
				System.out.println("hello from local class");
			}
		}
		Local local = new Local();
		local.sayHello();
	}

	public static void localClassWithIf() {
		//インターフェイスを実装したローカルクラス
		class Local implements Runnable {
			public void run() {
				System.out.println("hello from runnable");
			}
		}
		Runnable runner = new Local(); //継承親クラスでインスタンス化(ポリモーフィズム/多態性)
		runner.run();
	}

	public static void noNameClass() {
		//無名クラス
		//インターフェイスを実装したローカルクラスの、宣言を省略できる
		//インターフェイスのインスタンスを生成してるように見えるけど、実際は無名クラスが省略された形
		Runnable runner = new Runnable() { //ここでインスタンス化完了してる
			public void run() {
				System.out.println("hello from runnable(noName)");
			}
		}; //(式)
		runner.run();
	}

	public static void lambdaExpression() {
		//ラムダ式
		/*
		↑の無名クラスから
		・new Runnable(){}
		・public void run
		を省略した形
		*/
		Runnable runner = () -> { System.out.println("hello lambda"); };
		runner.run();

		/*
		つまり、インターフェイスを実装したインスタンスを生成する式　である
		"	()->{}"	これが式である
		*/

		//メソッドの引数としてラムダ式(インターフェイスをインスタンス化させる式)を渡すこともできる
		method(  ()->{ System.out.println("hello lambda from arg"); }  );
	}
	public static void method(Runnable r){ //ここでインスタンス化され、
		r.run(); //ここで実行
		//つまり、メソッドが単一のインターフェイスということが前提
	}



	//ラムダ式を使ったソート
	public static void exCollectionsSort() {
		/*	Collections.sort(List<T>, Comparator<? super T>);
		java.util パッケージの Collections クラス
		この sort メソッドの引数として、
		第一引数　List<T>　(ソートするリスト)
		第二引数　ソート方法　を渡す

		この "ソート方法" に関数型インターフェイスである Comparator インターフェイスの
		int compare(s1, s2) メソッドを実装したインスタンスを指定する

		compareメソッドは引数両者を比較し int を返す → つまり BiFunction<s1, s2, int> っぽい(？)
		if s1 > s2 | R=> 0より大きい値
		if s1 == s2 | R=> 0
		if s1 < s2 | R=> 0より小さい値
		*/

		int[] numbers = {-1, 2, 0, -3, 8};
		List<Integer> numList = new ArrayList<>();
		for(int i : numbers){
			numList.add(i);
		}

		Collections.sort(numList, (a, b)->{  return a - b; }); // a-b 昇順(小さい順
		for(int i : numList){
			System.out.println(i + " ");
		}

		Collections.sort(numList, (a, b)->{  return b - a;  }); // b-a 降順(大きい順
		for(int i : numList){
			System.out.println(i + " ");
		}

		Collections.sort(numList, (a, b)->{  return a*a - b*b;  }); // a*a-b*b 絶対値昇順
		for(int i : numList){
			System.out.println(i + " ");
		}
		System.out.println("---");
	}



	//StreamAPI
	public static void exStreamApi() {
		/*
		Collectionインターフェイスにある stream() メソッドを使う
		stream() は自分自身の Stream インスタンスを返すので、そこに色々なメソッドを加えて処理する
		その色々なメソッド、の引数に関数型インターフェイスを使える
		つまり、List.stream().メソット(ラムダ式) でいろいろできる
		*/
		int[] numbers = {-1, 2, 0, -3, 8};
		List<Integer> numList = new ArrayList<>();
		for(int i : numbers){
			numList.add(i);
		}


		// void forEach( Consumer<T> )
		// Listの要素の数だけ Consumer 実行
		numList.stream().forEach( (i)->{System.out.println(i + "");} );
		System.out.println();

		// Stream filter( Predicate<T> )
		// 条件に合わないものを除いた要素のStreamを返す。
		// Stream を返すので、その後に続けてメソッド打てる　=> 中間操作
		numList.stream().filter( (i)->{return i>0;} ) //Stream型 というわけではない模様
										.forEach( (i)->{System.out.println(i + " ");}); //終端操作

		// Stream map( Function<T, R> )
		// 引数をとり、結果を Stream にして返す。これも中間操作
		numList.stream().filter((i)->{return i>0;})
										.map((i)->{return "*" + i;})
										.forEach((i)->{System.out.println(i+" ");});

		// Stream sorted( Comparator<T> )
		// これでもソートできる。中間操作   "sorted"なので注意
		numList.stream().filter((i)->{return i>=0;})
										.sorted((i1, i2)->{return i1-i2;})
										.map((i)->{return "*" + i;})
										.forEach((i)->{System.out.println(i + "");});
	}
}
