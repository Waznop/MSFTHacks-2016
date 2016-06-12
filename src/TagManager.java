import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Waznop on 2016-06-12.
 *
 * This is to filter out irrelevant results from image recognition
 */
public class TagManager {

    private final String link;

    private final String storageConnectionString = "DefaultEndpointsProtocol=http;" + "AccountName=applesfood;" +
            "AccountKey=oU5MRlkKRYaIjSWzaUbqUSxoBKskMmF1KSgXD+BPKPzHv59bgQfamustHYz2HyyMzaoTe11CZxrn1KRSh+NFMA==";

    public TagManager(String link) {

        this.link = link;

    }

    public String getResults(List<String> tagList) throws IOException {

        HttpClient client = HttpClientBuilder.create().build();

        List<String> legitTags = new ArrayList<>();

        for (int i = 0; i < tagList.size(); i++) {
            String tag = tagList.get(i);
            String newTag = tag.replaceAll("\\s", "%20");
            String URL = link + newTag + ".json";
            HttpGet get = new HttpGet(URL);
            HttpResponse response = client.execute(get);
            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
            Gson gson = new GsonBuilder().create();
            Foodtag foodTag = gson.fromJson(br.readLine(), Foodtag.class);
            if (foodTag.getPass() != null && foodTag.getPass()) {
                legitTags.add(foodTag.getName());
            }
        }

        if (legitTags.size() > 0) {
            return legitTags.get(0);
        } else {
            return "";
        }

    }

    public void AddEntry() throws ClientProtocolException, IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(link);

        // example
        String json = "{\"name\":\"bread\", \"pass\":true}";
        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.addHeader("Content-Type","application/json");

        CloseableHttpResponse response = client.execute(httpPost);
        client.close();
    }

}
