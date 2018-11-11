import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import job.Job;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import resource.Resource;

/**
 * JsonファイルからのInput用のクラス
 */
public class FileInputter {

    public List<Resource> inputResourceList(String filename) throws IOException {
        Gson gson = new Gson();
        final FileInputStream ips = new FileInputStream(filename);
        final InputStreamReader isr = new InputStreamReader(ips, StandardCharsets.UTF_8);
        JsonReader jsonReader = new JsonReader(isr);
        ArrayList<Resource> resourceList =gson.fromJson(jsonReader, new TypeToken<List<Resource>>() {}.getType());
        isr.close();

        return resourceList;
    }

    public List<Job> inputJobList(String filename) throws IOException{
        Gson gson = new Gson();
        final FileInputStream ips = new FileInputStream(filename);
        final InputStreamReader isr = new InputStreamReader(ips, StandardCharsets.UTF_8);
        JsonReader jsonReader = new JsonReader(isr);
        ArrayList<Job> jobList = gson.fromJson(jsonReader, new TypeToken<List<Job>>() {}.getType());
        isr.close();

        return jobList;
    }

}

