import java.io.File;
import java.io.IOException;
import java.util.*;


public class Main {

    public static void readCSVFile(String filename, ArrayList<Activity> activities, boolean hasHeaders) throws IOException {
        File f = new File(filename);
        Scanner in = new Scanner(f);
        String line;
        boolean headersRead = false;
        while(in.hasNextLine()){
            line = in.nextLine();
            if(!hasHeaders || hasHeaders && headersRead)
            {
                if(line!=""){
                    Activity s = parseLine(line);
                    activities.add(s);
                }
            }
            else{
                headersRead = true;
            }
            System.out.println(line);
        }
    }

    private static Activity parseLine(String line){
        String activityType;
        String date;
        int duration;
        double distance;
        int averageHeartRate;
        StringTokenizer st = new StringTokenizer(line, ",");
        activityType = st.nextToken();
        date = st.nextToken();
        duration = Integer.parseInt(st.nextToken().trim());
        distance = Double.parseDouble(st.nextToken().trim());
        averageHeartRate = Integer.parseInt(st.nextToken().trim());
        return new Activity(activityType, date, duration, distance, averageHeartRate);
    }

    public static void displayMenu(){
        System.out.println("0: Exit");
        System.out.println("1: Sort by calories burned (Descending)");
        System.out.println("2: Sort by date (Ascending)");
        System.out.println("3: Sort by date (Descending)");
        System.out.println("4: Sort by activity duration (Ascending)");
        System.out.println("5: Sort by activity duration (Descending)");
        System.out.println("6: Sort by type of activity");
        System.out.println("7: Sort by distance (Ascending)");
        System.out.println("8: Sort by distance (Descending)");
    }

    public static void displayTable(ArrayList<Activity> entries) {
        System.out.printf("%-20s %-15s %-15s %-15s %-15s%n", "Activity", "Date", "Duration", "Distance", "Average heart rate");

        for (Activity s : entries) {
            System.out.printf("%-20s %-15s %-15d %-15.2f %-15d%n", s.getActivityType(), s.getDate(), s.getDuration(), s.getDistance(), s.getAverageHeartRate());
        }
    }



    public static void main(String[] args) throws IOException {
        ArrayList<Activity> activities = new ArrayList<>();
        System.out.println("\n------Activity table------\n");
        readCSVFile("activity_data_50.csv", activities, true);
        System.out.println("\n------Enter a number to sort the table------");
        Scanner input = new Scanner(System.in);

        int choice = 0;
        do {
            displayMenu();
            choice = input.nextInt();
            switch (choice) {
//                case 1:
//                    displayTable(activities);
//                    break;
                case 2:
                    Collections.sort(activities, Collections.reverseOrder(new DateComparator()));
                    displayTable(activities);
                    break;
                case 3:
                    Collections.sort(activities, new DateComparator());
                    displayTable(activities);
                    break;
                case 4:
                    Collections.sort(activities, new DurationComparator());
                    displayTable(activities);
                    break;
                case 5:
                    Collections.sort(activities, Collections.reverseOrder(new DurationComparator()));
                    displayTable(activities);
                    break;
                case 6:
                    Collections.sort(activities, new ActivityNameComparator());
                    displayTable(activities);
                    break;
                case 7:
                    Collections.sort(activities, Collections.reverseOrder(new DistanceComparator()));
                    displayTable(activities);
                    break;
                case 8:
                    Collections.sort(activities, new DistanceComparator());
                    displayTable(activities);
                    break;


        }
            }
        while (choice != 0);
    }
}