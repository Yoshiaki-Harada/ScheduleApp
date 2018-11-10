package schedule;

public class Schedule {
    private String id;//相手のID（プロセスが持てば相手のリソースの，リソースが持てばプレセスのIDを表す）
    private int beginTime;
    private int endTime;

    /**
     * 呼び出されると，IDを開始時刻を記録する
     * @param id
     * @param beginTime
     */
    public Schedule(String id, int beginTime) {
        this.id = id;
        this.beginTime = beginTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public String getId() {
        return id;
    }

    public int getBeginTime() {
        return beginTime;
    }

    public int getEndTime() {
        return endTime;
    }
}
