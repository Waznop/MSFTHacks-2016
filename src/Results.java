/**
 * Created by Waznop on 2016-06-05.
 */
public class Results {

    private String search;

    private int accuracy;

    private int calories;

    public Results(String search, int accuracy, int calories) {
        this.search = search;
        this.accuracy = accuracy;
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "Results{" +
                "search='" + search + '\'' +
                ", accuracy=" + accuracy +
                ", calories=" + calories +
                '}';
    }

    public String getSearch() {
        return search;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public int getCalories() {
        return calories;
    }
}
