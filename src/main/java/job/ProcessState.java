package job;

public enum ProcessState {
    NotAble,//実行不可能
    Able,//実行可能
    In,//実行中
    Done;//完了

    public boolean isNotAble() {
        return this == NotAble;
    }

    public boolean isAble() {
        return this == Able;
    }

    public boolean isIn() {
        return this == In;
    }

    public boolean isDone() {
        return this == Done;
    }
}
