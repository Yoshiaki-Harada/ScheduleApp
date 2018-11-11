package resource;

import job.Process;
import schedule.ResourceSchedule;

public class Resource {
    private String id;
    private int type;
    private Process process;
    private ResourceSchedule resourceSchedule;

    public Resource(String id, int type) {
        this.id = id;
        this.type = type;
        this.process = null;
    }

    public String getId() {
        return id;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process, int t) {
        this.process = process;
        if (resourceSchedule == null){
            this.resourceSchedule = new ResourceSchedule(this.id);
        }
        this.resourceSchedule.addSchedule(process.getId(), t);
    }

    public boolean isAble(Process process){
        if (this.process == null && this.type == process.getType()){
            return true;
        }
        return false;
    }

    /**
     * プロセスの処理を行う
     * 終了していれば、このリソースのプロセスをnullにする。
     * @param t
     */
    public void doProcess(int t){
        this.process.incCurrentTime();
        if (process.hasDone(t)){
            resourceSchedule.recordEndTime(t+1);
            this.process = null;
        }
    }

    public ResourceSchedule getResourceSchedule() {
        return resourceSchedule;
    }
}
