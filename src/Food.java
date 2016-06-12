/**
 * Created by Waznop on 2016-06-04.
 */
public class Food {

    private String name;

    private String brand;

    private double score;

    private int grams;

    private int calories;

    private double calPerGram;

    public Food(String name, String brand, double score, int grams, int calories) {
        this.name = name;
        this.brand = brand;
        this.score = score;
        this.grams = grams;
        this.calories = calories;

        if (grams != 0) {
            this.calPerGram = calories / grams;
        }
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", score=" + score +
                ", grams=" + grams +
                ", calories=" + calories +
                ", calPerGram=" + calPerGram +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public double getScore() {
        return score;
    }

    public int getGrams() {
        return grams;
    }

    public int getCalories() {
        return calories;
    }

    public double getCalPerGram() {
        return calPerGram;
    }
}
