package job;

import java.util.List;

public class ListOfJob {
    private List<Job> jobList;

    public ListOfJob(List<Job> jobList) {
        this.jobList = jobList;
    }

    public List<Job> getJobList() {
        return jobList;
    }

    public void changeProcessAble(){
        for (Job job: this.jobList){
            job.changeProcessAble();
        }
    }

    public void changeDone(){
        for (Job job: this.jobList){
            job.changeDone();
        }
    }

    public boolean allDone(){
        for (Job job :this.jobList){
            if (!job.isDone()){
                return false;
            }
        }
        return true;
    }


}
