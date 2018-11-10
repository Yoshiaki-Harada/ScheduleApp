package job;

public enum JobState {
    Wait,//待ち状態
    In,//実行中
    Done;//完了

    public boolean isWait(){
        return this == Wait;
    }

    public boolean isDone(){
        return this == Done;
    }
}
