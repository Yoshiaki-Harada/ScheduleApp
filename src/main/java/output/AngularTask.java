package output;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AngularTask {
    private static final String INITIAL_TIME = "2018-01-01T01:00:00";
    private String name;
    private String color;
    private String from;
    private String to;

    public AngularTask(String name, String color, int from, int to) {
        this.name = name;
        this.color = color;
        this.from = this.convertTime(from);
        this.to = this.convertTime(to);
    }

    public String convertTime(int t) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = new Date();
        try {
            date = sdf.parse(INITIAL_TIME);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, t);
        Date d1 = calendar.getTime();

        return sdf.format(d1);
    }
}
