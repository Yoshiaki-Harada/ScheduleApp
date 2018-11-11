import job.Job;
import job.ListOfJob;
import output.AngularGanttOutputter;
import resource.ListOfResource;
import resource.Resource;
import schedule.Scheduler;

import java.io.IOException;
import java.util.List;

public class Main {
    public static final String JOB_FILE = "jobList.json";
    public static final String RESOURCE_FILE = "resourceList.json";

    public static void main(String[] args) throws IOException {

        FileInputter fileInputter = new FileInputter();
        List<Job> jobList = fileInputter.inputJobList(JOB_FILE);
        List<Resource> resourceList = fileInputter.inputResourceList(RESOURCE_FILE);

        ListOfJob listOfJob = new ListOfJob(jobList);
        ListOfResource listOfResource = new ListOfResource(resourceList);

        Scheduler scheduler = new Scheduler();
        scheduler.scheduling(listOfResource,listOfJob);

       AngularGanttOutputter angularGanttOutputter = new AngularGanttOutputter();
       angularGanttOutputter.outputSchedule(listOfResource);
    }
}
