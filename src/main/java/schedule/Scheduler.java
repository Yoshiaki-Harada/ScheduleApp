package schedule;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import job.Job;
import job.ListOfJob;
import resource.ListOfResource;
import job.Process;
import resource.Resource;

public class Scheduler {



    public void scheduling(ListOfResource listOfResource, ListOfJob listOfJob){

        for (int t = 0; t < 1000; t++){
            //全てのジョブが終わったら終了
            System.out.println(t);

            if (listOfJob.allDone()){
                break;
            }
            //実行可能なプロセスの状態を実行可能に変更
            listOfJob.changeProcessAble();
            for (Job job: listOfJob.getJobList()){
                //完了したジョブは無視
                if (job.isDone()){
                    continue;
                }
                for (Process process :job.getProcessList()){
                    //実行不可能なプロセスと実行中のプロセスと完了したプロセスは無視
                    if (process.isNotAble() ||process.isIn()|| process.isDone()){
                        continue;
                    }
                    if (process.isAble() && listOfResource.isAbleResource(process)){
                        Resource resource = listOfResource.getAbleResource(process);
                        resource.setProcess(process, t);
                        process.changeIn(resource, t);
                    }
                }
            }
            //実行中のプロセスの処理を1行う
            listOfResource.doProcess(t);
            //完了したジョブの状態を変更
            listOfJob.changeDone();
        }
    }

}
