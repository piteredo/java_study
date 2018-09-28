package java_study.exception;

import java.util.InputMismatchException;
import java.util.Scanner;

class Main {
  public static void main(String[] args) {

    // 例外
    /*
    例外とは、例外クラスである
    すべてのエラークラスの親は Throwable クラス

    配下
    ・Error クラス　　重大エラー。止まってしまうので捕捉の必要なし
    ・Exception クラス　　回復が期待できる例外。捕捉は必須 = チェック例外
    ・RuntimeException クラス　　Exception の子。捕捉は任意 = 非チェック例外

    ・Throwable で全てのエラーを受け取れるのだけど、予期しないものを受けてしまう場合もあるのでちゃんと狙い定める
    ・catchブロックの仮引数で Throwable の子以外の型を指定するとコンパイルエラー
    ・チェック例外を try で囲んでない場合・または throws してないとはコンパイルエラー
    */
    ex01();

    try {
      ex02();
    } catch(ExException e){
      System.out.println("Throws で投げられてきたエラーを呼び出し元でキャッチ");
      System.out.println("例外情報: " + e); //クラス名を表示
    }
    // ex02(); Throws されてるメソッドを try で囲んでおかないとコンパイルエラー？
  }


  //基本
  public static void ex01() {
    Scanner sc = new Scanner(System.in); //Scanner自体は非チェック例外

    try {
      System.out.println("a / b を計算します");

      System.out.println("a を入力");
      int a = sc.nextInt(); //数字入れてるかチェック

      System.out.println("b を入力");
      int b = sc.nextInt(); //数字入れてるかチェック

      System.out.println("a / b = " + (a/b)); //計算可能か(0で割ってないか)チェック
    } catch(ArithmeticException e) {
      System.out.println("0 除算はできません"); //0で割れないよ エラー
      System.out.println("例外情報: " + e); //クラス名＋ by zero を表示
    } catch(InputMismatchException e) {
      System.out.println("数値を入力してください"); //型がマッチしないよ エラー
      System.out.println("例外情報: " + e); //クラス名を表示
    } finally {
      //スペル注意 ll
      System.out.println("エラーでもこの中は実行するよ！");
    }
  }


  //例外クラスを自作する
  public static void ex02() throws ExException { //例外がおきるメソッドであることを先に宣言(しないとコンパイルエラー？)
    ExException e = new ExException();
    throw e; //キャッチしたいことに対して、自作のエラーを送出する throws ではないので注意
  }
}

class ExException extends Exception {

}
