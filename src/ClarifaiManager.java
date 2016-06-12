import com.clarifai.api.ClarifaiClient;
import com.clarifai.api.RecognitionRequest;
import com.clarifai.api.RecognitionResult;
import com.clarifai.api.Tag;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Waznop on 2016-06-12.
 */
public class ClarifaiManager {

    private final String appId;

    private final String appSecret;

    public ClarifaiManager(String appId, String appSecret) {

        this.appId = appId;
        this.appSecret = appSecret;

    }

    public List<String> read(String url) {

        ClarifaiClient clarifai = new ClarifaiClient(appId, appSecret);
        List<String> tagList = new ArrayList<>();

        try {
            List<RecognitionResult> results = clarifai.recognize(new RecognitionRequest(new File(url)));

            for (Tag tag : results.get(0).getTags()) {
                tagList.add(tag.getName());
            }
        } catch (Exception e) {
            // rip
        }

        return tagList;

    }

}
