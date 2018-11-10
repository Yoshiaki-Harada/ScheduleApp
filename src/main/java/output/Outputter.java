package output;

import job.ListOfJob;
import resource.ListOfResource;

public interface Outputter {
    public void outputResourceSchedule(ListOfResource listOfResource);
    public void outputaJobSchedule(ListOfJob listOfResource);
}
