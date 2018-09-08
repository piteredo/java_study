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
}
