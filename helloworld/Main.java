package java_study.helloworld;
//package名は小文字のみ、単語間もすべて連結する (java_study も javastudy に)

public class Main {
	//実行時に引数を渡せる。String の配列で渡ってくる。
	//引数なくても空の配列を受け取ってるので、String[] args は必須
	public static void main(String[] args){

		System.out.println("Hello World!");
		//System.out は PrintStream型 → の println メソッド
		//System クラスはインスタンス化できない (どのように？)

		System.out.println();
		//引数なしの println() サポートされてるので純粋な改行に使える ("")のような空文字不要
	}
}

//実行時は class path 以降から書く → java java_study.helloworld.Main
