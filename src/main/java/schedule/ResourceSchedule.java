package schedule;

import java.util.ArrayList;
import java.util.List;

public class ResourceSchedule {
    //自分のID
    private final String id;
    private List<Schedule> scheduleList;

    public ResourceSchedule(String resourceId) {
        if (resourceId == "") {
            System.out.println("idを指定してください。");
        }
        this.id = resourceId;
        this.scheduleList = new ArrayList<>();
    }

    public void addSchedule(String processId, int t) {
        this.scheduleList.add(new Schedule(processId, t));
    }

    public void recordEndTime(int t) {
        this.scheduleList.get(this.scheduleList.size() - 1).setEndTime(t);
    }

    public String getId() {
        return id;
    }

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }
}
