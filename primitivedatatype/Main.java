package java_study.primitivedatatype;

public class Main {
	public static void main(String[] args) {
		exInteger();
		exDecimal();
		exChar();
		exBoolean();
		exCast();
	}

	public static void exInteger() {
		//符号付き整数
		//バイナリの一番左 bit を符号(0=正 / 1=負)とすることで、0を中心にした数値範囲を得られる
		//0が整数なので -n ~ n-1 の範囲になる

		// 8bit
		// 1オクテット
		// 0b00000000 == 0x00
		byte a;

		// 16bit
		// 2オクテット (左から、第一オクテット、第二...)
		// 0b00000000_00000000 == 0x00_00
		short b;

		// 32bit
		// 4オクテット
		// 0b00000000_00000000_00000000_00000000 == 0x00_00_00_00
		int d;

		// 64bit
		// 8オクテット
		long e = 10;


		//初期化してない変数を参照するとコンパイルエラー
		//System.out.println(d);

		//0 を頭に加えることで 8進数(octal)として代入できる
		a = 024;
		System.out.println(a);

		//0x を頭に加えることで 16進数(hexadecimal)として代入できる
		b = 0xf;
		System.out.println(b);

		//予告なしの整数は int として扱われる
		System.out.println(15);
		//System.out.println(99999999999); //よってこれはコンパイルエラー (オーバーフロー)
		System.out.println(99999999999L); //Lを付けることで強制的に long型にできるのでこれはOK
	}

	public static void exDecimal() {
		//浮動小数点数
		//小数点の位置が浮動(定まらず揺れ動く)の小数型

		/*
		単精度浮動小数点数 (float | シングルフォーマット)
		符号(0正/１負)1bit + 指数部8bit + 仮数部23bit => 32bit

		倍精度浮動小数点数 (double | ダブルフォーマット)
		符号(0正/１負)1bit + 指数部11bit + 仮数部52bit => 64bit

		仮数部
		100.11 等の小数点位置をずらして 1.0011 の形にして 1 を省いたもの 0.0011

		指数部
		ずらした小数点を戻すための数。↑で２桁動かしたので 2^2 (10進数なら 10^2)

		IEEE754形式
		(-1)×符号部 × 2^指数部+127(doubleは+1023) × (1+仮数部)
		指数部が -127された状態からのカウントになるため(オフセットバイナリ) 127を足す
		*/


		// 32bit
		float f;

		// 64bit
		double g;


		//予告なしの小数はサイズ問わず double になる
		System.out.println(1.0);

		//float にしたい場合は末尾に F をつける
		System.out.println(1.0F);
	}

	public static void exChar() {
		//char
		//16bit の Unicode１文字
		//シングルクォーテーションで囲むこと
		char h = '水';
		System.out.println(h);

		//Unicodeの数値に変換できる
		//6C34 = 水を確認
		System.out.println(Integer.toHexString((int)h));

		//Unicodeの数値で代入もできる
		//16進数からの指定で "水" を確認
		char i = 0x6C34;
		System.out.println(i);
	}

	public static void exBoolean() {
		//boolean
		//サイズはVM依存。使うのは1bitだけのはずだけど、区切りが悪いから8bit確保してたり？
		//型変換もキャストもできない。やや正体不明なやつ
		boolean j; //true or false
	}

	public static void exCast() {
		//型変換・キャスト (抜粋)

		/*
		型変換
		暗黙の型変換処理・拡大変換OK・縮小方向はコンパイルエラー

		キャスト
		意識的に行う型変換処理・縮小方向で溢れた分は(２進数での)大きい桁が切り捨てられる
		*/

		short k = Short.parseShort("0000000100000111", 2);

		//下位８桁のみ残って 00000111=7に
		System.out.println(Integer.toBinaryString((byte)k)); //Byteクラスには toBinaryString がない。
	}
}
