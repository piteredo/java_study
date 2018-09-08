package java_study.variadic;

class Main {
	public static void main(String... args){ // <= つまりこれでも良い

		//可変長引数
		/*
		引数の数を変動させられる
		型は揃える
		渡った先のメソッドでは配列として扱われる
		複数の引数を渡すときは、最後の引数としてしか渡せない ex(A, B1, B2, B3...)
		*/

		ex("A", 1, 2, 3, 4);
		ex("B", 1, 2, 3, 4, 5, 6, 7);
		ex("C"); // 0でも可 => 空の配列が渡る

		ex2("D", new String[]{"A", "B", "C"}); //参照型の場合配列を渡しても良い・基本型だと配列まるごと１要素にされてしまう
	}

	public static void ex(String label, int... numbers) {
		System.out.println(label + " length = " + numbers.length);
		for(int i : numbers){
			System.out.println(i);
		}
		System.out.println("--");
	}

	public static void ex2(String label, String... numbers) { //配列型で受け取らなくでも配列のまま処理してくれる
		System.out.println(label + " length = " + numbers.length);
		for(String i : numbers){
			System.out.println(i);
		}
		System.out.println("--");
	}
}
