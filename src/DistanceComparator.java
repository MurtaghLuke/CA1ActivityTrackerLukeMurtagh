import java.util.Comparator;

public class DistanceComparator implements Comparator<Activity>{
    public int compare(Activity a1, Activity a2){
        int x = (int)((a2.getDistance() - a1.getDistance())*100);
        return x;
    }


}
