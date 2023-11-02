import java.util.Comparator;

public class DateComparator implements Comparator<Activity> {

    @Override
    public int compare(Activity activity1, Activity activity2) {
        String dateStr1 = activity1.getDate();
        String dateStr2 = activity2.getDate();

        // Assuming dateStr1 and dateStr2 are in the format "dd/MM/yyyy"
        return dateStr1.compareTo(dateStr2);
    }


}





