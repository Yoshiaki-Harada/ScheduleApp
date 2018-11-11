# ScheduleApp
スケジュールを立てる為のプログラムで，卒業論文で作成したプログラムの一部を改良したものです。
言語はJavaでintellijを用いてgradleプロジェクトとして作成しました。

入力は,ResourceとJobの情報です。それぞれjsonファイルを読み込むようにしています。出力はガントチャート用のjsonファイルです。

## 入力について

Resourceの情報(resourceList.json)

idが1，処理できるプロセスの種類が1(種類は整数値で判定)
```
[ {
    "id": "1",
    "type": 1
  }]
  ```
  
 Jobの情報(jobLit.json)
 
 Jobのidが，その中にidが1-1のプロセスとidが1-2のProcessがあり，処理順序はこの順番を守らなければいけないものを想定している。
 
 Process1-1は種類(種類は整数値で判定)が1で処理時間が4
 
 Process2-2は種類が2で処理時間が5
 
 ```
 [{   "id": "1",
    "state": "Wait",
    "processList": [{"id": "1-1", "type": 1,"processTime": 4,"currentTime": 0,"state": "NotAble"},
                    {"id": "1-2", "type": 2,"processTime": 5,"currentTime": 0,"state": "NotAble"}]}]
```                  
## スケジュールについて
Jobのリストの先頭から優先されるスケジュールを作成する。Jobの中には先行関係制約があるものを想定している。
 
## 出力(schedule.json)
ガントチャートを生成するAngularGantt用のJsonファイルを出力。以下の物を使いガントチャートを出力しました。https://github.com/ksakae1216/AngularGantt しかし，javascriptに対する知識があまりなくファイル入力に対応させることができませんでした。なのでscripts/scripts.jsの中のsampleデータ部分を直接書き換えてガントチャートを出力しました。同じJobのProcessは同じ色で表示できるようにしました。

![image](./ガントチャート例.png)
