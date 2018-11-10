import job.Job;
import job.ListOfJob;
import job.Process;
import output.AngularGanttOutputter;
import resource.ListOfResource;
import resource.Resource;
import schedule.Schedule;
import schedule.Scheduler;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        Resource resource1 = new Resource("1",1);
        Resource resource2 = new Resource("2",2);
        Resource resource3 = new Resource("3",3);

        List<Resource> resourceList = new ArrayList<>();
        resourceList.add(resource1);
        resourceList.add(resource2);
        resourceList.add(resource3);

        ListOfResource listOfResource = new ListOfResource(resourceList);

        Process process1 = new Process("1-1",1,3);
        Process process2 = new Process("1-2",2,3);
        Process process3 = new Process("1-3",3,3);
        List<Process> processList1= new ArrayList<>();
        processList1.add(process1);
        processList1.add(process2);
        processList1.add(process3);
        Process process4 = new Process("2-1",2,3);
        Process process5 = new Process("2-2",1,3);
        Process process6 = new Process("2-3",3,3);
        List<Process> processList2= new ArrayList<>();
        processList2.add(process4);
        processList2.add(process5);
        processList2.add(process6);
        Job job1 = new Job("1",processList1);
        Job job2 = new Job("1",processList2);
        List<Job> jobList = new ArrayList<>();
        jobList.add(job1);
        jobList.add(job2);
        ListOfJob listOfJob = new ListOfJob(jobList);

        Scheduler scheduler = new Scheduler();
        scheduler.scheduling(listOfResource,listOfJob);

       AngularGanttOutputter angularGanttOutputter = new AngularGanttOutputter();
       angularGanttOutputter.outputResourceSchedule(listOfResource);
    }
}
