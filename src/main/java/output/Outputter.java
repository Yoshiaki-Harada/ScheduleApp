package output;

import job.ListOfJob;
import resource.ListOfResource;

public interface Outputter {
    public void outputSchedule(ListOfResource listOfResource);
}
