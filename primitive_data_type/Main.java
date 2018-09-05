package java_study.primitive_data_type;
//package 名は、ローワースネークケース

public class Main {
	public static void main(String[] args) {

		//符号付き整数

		byte a; //8bit
		short b, c; //16bit 2オクテット
		int d; //32bit 4オクテット
		long e = 10; //64bit オクテット
		//大きい桁から(左側から) 第１～４オクテット という様にブロックを指して呼ぶときにも使われる
		//まとめて宣言できる・すぐ値を代入しなくても良い
		//符号(0=正 / 1=負)先頭1bit + 残りのbitで数字
		//0が正の数なので -n～n-1 の範囲になる

		//System.out.println(d); //初期化してない変数を参照するとコンパイルエラー
		a = 024; //0 を加えることで 8進数(octal)として代入できる
		System.out.println(a);
		b = 0xf; //0x を加えることで 16進数(hexadecimal)として代入できる
		System.out.println(b);
		System.out.println(15); //予告なしの整数は int として扱われる
		//System.out.println(99999999999); //よってこれはコンパイルエラー
		System.out.println(99999999999L); //L を付けることで強制的に long型にできる



		//浮動小数点数

		//符号(0正/１負)1bit + 指数部8bit + 仮数部23bit => 32bit(float) / 単精度浮動小数点数
		//符号(0正/１負)1bit + 指数部11bit + 仮数部52bit => 64bit(double) / 倍精度浮動小数点数
		//仮数部 100.11 等の小数点位置をずらして 1.0011 の形にして 1 を省いたもの 0.0011
		//指数部 ずらした小数点を戻すための数。↑で２桁動かしたので 2^2 (10進数なら 10^2)
		//IEEE754形式 (-1)×符号部 × 2^指数部+127(doubleは+1023) × (1+仮数部)
		//指数部が -127された状態からのカウントになるため(オフセットバイナリ) 127を足す

		//つまり 小数点の位置が浮動(定まらず揺れ動く) の小数型

		float f; //32bit
		double g; //64bit
		System.out.println(1.0); //予告なしの小数はサイズ問わず double になる
		System.out.println(1.0F); //float にしたい場合は末尾に F をつける



		//char
		char h = '水'; //16bit の Unicode文字・シングルクォーテーションで囲む！
		System.out.println(h);
		System.out.println(Integer.toHexString((int)h)); //6C34 = 水を確認
		char i = 0x6C34;
		System.out.println(i); //16進数からの指定でも "水" を確認

		//boolean
		boolean j; //true or false
		//サイズはVM依存。使うのは1bitだけのはずだけど、区切りが悪いから8bit確保してたり？
		//型変換もキャストもできずやや謎。



		//型変換・キャスト(抜粋)
		//型変換 => 暗黙の型変換処理・拡大変換OK・縮小方向はコンパイルエラー
		//キャスト => 意識的に行う型変換処理・縮小方向で溢れた分は(２進数での)大きい桁が切り捨てられる
		short k = Short.parseShort("0000000100000111", 2);
		System.out.println(k);
		System.out.println((byte)k);
		System.out.println(Integer.toBinaryString((byte)k));//下位８桁のみ残って 00000111=7に
		//0x 0 で16, 8進数は代入できるのに 2進数で楽々代入できる記号はないのか？
		//Byte クラスには toBinaryString がない。この辺りの意図とは？
	}
}
