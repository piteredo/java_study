package java_study.generics;

import java.util.*;

class Main {
	public static void main(String[] args){

		//ジェネリック


		/*　用語
		・原型　List　パラメータの無いジェネリクス(？)非推奨
		・仮型パラメータ　<E>  文字は自由だが、T(type), R(return), E(element) が主
		・実型パラメータ　<String>　型がはっきりしてるパラメータ？

		・ジェネリック型　List<E>  パラメータが仮型の型
		・パラメータ化された型　List<String>  実型パラメータを含む型

		・境界型パラメータ　<E extends Number>  境界(上限・下限)が設定されたパラメータ・Eは１種類に確定する
		・非境界ワイルドカード型　List<?>  なんでも良い・その都度違う型のパラメータに対応できる
		・境界ワイルドカード型　List<? super Integer>  境界(上限・下限)が設定されたワイルドカード
		*/



		/*　変性
		ジェネリクス型　X<T>　において、
		型 A extends 型 B のとき、 X<A> extends X<B> が成り立てば XはパラメータTについて　共変　という
		型 A extends 型 B のとき、 X<B> extends X<A> が成り立てば XはパラメータTについて　反変　という
		どちらも成り立たないときは XはパラメータTについて　不変　という

		Java の場合すべて 不変 である。
		Integer extends Number だろうが、　List<Integer> extends List<Number> な訳ではない。
		*/
		Number a = Integer.valueOf(10); //は成り立つけど
		List<Number> b = new ArrayList<>();
		List<Integer> c = new ArrayList<>();
		//b = c; //は成り立たない。リスト同士は親子じゃない

		//配列は　共変
		Object[] d;
		Integer[] e = {1,2,3,4,5};
		d = e; //これが成り立つ Super = Sub [共変]
		//d[0] = "String"; //Object配列だから String を入れようとしてもコンパイル通る。ランタイムでエラー。



		//ジェネリクスのクラスはこうなってる
		class ContainerA<T extends Number> {
			private T value;
			public ContainerA(T value) {
				this.value = value;
			}
			public T get() {
				return value;
			}
		}
		//コンパイル時に型が確定して、仮型情報は消去される
		//コンパイル後はこうなる
		//上限境界の型(↑の場合Number)があてがわれる。境界なければ Object型 になる
		class ContainerB {
			private Number value;
			public ContainerB(Number value) {
				this.value = value;
			}
			public Number get() {
				return value;
			}
		}
		//なので、実行時に仮型情報を取得することはできない
		// return new T(); 仮型のインスタンス化はできない
		// new T[]; 仮型の配列は作れない
		// new ArrayList<T>(); でも仮型パラメータとしてならインタンス生成できる


		wildB();
		wildC();
		pecs();

		//ワイルドカード (非境界)
		List<String> strList = new ArrayList<>();
		strList.add("ff");
		List<Integer> intList = new ArrayList<>();
		System.out.println(  contains(strList, "a")  ); //Stringパラメータ
		System.out.println(  contains(intList, 3)  ); //Integerパラメータ
		test(strList);
	}
	// 仮型パラメータを ? にすることで、その都度任意のパラメータを渡せる
	public static boolean contains(List<?> list, Object o) {
		//list.add(o); ただし"要素型が不定"ということなので、listのメソッドは呼べない
		return list.contains(o);
	}
	public static void test(List<?> list) {
		Object o = list.get(0); //"要素型不定"なので get の帰り値型は Object になる
		//String s = list.get(0); コンパイルエラー
		//というか get はできるんかい
	}


	//ワイルドカード (上限境界)
	public static void wildB() {
		//List<? extends T> Tを親に持つ任意の型パラメータならOK
		//なのでパラメータ同士の比較が成立して、ジェネリクスを「共変」にすることができる
		List<Number> numList = new ArrayList<>();
		List<? extends Number> wildCardNumList = new ArrayList<>();
		List<Integer> intList = new ArrayList<>();

		wildCardNumList = intList; //<Integer extends Number> なのでOK 「共変」
		//numList = intList; //List<Number> extends List<Integer> じゃないのでコンパイルエラー
	}


	//ワイルドカード (下限境界)
	public static void wildC() {
		//List<? super T> ?が、Tの親ならOK (ex. <Number super Integer>)
		//パラメータ同士の比較を成立させ、「反変」にできる
		List<Integer> intList = new ArrayList<>();
		List<? super Integer> wildCardIntList = new ArrayList<>();
		List<Number> numList = new ArrayList<>();

		wildCardIntList = numList; //<Number super Integer>, パラメータInteger = パラメータNumberが成り立つ「反変」(？)
		//intList = numList; //これは List<Integer> = List<Number> 無論NG
	}


	//pecs
	public static void pecs() {
		//プロデューサーは <? extends T>
		//消費者は <? super T>
		//？？？？？
	}
}
