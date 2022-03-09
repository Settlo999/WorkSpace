ご高覧いただき、ありがとうございます。
本アプリは私物のPC内のDBと併用して、初めて動作する仕様となっています。
ボードゲームである「ヨット」をJavaで再現し、JSPでブラウザ上へ出力、DBで記録保管と閲覧が可能となっています。
パッケージ毎の簡単な説明もこちらに記載しておきます。

"servlet"パッケージ   フォームからの情報取得、JSPへのフォワード、modelメソッドの呼び出し等を行うクラス(コントローラー)
LogView.java         記録閲覧用
Login                ログイン処理用
Logout               ログアウト用
Yahtzee              ヨット処理用

"DAO"パッケージ        DBにアクセスするクラス(DAO)
GameDAO.java          ゲームテーブルを扱う
GameDetailDAO.java    ゲーム詳細テーブルを扱う
RankDAO.java          役テーブルを扱う
UsersDAO.java         ユーザーテーブルを扱う

"javabeans"パッケージ   スコープを経由してJSPに受け渡すクラス
Dice.java              賽
GameDetail.java        ゲーム詳細
Log.java               記録
Ranks.java             記帳
Scores.java            点数
User.java              ユーザー

"model"パッケージ       実際の処理を行うクラス
LogViewLogic.java      記録閲覧処理
LoginLogic.java        ログイン処理
UserRejisterLogic.java ユーザー登録処理
YahtzeeLogic.java      ヨット処理
