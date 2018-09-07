package java_study.number; //package名は"_"使わず全て小文字で連結が良いらしい(google規約)

import java.math.BigInteger; //ワイルドカード(*)インポート禁止(google規約)
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.util.Locale;

public class Main {
	public static void main(String[] args) {

		//数値クラス
		//基本数字型６つ＋BigInteger, BigDecimal の８つ
		//すべて Number クラスを親に持つ
		//Big の２つ以外は コンストラクタが非推奨
		//Big の２つは java.math パッケージ


		//BigInteger, BigDecimal
		BigInteger bi = new BigInteger("-20"); //long より大きい整数・小数書き込むと実行時エラー
		BigDecimal bd = new BigDecimal("-10.3"); //大きな10進数(整数・小数)・整数代入時は".0"つかない・浮動小数点数ではない？



		//共通メソッド(Numberクラスに実装されたメソッド)

		// byte|short|int|long|float|doubleValue()
		//呼び出すオブジェクトを指定の基本型に変換
		Integer i = Integer.valueOf(2222);
		byte b = i.byteValue(); //溢れた分は削られる
		System.out.println(b); //-82

		//compareTo()
		//比べる　同値=>0 呼び出し大きい=>正の整数 呼び出し小さい=>負の整数
		//型を揃えないとエラー
		Long a = Long.valueOf(2222);
		Long c = Long.valueOf(1111);
		//System.out.println(  a.compareTo(i)  ); これは型揃ってないのでコンパイルエラー
		System.out.println(  a.compareTo(c)  ); // 1

		//equals()
		//比べる true or false
		//型揃って無くてもOK、同値でも型違いならfalse
		Integer d = Integer.valueOf(2222);
		System.out.println(  d.equals(a)  ); //同値だけど型違いなのでfalse。あとIDEの薄めの警告がでる

		//valueOf(String) Big２つ以外
		//文字列を各数値クラス型に変換して返す。基本型ではない。
		Integer e = Integer.valueOf("9999"); //数字以外を書くと実行時エラー・小数点(型以外の数値)書いても実行時エラー
		System.out.println(e);

		//parseByte|Short|Int|Float|Double Big２つ以外
		//文字列を各基本型に変換して返す
		Integer f = Integer.parseInt("9999"); //数字以外を書くと実行時エラー・小数点(型以外の数値)書いても実行時エラー
		System.out.println(f);

		//Integer.valueOf("String").intValue()
		//Integer.parseInt("String")
		//が同義となる

		//toString()
		//インスタンスメソッドとクラスメソッドどっちもある(クラス～はBig２つ以外)
		String g = bi.toString();
		System.out.println(g);
		String h = Integer.toString(f); //これはBig２つ以外
		String j = f.toString();
		System.out.println(h + "," + j);



		//定数

		//Big２つ以外(６クラス)共通の定数
		System.out.println(  Integer.MAX_VALUE  ); //呼び出し型の基本型で返される( Integer => int
		System.out.println(  Integer.MIN_VALUE  );

		//Double Float の２つ共通
		System.out.println(  Double.NEGATIVE_INFINITY  ); //負の無限大値 ってなに
		System.out.println(  Double.POSITIVE_INFINITY  ); //正の無限大値 ってなに

		exFormat();
		exCustomFormat();
		exLocale();
	}

	public static void exFormat() {
		//フォーマット処理
		//$とか%とかつける・NumberFormatクラス(java.textパッケージ)


		//フォーマットしたい数値(long or double)
		long n1 = 1000000L;
		long n2 = 2000000L;
		double n3 = 0.50D;

		//まず ファクトリメソッド を呼び出してNumberFormatオブジェクトを作る
		NumberFormat a = NumberFormat.getNumberInstance(); //フォーマットの形式(通貨とか%とか)を get する
		NumberFormat b = NumberFormat.getCurrencyInstance();
		NumberFormat c = NumberFormat.getPercentInstance();

		//作ったnumberFormatオブジェクトをformatメソッドでフォーマットする。引数に処理する数値
		System.out.println(  a.format(n1)  ); //NumberInstance 数字の途中に "," 入る
		System.out.println(  b.format(n2)  ); //CurrencyInstance "￥"マークと","つく
		System.out.println(  c.format(n3)  ); //PercentInstance 小数点を%に換算する 0.50 = 50%
	}

	public static void exCustomFormat() {
		//カスタムフォーマット処理
		//DecimalFormatクラスで細かいフォーマット形式を指定できる (NumberFormatの小クラス)


		//フォーマットしたい数値
		double n1 = 1252.24555D;
		double n2 = 1252.24D;
		double n3 = 1252.24D;
		double n4 = 0.50D;
		double n5 = 0.50D;

		//DecimalFormatクラスのコンストラクタの引数にフォーマット形式をStringで渡し、DecimalFormat オブジェクト生成
		//それから、同じ様にformat(数値)でフォーマット
		DecimalFormat a = new DecimalFormat("###,###.###"); //#は数字(0の場合非表示)・小数点以下溢れた場合は四捨五入
		System.out.println(a.format(n1));
		DecimalFormat b = new DecimalFormat("000,000.000"); //0も数字(0の場合は0で埋める)
		System.out.println(b.format(n2));
		DecimalFormat c = new DecimalFormat("\u00A5###,###"); // \u00A5 で　￥マーク \もセット
		System.out.println(c.format(n3));
		DecimalFormat d = new DecimalFormat("###%"); // %つける
		System.out.println(d.format(n4));
		DecimalFormat e = new DecimalFormat("'%'###%"); // ''シングルクォーテーションでエスケープ文字
		System.out.println(e.format(n5));

		//new DecimalFormat() 引数空のコンストラクタだと全てデフォルトのフォーマットになる
		DecimalFormat f = new DecimalFormat();
		System.out.println(  f.format(n1)  ); //デフォルト＝ NumberInstance と同じ(カンマ入るだけ)
	}

	public static void exLocale() {
		//ロケール
		//地域情報　これのデフォルトをgetしてるから　￥マークが出る。USなら$など
		//java.util パッケージ

		System.out.println(  Locale.getDefault()  ); //default "ja_JP"
		Locale.setDefault(Locale.US); //US に変更
		System.out.println(  Locale.getDefault()  ); //default "en_US"


		NumberFormat nf = NumberFormat.getCurrencyInstance();
		System.out.println(  nf.format(200000L)  ); //default変更したので $200,000.00

		NumberFormat nf2 = NumberFormat.getCurrencyInstance(Locale.UK); //ファクトリメソッドの引数にロケールを渡してもOK
		System.out.println(  nf2.format(2000000L)  ); 
	}
}
