package java_study.functionalinterface;

import java.util.function.*;

class Main {
	public static void main(String[] args) {

		/*  関数型インターフェイス
		javaで用意されてる、単一メソッドをもつインターフェイス群
		つまりラムダ式を使うのにもってこいの仕組み
		java.util.function パッケージ

		ジェネリクス型で、それぞれに引数(×個数)・戻り値(×個数)の仮型パラメータ・メソッドが決まっている(ものもある)
		引数・戻り値なくても単一メソッドならOK => ex. Runnable
		*/

		functions();
		exCollectionsSort();
		exStreamApi();
	}

	public static void functions() {
		//いっぱいあるけど、重要ないくつか

		// Function<T, R>
		// T => 引数型 / R => 戻り値型 / メソッドは R apply(T);(Rが戻るという意味 [apply 適用する]
		Function<Integer, String> astarisker = (i)->{ return "*" + i; }; //インスタンス化・(戻り値型は省略済み)
		String str = astarisker.apply(10);
		System.out.println(str);

		// BiFunction<T, T, R>
		//↑の、引数が２つになった版　"Bi"は"２回"という意味(英語)
		BiFunction<Integer, Integer, String> test = (i, j)->{return "*" + (i+j); };
		System.out.println(  test.apply(10, 20)  );


		// Consumer<T>
		//消費者。消費するだけなので(？)戻り値は無い / メソッドは void accept(T); [accept 受領する]
		Consumer<String> buyer = (s)->{ System.out.println(s + "を購入"); }; //(引数の型も省略済み)
		buyer.accept("おにぎり");

		// BiConsumer<T, T>
		//↑の、引数が２引数が２つになった版
		BiConsumer<String, String> buyer2 = (f, g)->{ System.out.println(f + g); };
		buyer2.accept("おにぎり" , "はむかつ");


		// Predicate<T>
		//断定・断言する。T は引数型 / 戻り値はboolean(これが断言？結果？) / メソッドは boolean test(T);
		Predicate<Integer> checker = (i)->{ return i<20; };
		System.out.println(  checker.test(15)  );

		// BiPredicate<T, T>
		BiPredicate<Integer, Integer> adder = (i, j)->{ return (i+j)>30; }; //中の ; 忘れずに
		System.out.println(  adder.test(15, 14)  );
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
