package schedule;


import job.Job;
import job.ListOfJob;
import job.Process;
import resource.ListOfResource;
import resource.Resource;

public class Scheduler {


    public void scheduling(ListOfResource listOfResource, ListOfJob listOfJob) {

        //全てのジョブが終わったら終了
        for (int time = 0; listOfJob.allDone(); time++) {
            //実行可能なプロセスの状態を実行可能に変更
            listOfJob.changeProcessAble();
            for (Job job : listOfJob.getJobList()) {
                //完了したジョブは無視
                if (job.isDone()) {
                    continue;
                }
                for (Process process : job.getProcessList()) {
                    //実行不可能なプロセスと実行中のプロセスと完了したプロセスは無視
                    if (process.isNotAble() || process.isIn() || process.isDone()) {
                        continue;
                    }
                    if (process.isAble() && listOfResource.isAbleResource(process)) {
                        Resource resource = listOfResource.getAbleResource(process);
                        resource.setProcess(process, time);
                        process.changeIn(resource, time);
                    }
                }
            }
            //実行中のプロセスの処理を1行う
            listOfResource.doProcess(time);
            //完了したジョブの状態を変更
            listOfJob.changeDone();
        }
    }

}
