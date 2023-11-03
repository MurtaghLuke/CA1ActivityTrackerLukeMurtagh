import java.util.Comparator;

public class CaloriesBurnedComparator implements Comparator<Activity> {
    @Override
    public int compare(Activity a1, Activity a2) {
        double cb1 = a1.getCaloriesBurned();
        double cb2 = a2.getCaloriesBurned();

        // Compare calories burned
        return Double.compare(cb1, cb2);
    }
}