package job;

import java.util.List;

public class Job {
    private final String id;
    private JobState state;
    private List<Process> processList;

    public Job(String id, List<Process> processList) {
        if (id == "") {
            System.out.println("idを指定してください。");
        }
        this.id = id;
        this.processList = processList;
        this.state = JobState.Wait;
    }

    public boolean isDone() {
        return this.state.isDone();
    }

    /**
     * 実行可能なプロセスを実行可能にする
     */
    public void changeProcessAble() {
        int i = 0;
        for (Process process : this.processList) {
            //1番目のプロセスが実行不可能となっているとき、実行可能にする
            if (i == 0 && process.isNotAble()) {
                process.changeAble();
                this.state = JobState.In;
                return;
            }
            //1つ前が終わっているのに、実行不可能なプロセスを実行可能にする
            if (i > 0 && this.processList.get(i - 1).isDone() && process.isNotAble()) {
                process.changeAble();
                return;
            }
            i++;
        }
    }

    public void changeDone() {
        for (Process process : this.processList) {
            if (!process.isDone()) {
                return;
            }
        }
        this.state = JobState.Done;
    }

    public String getId() {
        return id;
    }

    public List<Process> getProcessList() {
        return processList;
    }


}
