package job;

import resource.Resource;
import schedule.Schedule;

public class Process {
    private final String id;
    private final int type;
    private final int processTime;
    private int currentTime;
    private ProcessState state;
    private Schedule schedule;

    public Process(String id, int type, int processTime) {
        if (id == "") {
            System.out.println("idを指定してください。");
        }
        this.id = id;
        this.type = type;
        this.processTime = processTime;
        this.currentTime = 0;
        this.state = ProcessState.NotAble;
    }


    public boolean isIn() {
        return this.state.isIn();
    }

    public boolean isNotAble() {
        return this.state.isNotAble();
    }

    public boolean isAble() {
        return this.state.isAble();
    }

    /**
     * 終了したかの判定を行う
     * 終了すれば終了時間の記録も行う
     *
     * @param t
     * @return 完了していればtrue していなければfalse
     */
    public boolean hasDone(int t) {
        if (this.currentTime == this.processTime) {
            this.state = ProcessState.Done;
            this.schedule.setEndTime(t + 1);
            return true;
        }
        return false;
    }

    public boolean isDone() {
        return this.state.isDone();

    }

    public void changeIn(Resource resource, int t) {
        this.state = ProcessState.In;
        this.schedule = new Schedule(resource.getId(), t);
    }


    public void changeAble() {
        this.state = ProcessState.Able;
    }

    public String getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public void incCurrentTime() {
        this.currentTime++;
    }

    public int getProcessTime() {
        return processTime;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public ProcessState getState() {
        return state;
    }

}
