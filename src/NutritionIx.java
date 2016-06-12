import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.*;
import org.apache.http.client.HttpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Waznop on 2016-06-04.
 */
public class NutritionIx {

    private final String applicationId;

    private final String applicationKeys;

    private final String URL = "https://api.nutritionix.com/v1_1/search";

    public NutritionIx(String applicationId, String applicationKeys) {

        this.applicationId = applicationId;
        this.applicationKeys = applicationKeys;

    }

    public Results searchFood(String search) {

        HttpClient httpClient = HttpClients.createDefault();

        JSONObject json = new JSONObject();
        json.put("appId", applicationId);
        json.put("appKey", applicationKeys);
        json.put("query", search);
        JSONArray fields = new JSONArray();
        fields.put("item_name");
        fields.put("brand_name");
        fields.put("nf_calories");
        fields.put("nf_serving_weight_grams");
        json.put("fields", fields);
        JSONObject sort = new JSONObject();
        sort.put("field", "_score");
        sort.put("order", "desc");
        json.put("sort", sort);
        JSONObject filters = new JSONObject();
        filters.put("item_type", 2);
        json.put("filters", filters);

        HttpPost request = new HttpPost(URL);

        request.addHeader("content-type", "application/json");

        StringEntity params;

        try {
            params = new StringEntity(json.toString());
            request.setEntity(params);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new Results(search, 0, 0);
        }

        HttpResponse response;

        HttpEntity entity;

        try {
            response = httpClient.execute(request);
            entity = response.getEntity();

            if (entity != null) {
                String contentString = EntityUtils.toString(entity);
                JSONObject content = new JSONObject(contentString);

                double maxScore = 0;
                if (! content.isNull("max_score")) {
                    maxScore = content.getDouble("max_score");
                } else {
                    return new Results(search, 0, 0);
                }

                JSONArray items = content.getJSONArray("hits");

                List<Food> foodList = new ArrayList<>();

                for (int i = 0; i < items.length(); i++) {
                    JSONObject item = items.getJSONObject(i);

                    double score = item.getDouble("_score");

                    JSONObject details = item.getJSONObject("fields");

                    String name = details.getString("item_name");

                    String brand = details.getString("brand_name");

                    int grams = 0;
                    if (! details.isNull("nf_serving_weight_grams")) {
                        grams = details.getInt("nf_serving_weight_grams");
                    }
                    int calories = details.getInt("nf_calories");

                    Food food = new Food(name, brand, score, grams, calories);

                    foodList.add(food);
                }

                FoodResults foodResults = new FoodResults(search, maxScore, foodList);

                // System.out.println(foodResults.getResults());
                return foodResults.getResults();
            } else {
                return new Results(search, 0, 0);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new Results(search, 0, 0);
        }

    }

    public Results searchFood(String search, double gramParam) {

        HttpClient httpClient = HttpClients.createDefault();

        JSONObject json = new JSONObject();
        json.put("appId", applicationId);
        json.put("appKey", applicationKeys);
        json.put("query", search);
        JSONArray fields = new JSONArray();
        fields.put("item_name");
        fields.put("brand_name");
        fields.put("nf_calories");
        fields.put("nf_serving_weight_grams");
        json.put("fields", fields);
        JSONObject sort = new JSONObject();
        sort.put("field", "_score");
        sort.put("order", "desc");
        json.put("sort", sort);
        JSONObject filters = new JSONObject();
        filters.put("item_type", 2);
        json.put("filters", filters);

        HttpPost request = new HttpPost(URL);

        request.addHeader("content-type", "application/json");

        StringEntity params;

        try {
            params = new StringEntity(json.toString());
            request.setEntity(params);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new Results(search, 0, 0);
        }

        HttpResponse response;

        HttpEntity entity;

        try {
            response = httpClient.execute(request);
            entity = response.getEntity();

            if (entity != null) {
                String contentString = EntityUtils.toString(entity);
                JSONObject content = new JSONObject(contentString);

                double maxScore = 0;
                if (! content.isNull("max_score")) {
                    maxScore = content.getDouble("max_score");
                } else {
                    return new Results(search, 0, 0);
                }

                JSONArray items = content.getJSONArray("hits");

                List<Food> foodList = new ArrayList<>();

                for (int i = 0; i < items.length(); i++) {
                    JSONObject item = items.getJSONObject(i);

                    double score = item.getDouble("_score");

                    JSONObject details = item.getJSONObject("fields");

                    String name = details.getString("item_name");

                    String brand = details.getString("brand_name");

                    int grams = 0;
                    if (! details.isNull("nf_serving_weight_grams")) {
                        grams = details.getInt("nf_serving_weight_grams");
                    }
                    int calories = details.getInt("nf_calories");

                    Food food = new Food(name, brand, score, grams, calories);

                    foodList.add(food);
                }

                FoodResultsWithGramParam foodResults = new FoodResultsWithGramParam(search, maxScore, foodList, gramParam);

                System.out.println(foodResults.getResults());
                return foodResults.getResults();
            } else {
                return new Results(search, 0, 0);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new Results(search, 0, 0);
        }

    }

}
