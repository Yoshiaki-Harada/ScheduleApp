package output;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import job.ListOfJob;
import resource.ListOfResource;
import resource.Resource;
import schedule.Schedule;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AngularGanttOutputter implements Outputter{
    List<Map> mapList;
    private ArrayList<AngularTask> angularTaskList;

    public AngularGanttOutputter() {
        this.mapList = new ArrayList<>();
        this.angularTaskList = new ArrayList<>();
    }

    public List convertTasks(Resource resource){
        List<AngularTask> angularTaskList = new ArrayList<>();
        for (Schedule schedule: resource.getResourceSchedule().getScheduleList()){
            angularTaskList.add(new AngularTask(schedule.getId(),"#00FFFF",schedule.getBeginTime(),schedule.getEndTime()));
        }
        return angularTaskList;
    }

    public List convertGantt(ListOfResource listOfResource){
        for (Resource resource: listOfResource.getResourceList()){
            Map map = new HashMap<>();
            map.put("name","Resource" + resource.getId());
            map.put("tasks",this.convertTasks(resource));

            this.mapList.add(map);
        }
        return mapList;
    }

    @Override
    public void outputResourceSchedule(ListOfResource listOfResource) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter fw = new FileWriter("schedule.json");
            fw.write(gson.toJson(this.convertGantt(listOfResource)));
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void outputaJobSchedule(ListOfJob listOfResource) {

    }
}
