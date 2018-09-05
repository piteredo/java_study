package java_study.bitwise_operation;

public class Main {
	public static void main(String[] args){

		//ビット演算
		//ビット位置は右から数える(0～nビット目)
		//２進数の数ではなくて、８bit１組(1byte=1octet)のフラグのON/OFF制御として使う
		//各演算子は 同じビット目同士を比べた結果がその各ビットごとに帰ってくる形

		//AND &
		//どちらも１なら１、片方でも０の場合は０を返す
		int a = Integer.parseInt("11110000", 2);
		int b = Integer.parseInt("10100000", 2);
		int c = a & b; //int として返ってくる
		System.out.println(Integer.toBinaryString(c)); //10100000

		//OR |
		//どちらかが１なら１、両方０なら０を返す
		int d = Integer.parseInt("11110000", 2);
		int e = Integer.parseInt("10101010", 2);
		int f = d | e;
		System.out.println(Integer.toBinaryString(f)); //11111010

		//XOR ^
		//どちらか片方が１(=違う箇所)は１、両方０/両方１の場合は０を返す
		//= 比較して違う箇所を１として返す
		int g = Integer.parseInt("11110000", 2);
		int h = Integer.parseInt("10101010", 2);
		int i = g ^ h;
		System.out.println(Integer.toBinaryString(i)); //01011010

		//NOT ~
		//すべてのビットの数値を反転させる 1101=>0010
		int j = Integer.parseInt("01010101", 2);
		int k = ~j;
		String l = Integer.toBinaryString(k);
		System.out.println(l.substring(l.length()-8));
		//String じゃなく２進数に変換する手はないのか？？

		bitFlag();
		bitShift();
	}


	public static void bitFlag(){
		//メモリ節約のため等、複数のブール型のセットとして使う
		//ビットで重ならないように、２の累乗でフラグをセットする

		//[フラグの定義]　1byte データ
		//0b で２進数代入できた(1octetごとにアンダーバーでつないでいい)。
		System.out.println(0b0000_1000);
		//でも 0x のが視覚的にベターらしい
		final byte BIT_FLAG_1 = 0x0001; //0b0000 0001
		final byte BIT_FLAG_2 = 0x0002; //0b0000 0010
		final byte BIT_FLAG_4 = 0x0004; //0b0000 0100
		final byte BIT_FLAG_8 = 0x0008; //0b0000 1000

		//[変数にフラグを立てる]
		int flag = BIT_FLAG_1 | BIT_FLAG_4; //0b0000 0101 ２箇所にフラグが立つ
		System.out.println(Integer.toBinaryString(flag)); // 101

		//[値を抽出する]
		boolean boo = (flag & BIT_FLAG_2) == BIT_FLAG_2; //flag に BIT_FLAG_2が立ってるか(どちらも１か)確認できる
		System.out.println(boo);
	}


	public static void bitShift(){
		//[ 左シフト ] a<<3 => aを左に３ビットシフト
		//２進数なので、３ビット左＝ 2^3 倍の数になる
		//0001(1) => 1000(8)
		//空いた右側には 0 が詰められる / 溢れた左は切り捨て
		int a = 1; //0b0001
		System.out.println(a<<3); //0b1000 = 8
		int b = 1 << 3; //同義
		System.out.println(b);


		//[ 右シフト(符号あり) ] a>>2 => aを右に２ビットシフト
		//一番左(符号bit)が0の場合は、空いた左側は0で埋める  0100 => 0001
		//一番左が1の場合は空いた左側は全て１で埋める  1000 =>1110
		//これによって負の整数は符号を維持したまま数値だけ半分^nにできる(？ / 以下検証)
		//---
		//負の符号あり整数は 0b1111_1111 が -1 つまり負数の中で最大として、
		//以降減っていく書き方をする 0b1111_1110(-2) / 0b1000_0001(-127)
		//0b1111_1011(-5) >>2 => 0b1111_1110(-2)
		//左が1で埋まることによってフラグとしてはOFFの状態なので、数値を維持できる

		//負の符号つき整数は 0がON/1がOFF ２進数 -1 の数になる

		byte c = 0b0010_1000;
		System.out.println(Integer.toBinaryString(c >> 2)); //1010
		byte d = -127;
		System.out.println(d >> 2); //0b1000_0001 => 0b1110_0000 (-32)


		//[ 右シフト(符号なし) ] a>>>2 (>が３つ)
		//符号の維持などなく、全部右にシフト。左は０で埋められる
		//10進数での数値変化の目的ではなく、純粋なフラグ管理として使う目的
		int e = -0b0010_0000; //符号付きで指定できないのか…
		System.out.println(e);
		e = ~Integer.parseInt("00100000", 2) + 1; //これで一応同義か…
		System.out.println(Integer.toBinaryString(e));
		System.out.println(Integer.toBinaryString(e>>>3));


		//シフト演算すると元の型関係なくint型として返ってしまうので、intかlongで計算する
	}
}
