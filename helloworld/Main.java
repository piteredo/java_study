package java_study.helloworld;

public class Main {
	public static void main(String[] args){
		System.out.println("Hello World!");
	}
	//実行時は package 名を添える → java java_study.helloworld.Main
	//実行時に引数を渡せる。Stringの配列で渡ってくる。
	//System.out は PrintStream型 → の println メソッド
	//引数なしの println() サポートされてるので純粋な改行に使える ("")不要
	//package 名に大文字使うとエラーがでる(コンパイルできるからエディタのせい？)
}
