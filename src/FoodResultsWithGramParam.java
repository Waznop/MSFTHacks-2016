import java.util.ArrayList;
import java.util.List;

/**
 * Created by Waznop on 2016-06-05.
 */
public class FoodResultsWithGramParam {

    private String search;

    private double maxScore;

    private List<Food> foodList;

    private final double MAXRATING = 5;

    private double gramParam;

    private int rating;

    public FoodResultsWithGramParam(String search, double maxScore, List<Food> foodList, double gramParam) {
        this.search = search;
        this.maxScore = maxScore;
        this.foodList = foodList;
        this.gramParam = gramParam;

        rating = (int) (100 * maxScore / MAXRATING);
    }

    @Override
    public String toString() {
        return "FoodResultsWithGramParam{" +
                "rating=" + rating +
                ", search='" + search + '\'' +
                ", gramParam=" + gramParam +
                ", foodList=" + foodList +
                '}';
    }

    public Results getResults() {

        List<Food> withGrams = new ArrayList<>();

        for (int i = 0; i < foodList.size(); i++) {
            Food foodEntry = foodList.get(i);
            if (foodEntry.getGrams() != 0) {
                withGrams.add(foodEntry);
            }
        }

        if (getTotalScore(withGrams) > 0) {
            return getResultsByGrams(withGrams);
        } else {
            return new Results(search, 0, 0);
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

    private Results getResultsByGrams(List<Food> entries) {
        double avgCalPerGram = getAvgCalPerGram(entries);
        int calories = (int) (avgCalPerGram * gramParam);

        return new Results(search, rating, calories);
    }

}
