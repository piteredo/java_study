package java_study.classes;

public final class Main extends SuperClass implements IEx {
	//final をつけたらこのクラスは継承不可
	//クラスのアクセスレベルは public, 無印のみ(無印は同一パッケージからアクセス可)

	//フィールド・メソッドアクセスレベル
	//public どっからでもアクセス可
	//protected 同一クラス内・同一パッケージ内・小クラスからはアクセス可
	//無印(デフォルト) 同一クラス・パッケージのみ可
	//private 同一クラス内のみ可

	//ここがフィールド
	//書かれた変数 => メンバ変数
	static int a = 1; //クラス変数
	int b = 1; //インスタンス変数

	//定数
	static final int TEISUU = 1;

	//こっからメソッド
	public static void main(String[] args) {
		//メソッド内の変数 => ローカル変数
		final int teisuuB = 1; // finalでもローカル変数は定数とは言わない、のでloweCamelCase

		//クラスメソッドは static を付ける
		//クラスメソッドからはクラス変数が呼べる

		//クラスメソッドからはクラスメソッドしか呼べない
		//インスタンスメソッド呼びたいなら、自身をインスタンス化する
		Main self = new Main();
		self.instanceMethod();
		self.run();
	}

	private void instanceMethod(){
		System.out.println("instanceMethod");
	}

	//インターフェイスの実装は public
	public void run(){
		System.out.println("Interface method");
	}

	//
	public void methometho() {

	}

	//内部クラス書く(statice/dynamic)
}

//抽象クラスはインスタンス化できない
abstract class SuperClass extends SuperSuper {
	SuperClass () {
		//コンストラクタ
		//アクセスレベル・戻り値をはしょれる
		//不要なら書かなくていい

		//オーバーロードしてる別のコンストラクタを this(args); で呼べる。必ずメソッドの先頭で呼ぶ
		this(333); //引数の型が合う別のコンストラクタをさがす

		//コンストラクタは継承しないので、親のコンストラクタを呼びたいときは super(args); で呼ぶ
		//super();
	}

	SuperClass (int n) {
		System.out.println(n);
	}

	//抽象メソッド(継承クラスで実装する)
	abstract public void methometho();

	//abstractクラスは実装メソッドがあっても良い
	@Override //アノテーションは書く(google規約) Oは大文字
	public void exMethod () {
		System.out.println("orverride method");
	}
}

class SuperSuper {
	SuperSuper () {
		System.out.println("SuperSuper");
	}

	public void exMethod () {
		//System.out.println("");
	}
}


interface IEx {
	void run(); //メソッドの宣言のみ。実装は継承クラスで行う
}
