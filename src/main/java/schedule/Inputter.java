package schedule;

import job.Job;
import resource.Resource;

import java.util.ArrayList;
import java.util.List;

public interface Inputter {
    public List<Resource> inputResource();
    public List<Job> inputJOb();
}
