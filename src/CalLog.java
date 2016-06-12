import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Waznop on 2016-06-05.
 */
public class CalLog {

    private String name;

    private int gender;

    private int age;

    private int activity;

    private List<LogEntry> logs;

    private int recommended;

    private int currentDaily;

    private final int MAX_LOGS = 50;

    public CalLog(String name, int gender, int age, int activity, List<LogEntry> logs) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.activity = activity;
        this.logs = logs;

        recommended = getRecommended(gender, age, activity);
        currentDaily = getCurrentDaily(logs);
    }

    @Override
    public String toString() {
        return "CalLog{" +
                "currentDaily=" + currentDaily +
                ", recommended=" + recommended +
                ", logs=" + logs +
                ", activity=" + activity +
                ", age=" + age +
                ", gender=" + gender +
                ", name='" + name + '\'' +
                '}';
    }

    public void updateInfo(String name, int gender, int age, int activity) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.activity = activity;

        recommended = getRecommended(gender, age, activity);
    }

    public void updateDailyCalories(List<LogEntry> logs) {
        currentDaily = getCurrentDaily(logs);
    }

    public LogEntry addEntry(Results results) {
        LocalDateTime time = LocalDateTime.now();
        String food = results.getSearch();
        int calories = results.getCalories();

        LogEntry entry = new LogEntry(time, food, calories);
        logs.add(entry);

        if (logs.size() > MAX_LOGS) {
            logs.remove(0);
        }

        updateDailyCalories(logs);

        return entry;
    };

    private int getCurrentDaily(List<LogEntry> logs) {
        LocalDate currentDate = LocalDate.now();

        List<LogEntry> dailyLogs = new ArrayList<>();

        for (int i = 0; i < logs.size(); i++) {
            LogEntry entry = logs.get(i);
            if (entry.getTime().toLocalDate().compareTo(currentDate) == 0) {
                dailyLogs.add(entry);
            }
        }

        int dailyCalories = 0;

        for (int i = 0; i < dailyLogs.size(); i++) {
            dailyCalories += dailyLogs.get(i).getCalories();
        }

        return dailyCalories;
    }

    private int getRecommended(int gender, int age, int activity) {
        if (gender == 0) {
            return getRecommendedMale(age, activity);
        } else if (gender == 1) {
            return getRecommendedFemale(age, activity);
        } else {
            return (getRecommendedMale(age, activity) + getRecommendedFemale(age, activity)) / 2;
        }
    }

    // should have used a formula...
    private int getRecommendedMale(int age, int activity) {
        int recommended;

        if (age < 4) {
            if (activity == 0) recommended = 1100;
            else if (activity == 1) recommended = 1350;
            else recommended = 1500;
        } else if (4 <= age && age < 6) {
            if (activity == 0) recommended = 1250;
            else if (activity == 1) recommended = 1450;
            else recommended = 1650;
        } else if (6 <= age && age < 8) {
            if (activity == 0) recommended = 1400;
            else if (activity == 1) recommended = 1600;
            else recommended = 1800;
        } else if (8 <= age && age < 10) {
            if (activity == 0) recommended = 1500;
            else if (activity == 1) recommended = 1750;
            else recommended = 2000;
        } else if (10 <= age && age < 12) {
            if (activity == 0) recommended = 1700;
            else if (activity == 1) recommended = 2000;
            else recommended = 2300;
        } else if (12 <= age && age < 14) {
            if (activity == 0) recommended = 1900;
            else if (activity == 1) recommended = 2250;
            else recommended = 2600;
        } else if (14 <= age && age < 17) {
            if (activity == 0) recommended = 2300;
            else if (activity == 1) recommended = 2700;
            else recommended = 3100;
        } else if (17 <= age && age < 19) {
            if (activity == 0) recommended = 2450;
            else if (activity == 1) recommended = 2900;
            else recommended = 3300;
        } else if (19 <= age && age < 31) {
            if (activity == 0) recommended = 2500;
            else if (activity == 1) recommended = 2700;
            else recommended = 3000;
        } else if (31 <= age && age < 51) {
            if (activity == 0) recommended = 2350;
            else if (activity == 1) recommended = 2600;
            else recommended = 2900;
        } else if (51 <= age && age < 71) {
            if (activity == 0) recommended = 2150;
            else if (activity == 1) recommended = 2350;
            else recommended = 2650;
        } else {
            if (activity == 0) recommended = 2000;
            else if (activity == 1) recommended = 2200;
            else recommended = 2500;
        }

        return recommended;
    }

    // rip hardcoding
    private int getRecommendedFemale(int age, int activity) {
        int recommended;

        if (age < 4) {
            if (activity == 0) recommended = 1100;
            else if (activity == 1) recommended = 1250;
            else recommended = 1400;
        } else if (4 <= age && age < 6) {
            if (activity == 0) recommended = 1200;
            else if (activity == 1) recommended = 1350;
            else recommended = 1500;
        } else if (6 <= age && age < 8) {
            if (activity == 0) recommended = 1300;
            else if (activity == 1) recommended = 1500;
            else recommended = 1700;
        } else if (8 <= age && age < 10) {
            if (activity == 0) recommended = 1400;
            else if (activity == 1) recommended = 1600;
            else recommended = 1850;
        } else if (10 <= age && age < 12) {
            if (activity == 0) recommended = 1500;
            else if (activity == 1) recommended = 1800;
            else recommended = 2050;
        } else if (12 <= age && age < 14) {
            if (activity == 0) recommended = 1700;
            else if (activity == 1) recommended = 2000;
            else recommended = 2250;
        } else if (14 <= age && age < 17) {
            if (activity == 0) recommended = 1750;
            else if (activity == 1) recommended = 2100;
            else recommended = 2350;
        } else if (17 <= age && age < 19) {
            if (activity == 0) recommended = 1750;
            else if (activity == 1) recommended = 2100;
            else recommended = 2400;
        } else if (19 <= age && age < 31) {
            if (activity == 0) recommended = 1900;
            else if (activity == 1) recommended = 2100;
            else recommended = 2350;
        } else if (31 <= age && age < 51) {
            if (activity == 0) recommended = 1800;
            else if (activity == 1) recommended = 2000;
            else recommended = 2250;
        } else if (51 <= age && age < 71) {
            if (activity == 0) recommended = 1650;
            else if (activity == 1) recommended = 1850;
            else recommended = 2100;
        } else {
            if (activity == 0) recommended = 1550;
            else if (activity == 1) recommended = 1750;
            else recommended = 2000;
        }

        return recommended;
    }

    public String getName() {
        return name;
    }

    public int getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public int getActivity() {
        return activity;
    }

    public int getCurrentDaily() {
        return currentDaily;
    }

    public int getRecommended() {
        return recommended;
    }

    public List<LogEntry> getLogs() {
        return logs;
    }
}
