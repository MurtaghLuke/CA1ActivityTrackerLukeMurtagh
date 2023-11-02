import java.util.Comparator;

public class DurationComparator implements Comparator<Activity> {
    @Override
    public int compare(Activity activity1, Activity activity2) {
        int duration1 = activity1.getDuration();
        int duration2 = activity2.getDuration();

        // Compare durations
        return Integer.compare(duration1, duration2);
    }
}