#  SGFUT on CRAP

## バックエンドサーバー PoC動作環境構築手順

バックエンドサーバーの選定技術検証のためのPoC動作環境構築手順

	1. インストールソフトウェア
    
	    |ソフトウェア|バージョン|リンク|ファイル名|
   	 |:-----------|:------------|:------------|:------------|
  	  |Eclipse|2021-09|(Windows用)[リンク](https://www.eclipse.org/downloads/download.php?file=/oomph/epp/2021-09/R/eclipse-inst-jre-win64.exe&mirror_id=105) <br> |(Windows用) eclipse-inst-jre-win64.exe  |
	    |Java|11|[リンク](https://github.com/AdoptOpenJDK/openjdk11-binaries/releases/tag/jdk-11.0.10%2B9_openj9-0.24.0)|(Windows用)OpenJDK11U-jdk_x64_windows_openj9_11.0.10_9_openj9-0.24.0.zip <br> |
	    |Docker|最新版|[リンク](https://docs.docker.com/desktop/)|Docker Desktop Installer.exe|
   	 |CheckStyle|8.35.0|[リンク](JavaCodingRule/checkstyle/net.sf.eclipsecs-updatesite_8.35.0.202106020658.zip)|net.sf.eclipsecs-updatesite_8.35.0.202106020658.zip|
  	  |SpotBugs|最新版|-|-|
   	 |JMeter|5.4.1|[リンク](https://ftp.riken.jp/net/apache/jmeter/binaries/apache-jmeter-5.4.1.zip)|apache-jmeter-5.4.1.zip|

		※CheckStyle、SpotBugs、JMeterはいずれ開発が始まったら導入するのでPoC環境には不要。


	2. Javaのインストール

    	2.1. OpenJDK11U-jdk_x64_windows_openj9_11.0.10_9_openj9-0.24.0.zipを解凍し、`C:\SGFUT\Java\jdk-11.0.10+9`に配置する。   
    	```
   	 [配置後のフォルダ構成]
	    C:\SGFUT\Java\jdk-11.0.10+9
    	├─bin\
  	  ├─conf\
	    ├─include\
  	  ├─jmods\
  	  ├─legal\
  	  └─lib\
   	 ```


	3. Eclipseセットアップ

	    3.1. Eclipseのインストール

		- 3.1.1. eclipse-inst-jre-win64.exeを任意の場所に配置し、ダブルクリックしてインストーラーを起動する。
		- 3.1.2. Eclipse IDE for Enterprize Java and Web Developersを選択する。
		- 3.1.3. [Java 11 + VM]に「C:\SGFUT\Java\jdk-11.0.10+9」を設定する。
		- 3.1.4. [Installation Folder]は「C:\SGFUT\Eclipse\jee-2021-09」を設定する。
		- 3.1.5. create start menu entry、create desktop shortcutはチェック済みの状態で、INSTALLボタンを押下する。
		- 3.1.6. LAUNCHボタンを押下する。workspaceの指定は「C:\SGFUT\backendServer」とし、Eclipseが起動することを確認する。

		3.2. Spring Toolsプラグインの導入

		- 3.2.1. Help > Eclipse Marketplace をクリックする。
		- 3.2.2. Find に「Spring Tools」を入力して、Enterをクリックする。
		- 3.2.3. 表示された「Spring Tools 4 (aka Spring Tool Suite 4) 4.12.RELEASE」の右下 Install をクリックする。
		- 3.2.4. そのまま、Confirmをクリックする。
		- 3.2.5. ライセンス情報が表示されるので、I accept the terms of the license agreements をチェックして、Finishをクリックする。
		- 3.2.6. Trust画面が表示された場合は、Select ALL を選択して、Trust selectedをクリックする。
		- 3.2.7. Eclipse再起動を求められるので、Restart Now をクリックする。


	4. workspaceのセットアップ

		4.1. JREを設定する。(既に設定されている場合、当手順は不要。)

		- 4.1.1. Window > Preferences でPreferencesを開く。
		- 4.1.2. Java > Installed JREs を開く。
		- 4.1.3. Installed JREsが下記のみになるよう設定する。
	        - Name: jdk-11.0.10+9
    	    - Location: C:\SGFUT\Java\jdk-11.0.10+9
        	- Type: Standard VM

		4.2. GitHubからリポジトリをcloneする。

		- 4.2.1. GitHubにssh接続をしたことが無い場合は、リンク先を参考に鍵を登録する。https://qiita.com/shizuma/items/2b2f873a0034839e47ce
		- 4.2.2. Window > Show View > Other > Git > Git Repositoriesビューを開く。
		- 4.2.3. Git RepositoriesビューのClone a Git Repositoryをクリックする。
		- 4.2.4. URIに以下を入力しNextをクリックする。
			- `git@github.com:Ryo-git1015/SGFUTonCRAP_Backend.git`
		- 4.2.5. 初回ssh接続によりパスワードを求められたら、4.2.1. で設定したパスワードを入力する。
		- 4.2.6. masterブランチにチェックを入れてNextをクリックする。
		- 4.2.7. Directoryに「C:\SGFUT\backendServer\SGFUTonCRAP_Backend」を設定する。
		- 4.2.8. Import all existing Eclipse projects after clone finishes にチェックを入れてFinishをクリックする。
		- 4.2.9. Window > Show View > Other > Java > Package Explorerビューを開く。
		- 4.2.10. Git Repositoriesビューの SGFUTonCRAP_Backend を右クリックして、 Import Projects をクリックする。
		- 4.2.11. Import source が「C:\SGFUT\backendServer\SGFUTonCRAP_Backend」になっていることを確認し、Finishをクリックする。
		- 4.2.12. Package Explorer 上に SGFUTonCRAP_Backend プロジェクトがインポートされたことを確認する。

		4.3. Maven Updateを行う。

		- 4.3.1. Package Explorer から SGFUTonCRAP_Backend プロジェクトを右クリックする。
		- 4.3.2. Maven > Update Project... をクリックする。
		- 4.3.3. Select Allをクリックしすべてを選択後、OKをクリックする。
		- 4.3.4. Enterprise Explorer上で赤い×が出ていなければOK。

		4.4. Maven buildを行う。

		- 4.4.1. Package Explorer から SGFUTonCRAP_Backend プロジェクトを右クリックする。
		- 4.4.2. Run As > Maven build...をクリックする。
		- 4.4.3. Goalsに「clean package -DskipTests=true」を入力して、Runを実行する。
		- 4.4.4. Console上、BUILD SUCCESS となっていることを確認する。
		- 4.4.5. target配下に sgfutback.jar が生成されていることを確認する。(target配下に表示されない時はtargetを選択して、F5をクリックする。)


	5. Docker導入

		5.1. Docker Desktop Installer.exeを実行しインストールする。
			インストール方法は[リンク先](https://docs.docker.com/docker-for-windows/install/)を参照してください。


	6. Git Bash導入

		6.1. Git Bashをインストールする。
			インストール方法は[リンク先](https://qiita.com/suke_masa/items/404f06309bb32ca6c9c5)を参照してください。


	7. Dockerコンテナの作成

		7.1. Windows検索バーに「docker」と入力して、Docker Desktop を起動する。

		7.2. Docker Configをcloneする。
   
		- 7.2.1. エクスプローラー上から「C:\SGFUT」フォルダを右クリックして、Git Bashを起動する。
		- 7.2.2. git@github.com:Ryo-git1015/SGFUTonCRAP_Backend_Docker.git を入力して、Enterをクリックする。
		- 7.2.3. 「C:\SGFUT」配下に「SGFUTonCRAP_Backend_Docker」フォルダが作成されていることを確認する。

		7.3. PoCアプリケーションを起動する。

		- 7.3.1. Windwos検索バーに「Windows P」と入力して、Windows PowerShell を起動する。
		- 7.3.2. PowerShellで「C:\SGFUT\SGFUTonCRAP_Backend_Docker\DockerConfig」に移動する。
		- 7.3.3. 「docker-compose up -d」を実行する。

		7.4. 稼働確認する。

		- 7.4.1. ブラウザに「http://localhost:8080/sgfutback/sample/api/get/userList」を入力する。
			結果画面にList構造で、ユーザーリストが出力されれば問題なし。

		7.5. お試しURL一覧

		- 7.5.1. http://localhost:8080/sgfutback/sample/api/get/eventList
			- GET PostgreSQLのイベントリストを取得する。
		- 7.5.2. http://localhost:8080/sgfutback/sample/api/test/{param}
			- GET paramで指定された文字列を返却する。
		- 7.5.3. http://localhost:8080/sgfutback/sample/api/test?param={param}
			- GET paramで指定された文字列を返却する。 
		- 7.5.4. http://localhost:8080/sgfutback/sample/api/test Body->{param}
			- POST paramで指定された文字列を返却する。
		- 7.5.5. http://localhost:8080/sgfutback/sample/api/resource Body->{param}
			- POST "登録"という文字列を返却する。
		- 7.5.6. http://localhost:8080/sgfutback/sample/api/resource/{param}
			- GET "参照"という文字列を返却する。
		- 7.5.7. http://localhost:8080/sgfutback/sample/api/resource/{param}
			- DELETE "削除"という文字列を返却する。
		- 7.5.8. http://localhost:8080/sgfutback/sample/api/resource Body->{param}
			- PUT "更新"という文字列を返却する。
		- 7.5.9. http://localhost:8080/sgfutback/sample/api/hogemoge
			- GET HogeMogeBeanのJavaBean(POJO)を返却する。
		- 7.5.10. http://localhost:8080/sgfutback/sample/api/hogemoge3
			- GET HogeMogeMapを返却する。
		- 7.5.11. http://localhost:8080/sgfutback/sample/api/hogemoge4/
			- GET k8sマークのファイルを返却する。
		- 7.5.12. http://localhost:8080/sgfutback/sample/api/json
			- GET JSON形式で返却する。
		- 7.5.13. http://localhost:8080/sgfutback/sample/api/slack/postMessage
			- GET Slackにテストメッセージを通知する。(※ 当手順の「9. Slackへの通知API構築手順」参照)


	8. JMeter導入(番外編 現状は不要)
   
		8.1. apache-jmeter-5.4.1.zipを任意の場所に解凍します。

		8.2. [JMeterインストールフォルダ]\bin\jmeter.bat をダブルクリックするとGUIが起動します。


	9. Slackへの通知API構築手順

		9.1. Slack通知する対象のワークスペースを作成する。（作成済みの場合は当手順スキップ。）

		9.2. 以下のURL先の手順を参考に対象のワークスペースにBotを作成する。
			https://qiita.com/odm_knpr0122/items/04c342ec8d9fe85e0fe9
            ※1. Botに追加する権限は「calls:write」とする。
            ※2. 当手順の6.インストールしたボットを操作対象のチャンネルに招待するは、既に追加したBot表示されている場合は不要。

		9.3. Slackの通知対象としているチャンネルをクリックし、チャンネル名横の「∨」をクリックする。

		9.4. インテグレーション > APp > アプリを追加する をクリックする。

		9.5. 手順9.2.で追加したbotが表示されていることを確認し、検索窓に「Incoming WebHook」と入力し、インストールボタンをクリックする。

		9.6. 遷移先ページの「Slack に追加」をクリックする。

		9.7. チャンネルへの投稿で通知対象のチャンネルを選択して、「Incoming WebHookインテグレーションの追加」をクリックする。

		9.8. セットアップの手順に表示された Webhook URLをコピーし、eclipseワークスペースのSampleRestApiController.java SLACK_INCOMING_WEBHOOKに設定する。

		9.9. SampleRestApiController.java SimpleSlackIncomingの設定値も上記手順で設定した値に書き換える。

		9.10. eclipseワークスペースのSGFUTonCRAP_Backendプロジェクト右クリックでRun As > Maven build... をクリックする。

		9.11. Goalsに「clean package -DskipTests=true」を入力し、Runを実行する。Consoleに BUILD SUCCESS が表示されることを確認する。

		9.12. 「C:\SGFUT\backendServer\SGFUTonCRAP_Backend\target」配下にある sgfutback.jar を「C:\SGFUT\SGFUTonCRAP_Backend_Docker\DockerConfig\springboot\target」配下にコピーする。

		9.13. dockerコマンドで既存のspringbootイメージファイル(dockerconfig_app)を削除し、docker-compose up -dで再作成。


