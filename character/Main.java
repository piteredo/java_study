package java_study.character;

public class Main {
	public static void main(String[] args){

		//文字列
		//Character・String・StringBuffer の３クラスがある(のみ？)
		exCharacter();
		exString();
		exStringBuffer();
	}

	public static void exCharacter(){
		//Character
		//char型１文字を扱う(シングルクォーテーション)・イミュータブル

		//生成
		//Character a = new Character('A'); //コンストラクタは java9 から非推奨らいしい
		Character a = Character.valueOf('A'); //この指定が推奨のよう
		System.out.println(a);

		//比較
		// compareTo() ２つのオブジェクトのunicode値で比較。等しい=0 / 呼び出しOBJのが大きい=1 / 小さい=-1 を返す
		Character b = Character.valueOf('B');
		int c = a.compareTo(b);
		System.out.println(c); // -1
		// equals() ２つのオブジェクトが等しいか。こちらは boolean で返ってくる
		boolean d = a.equals(b);
		System.out.println(d); // false

		//char(基本)型に変換
		char e = a.charValue();
		System.out.println((int)e); // 65(unicode
		System.out.println((int)a); //しかしこれでもchar型ｎなってる、謎

		//クラスメソッド
		System.out.println(  Character.isUpperCase(a)  ); //true
		System.out.println(  Character.isLowerCase(a)  ); //false
		System.out.println(  Character.toLowerCase(a)  ); //a
		System.out.println(  Character.isLetter(a)  ); //true
		System.out.println(  Character.isDigit(a)  ); //false
		System.out.println(  Character.isLetterOrDigit(a)  ); //true
		System.out.println("--");
	}

	public static void exString(){
		//String
		//char型配列=文字列を扱う・イミュータブル

		//生成
		String a = "あいう"; //よほどでなければこれが一番軽いよう
		String b = new String("BBB");
		char[] c = {'C','C','C'};String d = new String(c);
		byte[] e = a.getBytes();for(byte f : e){System.out.println(Integer.toHexString(f));}String g = new String(e); //UTF-8のよう 1バイトごとに配列
		String h = new String(c, 0, 1); //char配列, オフセット,　length・同義
		System.out.println(a + b + d + g + h);
		System.out.println("--");

		//メソッド
		System.out.println(  a.length()  );
		System.out.println(  a.charAt(1)  );
		System.out.println(  a.substring(0)  ); //引数～終端
		System.out.println(  a.substring(0, 1)  ); //引数～引数
		System.out.println(  a.lastIndexOf("あい")  ); //最後の出現インデックス(先頭文字の位置)
		System.out.println(  a.compareTo(b)  );
		System.out.println(  "BBB".equals(b)  );
		System.out.println(  a.concat(b)  ); //引数が後ろにつく "a+b"
		System.out.println(  a.replace('い', 'え')  ); //イミュータブルなのでString作り直してる？
		System.out.println(  a  ); //変数は元の文字列(あいう)を指したまま
		System.out.println(  " あ い ".trim()  ); //両端の空白を消す
		System.out.println(  b.toLowerCase()  );
		System.out.println("--");

		//Ex
		String sentence = "This is test";
		int index = sentence.indexOf("test");
		String word = sentence.substring(index); //自力でindex探さずともこれで出来る
		System.out.println(word);

		//分割
		//split 正規表現に基づき分割してString配列を返す。StringBufferには無い
		String labels = "Label label LABEL";
		String[] labelArray = labels.split(" "); //空白で分割
		for(String l : labelArray){
			System.out.println(l);
		}
		String[] labelArray2 = labels.split(" ", 2); //空白で分割 , 第二引数で回数-1(引数は含まない！)を指定(余りは１要素にぶちこむ)
		for(String ll : labelArray2){
			System.out.println(ll);
		}
	}

	public static void exStringBuffer(){
		//StringBuffer
		//ミュータブルな文字列クラス
		//だけど変更を見越した(デフォルトで)16字分のメモリを食うので、頻繁な変更ないなら String 使うべし

		//コンストラクタ
		StringBuffer a = new StringBuffer(); //メモリ割り当て 16 文字分の文字列バッファ
		StringBuffer b = new StringBuffer(5); //バッファ領域を指定する = 5文字分
		StringBuffer c = new StringBuffer("もじ"); //文字列生成 + デフォルトのバッファ16文字分が加えられる
		System.out.println(a); //空文字がprintされる
		System.out.println(b); //空文字がprintされる
		System.out.println("--");
		//a = "あいう"; //これは型違いなのでコンパイルエラー
		a.append("えおか"); //こうやって代入する
		System.out.println(a);
		b.append("あいうえおかきくけこさしすせそ"); //バッファをオーバーフローしても追加されるので問題なし・この場合１文字目から再生成してるのか？
		System.out.println(b);

		//メソッド
		// length, charAt, substring 同じく使える
		c.append("ぐあ"); //末尾に追加 = 書き換えではない！ Stringのconcatに相当
		System.out.println(c);
		c.append(true); //booleanも文字列として追加できる(char, char[], double, float, int, longいける => 自動型変換で実質全基本型いける)
		System.out.println(c);
		c.delete(2, 4); //引数～引数を削除
		System.out.println(c);
		c.insert(2, "べし"); //第１引数位置に第２引数文字を挿入・全基本型OK;
		System.out.println(c);
		char[] ccc = {'A', 'B', 'C', 'D'};
		c.insert(0, ccc, 1, 2); //char配列のときはオフセット可能(appendでもこれ出来る)
		System.out.println(c);
		c.replace(0, 2, "ぐあぐあぐあぐあ"); //１引数(この値を含む)～２引数-1(この値を含まない！) を置き換える
		System.out.println(c);
		System.out.println(c.length());
		c.replace(0, c.length(), "ぜんとっかえ"); //これで全書換できるけどほかに手段ないのか？
		System.out.println(c);
		b.setLength(7); //文字長を後から変更
		System.out.println(b); //それによって文字が溢れた場合は削られる
		System.out.println(  a.capacity()  ); //バッファ含む割当領域を返す => 16
		System.out.println(  a.reverse()  ); //文字逆転
	}
}
