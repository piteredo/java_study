package java_study.collection;

import java.util.*;

class Main {
	public static void main(String[] args){

		//コレクション・フレームワーク
		/*
		インターフェイス
		・List
		・Queue(Dequeue) Listに似てる？
		・Set
		・Map (これだけ Collectionインターフェイスの子ではない)
		*/
		exList();
		exSet();
		exMap();
		exMapSort();
		exIterator();
	}

	public static void exList() {
		/*
		List インターフェイス
		・順番を持つ
		・要素の重複可

		実装クラス
		・ArrayList クラス (普通のList実装 / 要素の検索に強い)
		・LinckedList クラス (各要素の順番をリンクポインタで管理 / 要素の追加・削除に強い)
		*/

		// 変数の型はインターフェイス型を使うのがデファクトスタンダード (後からの変更に強い？)
		List<Integer> al = new ArrayList<>(); //インスタンス側の型引数は省略？可
		al.add(12); // ジェネリクスに指定した型以外を入れるとコンパイルエラー
		al.add(13);
		al.add(14);
		for(Integer s : al){
			System.out.println(s); // 入れた順に出力される
			// 先入先出 FIFO(First In First Out) <=> 後入先出 LIFO(Last In First Out)
		}
		System.out.println("--");

		// get で要素取得。Integer型で返ってきてる(キャストしなくて大丈夫)
		System.out.println(  al.get(0) instanceof Integer );
		System.out.println("--");

		// remove で要素削除。
		System.out.println(  al.remove(1)  ); // 削除した要素を返す
		for(Integer s : al){ System.out.println(s);	} // 削除されてる
		System.out.println("--");

		// size で要素数取得 ( arr.length に相当
		System.out.println(  al.size()  );
		System.out.println("--");


		// toArray でリストを配列に変換
		Integer[] ia = al.toArray(new Integer[al.size()]); // 引数に同型の配列(要素数つき)を渡す
		for(Integer s : ia){ System.out.println(s);	}
		System.out.println("--");


		// Arrays.asList(arr) で配列をリストに変換
		String[] strArr = new String[]{"tes1", "tes2", "tes3"};
		List<String> strList = Arrays.asList(strArr);
		System.out.println(strList);
		System.out.println("--");
		/*
		プリミティブ型は List にできないので asList できない。IntegerならOK
		int[] intArr = new int[]{1, 2, 3};
		List<int> intList = Arrays.asList(intArr);
		System.out.println(intList);
		System.out.println("--");
		*/


		// クラスをリストに詰め込む一例
		class Test {
			private String val1;
			private String val2;
			Test(String val1, String val2) {
				this.val1 = val1;
				this.val2 = val2;
			}
			public String getStr() {
				return val1 + val2;
			}
		}
		List<Test> t = new ArrayList<>();
		t.add(new Test("aaa", "bbb"));
		t.add(new Test("ccc", "ddd"));
		t.add(new Test("eee", "fff"));
		for(Test tt : t){System.out.println(tt.getStr());}
		System.out.println("--");
		System.out.println("--");
	}


	public static void exSet() {
		/*
		Set インターフェイス
		・順番を持たない
		・要素の重複不可

		実装クラス
		・HashMap クラス (普通のSet実装)
		・LinkedHashSet クラス (内部でポインタを保持して、追加された順を保持する。ソート順ではない)
		・TreeSet クラス (SortedSetインターフェイスの実装。ソートされた結果を扱えるけど、早くない)
		*/

		Set<String> ss = new HashSet<>();
		System.out.println(  ss.add("要素１")  ); // 追加可能だったときは true を返す
		System.out.println(  ss.add("要素１")  ); // 追加可能(重複)だったときは false を返す
		ss.add("要素２");
		ss.add("要素３");
		for(String s : ss){System.out.println(s);} // 順番通りには出力されない
		System.out.println("--");

		Set<String> ss2 = new LinkedHashSet<>();
		ss2.add("１");
		ss2.add("２");
		ss2.add("３");
		for(String s : ss2) {System.out.println(s);} // LinkedHashSetを使えば FIFO になる
		System.out.println("--");

		// contains 要素があるか返す
		System.out.println(  ss.contains("要素１")  );
		System.out.println("--");


		//要素重複チェックの一例
		Set<String> sst = new HashSet<>();
		sst.add("東京");
		sst.add("横浜");
		sst.add("北海道");

		if(!sst.add("横浜")) { // 重複なければ即代入
			System.out.println("横浜はすでに登録されています");
		}
		if(sst.contains("横浜")) { // 同義だが、こちらはチェックした後代入
			System.out.println("横浜はすでに登録されています");
		} else {
			sst.add("横浜");
		}
		System.out.println("--");
		System.out.println("--");
	}


	public static void exMap() {
		/*
		Map インターフェイス
		・キーと値が紐付けられた集合
		・キーは重複不可
		・順番を持たない

		実装クラス
		・HashMap クラス (普通の Map 実装)
		・LinkedHashMap クラス (追加された順を持つ / ソートではない)
		・TreeMap クラス (SortedMap の実装。ソートされた結果を扱えるけど、早くない)
		*/

		Map<String, String> hm = new HashMap<>();

		// put(addではない) 要素追加
		hm.put("key1", "要素１");
		hm.put("key2", "要素２");
		hm.put("key3", "要素３");
		System.out.println(  hm.put("key1", "要素４")  ); // キー重複は値を上書きして、古い値を返す false返却じゃない
		System.out.println(  hm.put("key4", "要素１")  ); // キーが重複してなければ値の重複は構わない この場合 null が返る
		System.out.println("--");

		// get(key) キーを引数にして要素取得
		System.out.println(  hm.get("key2")  );

		// keySet() キーの一覧を取得　　Set型で返される (重複の可能性はないため)
		System.out.println(  hm.keySet()  ); // ["key1", "key2", "key3"]

		// values() 値の一覧を取得　　Collection型で返される (重複の可能性があるため)
		System.out.println(  hm.values()  ); // ["要素４", "要素２", "要素３"]

		// entrySet() キー・値のセット一覧　Set<Map.Entry<K, V>> の型で返される
		// Entry = key と value ペアにするインターフェイス
		System.out.println(  hm.entrySet()  ); // [key1=要素４, key2=要素２, key3=要素３, key4=要素１]
		System.out.println("--");
		System.out.println("--");
	}


	public static void exMapSort() {

		// Map の ソート

		// TreeMap key の順番にソートする Mapクラス。引数に Comparatorクラスを渡してソート順を変更できる
		Map<Integer, String> tm = new TreeMap<>(); // 引数無しなら昇順
		tm.put(3, "three");
		tm.put(0, "zero");
		tm.put(2, "two");
		tm.put(1, "one");
		tm.put(4, "four");
		// Mapの全表示は forEach(  BiConsumer<K, V>関数インターフェイス  )　で
		tm.forEach( (key, value) -> System.out.println( key + ":" + value) );
		System.out.println("--");

		Map<Integer, String> tm2 = new TreeMap<>(Comparator.reverseOrder()); // 逆順にするstaticメソッド
		tm2.put(3, "three");
		tm2.put(0, "zero");
		tm2.put(2, "two");
		tm2.put(1, "one");
		tm2.put(4, "four");
		tm2.forEach( (key, value) -> System.out.println( key + ":" + value) );
		System.out.println("--");


		// 値でソートする
		Map<String, Integer> hm = new HashMap<>();
		hm.put("three", 3);
		hm.put("two", 2);
		hm.put("one", 1);
		hm.put("zero", 0);
		hm.put("four", 4);
		hm.forEach((k,v)->System.out.println(k +":"+v));

		List<Map.Entry<String, Integer>> ent = new ArrayList<>(hm.entrySet());
		Collections.sort(ent, (a, b) -> {return a.getValue() - b.getValue();} );

		ent.forEach(v->System.out.println(v));
		System.out.println("--");
		System.out.println("--");
	}


	public static void exIterator() {

		//イテレータ
		//拡張for文 や stream が実装されたので余り使わない？
		//Collectionの親・Iterable とは違う

		Set<String> hs = new HashSet<>();
		hs.add("要素１");
		hs.add("要素２");
		hs.add("要素３");

		Iterator<String> it = hs.iterator();

		// hasNext 次の要素が存在するか boolean 返す
		// 次とは、０番目からのことを指しているのか？？
		while(it.hasNext()){
			// 次の要素を取り出す・これをしないと無限ループになる
			String item = it.next();
			System.out.println(item);
		}
	}
}
