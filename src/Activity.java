import java.util.Objects;

public class Activity
{
    private String activityType;
    private String date;
    private int duration;
    private double distance;
    private int averageHeartRate;

    private double caloriesBurned;


    //Energy expended (Intensity) ENUM
    private double speed;
    private INTENSITY Intensity;
    public static enum INTENSITY {VERY_LIGHT, LIGHT, MODERATE, VIGOROUS, VERY_VIGOROUS};


    public Activity(String activityType, String date, int duration, double distance, int averageHeartRate) {
        this.activityType = activityType;
        this.date = date;
        this.duration = duration;
        this.distance = distance;
        this.averageHeartRate = averageHeartRate;
    }


    //calculate average speed in km/ph
    public double getSpeed(){
        speed = (distance / (duration / 60.00));
        return speed;
    }


    //Energy expended based on the average kilometres per hour
    public void setIntensity() {
        double speed = this.getSpeed(); // Calculate speed once

        if (Objects.equals(activityType, "Swimming")) {
            if (speed >= 0.50 && speed < 1.25) {
                Intensity = INTENSITY.VERY_LIGHT;
            } else if (speed >= 1.25 && speed < 2.00) {
                Intensity = INTENSITY.LIGHT;
            } else if (speed >= 2.00 && speed < 2.75) {
                Intensity = INTENSITY.MODERATE;
            } else if (speed >= 2.75 && speed < 3.50) {
                Intensity = INTENSITY.VIGOROUS;
            } else if (speed >= 3.50) {
                Intensity = INTENSITY.VERY_VIGOROUS;
            }
        } else if (Objects.equals(activityType, "Running")) {
            if (speed >= 4.00 && speed < 8.00) {
                Intensity = INTENSITY.VERY_LIGHT;
            } else if (speed >= 8.00 && speed < 12.00) {
                Intensity = INTENSITY.LIGHT;
            } else if (speed >= 12.00 && speed < 16.00) {
                Intensity = INTENSITY.MODERATE;
            } else if (speed >= 16.00 && speed <= 40.00) {
                Intensity = INTENSITY.VERY_VIGOROUS;
            } else {
                Intensity = INTENSITY.VIGOROUS;
            }
        } else if (Objects.equals(activityType, "Cycling")) {
            if (speed >= 8.00 && speed < 16.00) {
                Intensity = INTENSITY.LIGHT;
            } else if (speed >= 16.00 && speed < 25.00) {
                Intensity = INTENSITY.MODERATE;
            } else if (speed >= 25.00 && speed < 33.00) {
                Intensity = INTENSITY.VIGOROUS;
            } else if (speed >= 33.00) {
                Intensity = INTENSITY.VERY_VIGOROUS;
            } else {
                Intensity = INTENSITY.VERY_LIGHT;
            }
        } else {
            Intensity = INTENSITY.VERY_LIGHT;
        }
    }
    public INTENSITY getIntensity() {
        return Intensity;
    }


    public void setCaloriesBurned() {
        double duration = this.getDuration(); // Calculate speed once
        Intensity = this.getIntensity();

        if (Objects.equals(activityType, "Swimming")) {
            if (Intensity == INTENSITY.VERY_LIGHT) {
                caloriesBurned = (this.duration * 5);
            } else if (Intensity == INTENSITY.LIGHT) {
                caloriesBurned = (this.duration * 6.3);
            } else if (Intensity == INTENSITY.MODERATE) {
                caloriesBurned = (this.duration * 7.6);
            } else if (Intensity == INTENSITY.VIGOROUS) {
                caloriesBurned = (this.duration * 8.9);
            } else if (Intensity == INTENSITY.VERY_VIGOROUS) {
                caloriesBurned = (this.duration * 10.2);
            }
        } else if (Objects.equals(activityType, "Running")) {
            if (Intensity == INTENSITY.VERY_LIGHT) {
                caloriesBurned = (this.duration * 4.1);
            } else if (Intensity == INTENSITY.LIGHT) {
                caloriesBurned = (this.duration * 7.2);
            } else if (Intensity == INTENSITY.MODERATE) {
                caloriesBurned = (this.duration * 10);
            } else if (Intensity == INTENSITY.VIGOROUS) {
                caloriesBurned = (this.duration * 15.4);
            } else if (Intensity == INTENSITY.VERY_VIGOROUS) {
                caloriesBurned = (this.duration * 20.8);
            }
        } else if (Objects.equals(activityType, "Cycling")) {
            if (Intensity == INTENSITY.VERY_LIGHT) {
                caloriesBurned = (this.duration * 2);
            } else if (Intensity == INTENSITY.LIGHT) {
                caloriesBurned = (this.duration * 5);
            } else if (Intensity == INTENSITY.MODERATE) {
                caloriesBurned = (this.duration * 7);
            } else if (Intensity == INTENSITY.VIGOROUS) {
                caloriesBurned = (this.duration * 13);
            } else if (Intensity == INTENSITY.VERY_VIGOROUS) {
                caloriesBurned = (this.duration * 15);
            }
        } else {
            caloriesBurned = 0;
        }
    }
    public double getCaloriesBurned() {
        return caloriesBurned;
    }







    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getAverageHeartRate() {
        return averageHeartRate;
    }

    public void setAverageHeartRate(int averageHeartRate) {
        this.averageHeartRate = averageHeartRate;
    }




    @Override
    public String toString() {
        return "Activity{" +
                "activityType='" + activityType + '\'' +
                ", date='" + date + '\'' +
                ", duration=" + duration +
                ", distance=" + distance +
                ", averageHeartRate=" + averageHeartRate +
                '}';
    }
}
