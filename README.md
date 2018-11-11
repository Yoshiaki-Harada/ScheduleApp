# ScheduleApp
スケジュールを立てる為のプログラムで、卒業論文で作成したプログラムを一部改良したものです。

入力は,ResourceとJobの情報です。それぞれjsonファイルを読み込むようにしています。

入力について
Resourceの情報
idが1，処理できるプロセスの種類が1
 [ {
    "id": "1",
    "type": 1,
    "resourceSchedule": {
      "id": "1",
      "scheduleList": []
    }
  }]
 Jobの情報
 Jobのidが，その中にidが1-1のプロセスとidが1-2のプロセスがあり，処理順序はこの順番を守らなけらいけないものを想定
 プロセス1-1は種類が1で処理時間が4
 プロセス2-2が種類が2で処理時間が5
[{   "id": "1",
    "state": "Wait",
    "processList": [{"id": "1-1","type": 1,"processTime": 4,"currentTime": 0,"state": "NotAble"},
                    {"id": "1-2", "type": 2,"processTime": 5,"currentTime": 0,"state": "NotAble"}]
                   
