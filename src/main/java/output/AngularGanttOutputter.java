package output;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import resource.ListOfResource;
import resource.Resource;
import schedule.Schedule;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * AngulatGantt用の形式のJsonを出力
 */
public class AngularGanttOutputter implements Outputter {
    public static final String SCHEDULE_FILE = "schedule.json";

    private List<Map> mapList;
    private ArrayList<AngularTask> angularTaskList;
    static private Map<String, String> colormap = new HashMap<>(); //同じジョブのプロセスの色を揃える

    public AngularGanttOutputter() {
        this.mapList = new ArrayList<>();
        this.angularTaskList = new ArrayList<>();
    }

    public List convertTasks(Resource resource) {
        List<AngularTask> angularTaskList = new ArrayList<>();
        for (Schedule schedule : resource.getResourceSchedule().getScheduleList()) {
            String color = this.getColor(schedule);
            angularTaskList.add(new AngularTask(schedule.getId(), "#" + color, schedule.getBeginTime(), schedule.getEndTime()));
        }
        return angularTaskList;
    }

    public List convertGantt(ListOfResource listOfResource) {
        for (Resource resource : listOfResource.getResourceList()) {
            Map<String, String> map = new HashMap<>();
            map.put("name", "Resource" + resource.getId());
            Gson gson = new Gson();
            //String型に揃える為に，gsonで変換
            map.put("tasks", gson.toJson(this.convertTasks(resource)));
            this.mapList.add(map);
        }
        return mapList;
    }

    /**
     * 同じジョブのプロセスの色を揃える為
     * (name:1-1)，(name:1-2)は同じジョブのプロセスと判定
     *
     * @param schedule
     * @return
     */
    public String getColor(Schedule schedule) {
        if (colormap.containsKey(schedule.getId().substring(0, 1))) {
            return colormap.get(schedule.getId().substring(0, 1));
        }

        //色の生成
        Random random = new Random();
        int red = random.nextInt(255);
        int green = random.nextInt(255);
        int blue = random.nextInt(255);
        //カラーコードに変換
        String color = Integer.toString(((red << 16) | (green << 8) | blue), 16);
        colormap.put(schedule.getId().substring(0, 1), color);
        return color;
    }


    @Override
    public void outputSchedule(ListOfResource listOfResource) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter fw = new FileWriter(SCHEDULE_FILE, false);
            String gantt = gson.toJson(this.convertGantt(listOfResource));
            //ganttには不要な文字が含まれるのでそれを除去をする。除去したものをjsonファイルに書き込む
            fw.write(removeExtraString(gantt));
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String removeExtraString(String str) {
        String remove1 = "\\";
        String str1 = str.replaceAll(Pattern.quote(remove1), "");
        String remove2 = " \"" + "[";
        String remove3 = "]" + "\"";
        String str2 = str1.replaceAll(Pattern.quote(remove2), "[");
        String str3 = str2.replaceAll(Pattern.quote(remove3), "]");
        return str3;
    }


}
