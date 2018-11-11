package output;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import job.ListOfJob;
import resource.ListOfResource;
import resource.Resource;
import schedule.Schedule;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * AngulatGantt用の形式のJsonを出力
 */
public class AngularGanttOutputter implements Outputter{
    public static final String SCHEDULE_FILE = "schedule.json";

    List<Map> mapList;
    private ArrayList<AngularTask> angularTaskList;
    static Map<String,String> colormap = new HashMap<>(); //同じジョブのプロセスの色を揃える

    public AngularGanttOutputter() {
        this.mapList = new ArrayList<>();
        this.angularTaskList = new ArrayList<>();
    }

    public List convertTasks(Resource resource){
        List<AngularTask> angularTaskList = new ArrayList<>();
        for (Schedule schedule: resource.getResourceSchedule().getScheduleList()){
            String color = this.getColor(schedule);
            angularTaskList.add(new AngularTask(schedule.getId(),"#"+color,schedule.getBeginTime(),schedule.getEndTime()));
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

    /**
     * 同じジョブのプロセスの色を揃える為
     * @param schedule
     * @return
     */
    public String getColor(Schedule schedule){
        if (colormap.containsKey(schedule.getId().substring(0,1))){
            return colormap.get(schedule.getId().substring(0,1));
        }

        //色の生成
        Random random = new Random();
        int red = random.nextInt(255);
        int green = random.nextInt(255);
        int blue = random.nextInt(255);
        //カラーコードに変換
        String color = Integer.toString(((red << 16) | (green << 8) | blue), 16);
        colormap.put(schedule.getId().substring(0,1), color);
        return color;
    }


    @Override
    public void outputSchedule(ListOfResource listOfResource) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter fw = new FileWriter(SCHEDULE_FILE,false);
            fw.write(gson.toJson(this.convertGantt(listOfResource)));
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
