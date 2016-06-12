import java.util.ArrayList;
import java.util.List;

/**
 * Created by Waznop on 2016-06-04.
 */
public class FoodResults {

    private String search;

    private double maxScore;

    private List<Food> foodList;

    private final double MAXRATING = 5;

    private final double NOGRAMPENALTY = 0.8;

    private int rating;

    public FoodResults(String search, double maxScore, List<Food> foodList) {
        this.search = search;
        this.maxScore = maxScore;
        this.foodList = foodList;

        rating = (int) (100 * maxScore / MAXRATING);
    }

    @Override
    public String toString() {
        return "FoodResults{" +
                "rating=" + rating +
                ", foodList=" + foodList +
                ", search='" + search + '\'' +
                '}';
    }

    public Results getResults() {

        List<Food> withGrams = new ArrayList<>();
        List<Food> withoutGrams = new ArrayList<>();

        for (int i = 0; i < foodList.size(); i++) {
            Food foodEntry = foodList.get(i);
            if (foodEntry.getGrams() == 0) {
                withoutGrams.add(foodEntry);
            } else {
                withGrams.add(foodEntry);
            }
        }

        if (getTotalScore(withGrams) < getTotalScore(withoutGrams)) {
            return getResultsByScores(withoutGrams);
        } else {
            return getResultsByGrams(withGrams);
        }

    }

    private double getTotalScore(List<Food> entries) {
        double sum = 0;

        for (int i = 0; i < entries.size(); i++) {
            sum += entries.get(i).getScore();
        }

        return sum;
    }

    private double getAvgCalPerGram(List<Food> entries) {
        double totalScore = 0;
        double calPerGram = 0;

        for (int i = 0; i < entries.size(); i++) {
            Food entry = entries.get(i);
            totalScore += entry.getScore();
            calPerGram += entry.getCalPerGram() * entry.getScore();
        }

        return calPerGram / totalScore;
    }

    private double getAvgGrams(List<Food> entries) {
        double totalScore = 0;
        double grams = 0;

        for (int i = 0; i < entries.size(); i++) {
            Food entry = entries.get(i);
            totalScore += entry.getScore();
            grams += entry.getGrams() * entry.getScore();
        }

        return grams / totalScore;
    }

    private double getAvgCalories(List<Food> entries) {
        double totalScore = 0;
        double calories = 0;

        for (int i = 0; i < entries.size(); i++) {
            Food entry = entries.get(i);
            totalScore += entry.getScore();
            calories += entry.getCalories() * entry.getScore();
        }

        return calories / totalScore;
    }

    private Results getResultsByGrams(List<Food> entries) {
        double avgCalPerGram = getAvgCalPerGram(entries);
        double avgGrams = getAvgGrams(entries);
        int calories = (int) (avgCalPerGram * avgGrams);

        return new Results(search, rating, calories);
    }

    private Results getResultsByScores(List<Food> entries) {
        int calories = (int) (getAvgCalories(entries));
        int accuracy = (int) (rating * NOGRAMPENALTY);

        return new Results(search, accuracy, calories);
    }

}
