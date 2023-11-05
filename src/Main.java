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
        System.out.println("1: Sort by type of activity");
        System.out.println("2: Sort by date (Ascending)");
        System.out.println("3: Sort by date (Descending)");
        System.out.println("4: Sort by activity duration (Ascending)");
        System.out.println("5: Sort by activity duration (Descending)");
        System.out.println("6: Sort by calories burned (Descending)");
        System.out.println("7: Sort by distance (Ascending)");
        System.out.println("8: Sort by distance (Descending)");
        System.out.println("9: view a subset of activities based on specific fields");
        System.out.println("10: View average distance per activity");
        System.out.println("11: View average calories burned");
    }


    public static void displayTable(ArrayList<Activity> entries) {

        System.out.printf("%-20s %-15s %-15s %-15s %-15s %16s %20s%n", "Activity", "Date", "Duration", "Distance", "Average heart rate", "Intensity", "Calories burned");

        //set intensity for each line
        for (Activity activity : entries) {
            activity.setIntensity();
            activity.setCaloriesBurned();
        }

        for (Activity s : entries) {
            System.out.printf("%-20s %-15s %-15d %-15.2f %-15d %20s %20.2f%n", s.getActivityType(), s.getDate(), s.getDuration(), s.getDistance(), s.getAverageHeartRate(), s.getIntensity(), s.getCaloriesBurned());
        }
    }



    public static double calculateAverageDistance(ArrayList<Activity> activities) {
        double totalDistance = 0.0;
        for (Activity activity : activities) {
            totalDistance += activity.getDistance();
        }
        return totalDistance / activities.size();
    }

    public static double calculateAverageCaloriesBurned(ArrayList<Activity> activities) {
        double totalCaloriesBurned = 0.0;
        for (Activity activity : activities) {
            totalCaloriesBurned += activity.getCaloriesBurned();
        }
        return totalCaloriesBurned / activities.size();
    }




    public static void main(String[] args) throws IOException {
        ArrayList<Activity> activities = new ArrayList<>();
        System.out.println("\n------Activity table------\n");
        readCSVFile("activity_data_50.csv", activities, true);

        System.out.println("\n------Enter a number to sort your table------");
        Scanner input = new Scanner(System.in);

        int choice = 0;
        do {
            displayMenu();
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    Collections.sort(activities, new ActivityNameComparator());
                    displayTable(activities);
                    System.out.println();
                    break;
                case 2:
                    Collections.sort(activities, new DateComparator());
                    displayTable(activities);
                    System.out.println();
                    break;
                case 3:
                    Collections.sort(activities, Collections.reverseOrder(new DateComparator()));
                    displayTable(activities);
                    System.out.println();
                    break;
                case 4:
                    Collections.sort(activities, new DurationComparator());
                    displayTable(activities);
                    System.out.println();
                    break;
                case 5:
                    Collections.sort(activities, Collections.reverseOrder(new DurationComparator()));
                    displayTable(activities);
                    System.out.println();
                    break;
                case 6:
                    Collections.sort(activities, new CaloriesBurnedComparator());
                    displayTable(activities);
                    System.out.println();
                    break;
                case 7:
                    //lambda
                    activities.sort((s1, s2) -> Double.compare(s1.getDistance(), s2.getDistance()));
                    displayTable(activities);
                    System.out.println();
                    break;
                case 8:
                    //lambda
                    activities.sort((s1, s2) -> Double.compare(s2.getDistance(), s1.getDistance())); // Reverse order
                    System.out.println(activities);
                    System.out.println();
                    break;
                case 9:   //Allow the user view a subset of their activity based on specific fields
                    input.nextLine();
                    System.out.print("Enter the activity type (Running, Swimming, Cycling): ");
                    String userActivityType = input.nextLine();
                    System.out.print("Enter the minimum distance to two decimal places: ");
                    double userMinDistance = input.nextDouble();
                    input.nextLine();
                    System.out.print("Enter the type of energy expended (VERY_LIGHT, LIGHT, MODERATE, VIGOROUS, VERY_VIGOROUS): ");
                    String userEnergyExpendedType = input.nextLine();
                    System.out.print("Enter the minimum duration as a whole number: ");
                    int userMinDuration = input.nextInt();

                    // filtered activities arraylist
                    ArrayList<Activity> filteredActivities = new ArrayList<>();

                    for (Activity activity : activities) {
                        if (activity.getActivityType().equalsIgnoreCase(userActivityType) &&
                                activity.getDistance() >= userMinDistance &&
                                activity.getIntensity().name().equalsIgnoreCase(userEnergyExpendedType) &&            //gets the name of the enum value
                                activity.getDuration() >= userMinDuration) {
                            filteredActivities.add(activity);
                        }
                    }

                    displayTable(filteredActivities);
                    System.out.println();
                    break;
                case 10:
                    double averageDistance = calculateAverageDistance(activities);
                    System.out.println("Average distance per activity: " + averageDistance);
                    System.out.println();
                    break;
                case 11:
                    double averageCaloriesBurned = calculateAverageCaloriesBurned(activities);
                    System.out.println("Average calories burned: " + averageCaloriesBurned);
                    System.out.println();
                    break;
            }
        }
        while (choice != 0);
    }
}