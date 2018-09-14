package java_study.lambda;

import java.util.*;

class Main {
	public static void main(String[] args){

		//ラムダ式
		localClass();
		localClassWithIf();
		noNameClass();
		lambdaExpression();
	}

	public static void localClass() {
		//ローカルクラス
		//インナークラス(=内部クラス)の一種。メソッド内部に存在するクラスがローカルクラス
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
}
