import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;


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


//        name = st.nextToken();
//        id = Integer.parseInt(st.nextToken().trim());
//        gpa = Double.parseDouble(st.nextToken().trim());
//        return new student(name, id, gpa);
    }



    public static void main(String[] args) {

    }
}