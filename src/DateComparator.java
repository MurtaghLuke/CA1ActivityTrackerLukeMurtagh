import java.util.Comparator;

public class DateComparator implements Comparator<Activity> {

    @Override
    public int compare(Activity date1, Activity date2) {
        int[] dateInt1 = parseDate(date1.getDate());
        int[] dateInt2 = parseDate(date2.getDate());

        if(dateInt1[2]== dateInt2[2])
        {
            if(dateInt1[1]== dateInt2[1])
            {
                return dateInt1[0]- dateInt2[0];
            }
            return dateInt1[1]- dateInt2[1];
        }
        return dateInt1[2]- dateInt2[2];
    }

    public int[] parseDate(String s)
    {
        s = s.trim();
        String[] dateStr = s.split("/");
        int[] dates = new int[dateStr.length];
        for(int i = 0; i < dateStr.length;i++)
        {
            dates[i] = Integer.parseInt(dateStr[i]);
        }
        return dates;
    }


}





