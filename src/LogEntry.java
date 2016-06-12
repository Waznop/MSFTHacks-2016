import java.time.LocalDateTime;

/**
 * Created by Waznop on 2016-06-05.
 */
public class LogEntry {

    private LocalDateTime time;

    private String food;

    private int calories;

    public LogEntry(LocalDateTime time, String food, int calories) {
        this.time = time;
        this.food = food;
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "LogEntry{" +
                "time=" + time +
                ", food='" + food + '\'' +
                ", calories=" + calories +
                '}';
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getFood() {
        return food;
    }

    public int getCalories() {
        return calories;
    }
}
