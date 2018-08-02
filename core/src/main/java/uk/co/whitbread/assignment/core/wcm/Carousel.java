package uk.co.whitbread.assignment.core.wcm;

import com.adobe.cq.sightly.WCMUsePojo;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Carousel extends WCMUsePojo {

    private static final Logger log = LoggerFactory.getLogger(Carousel.class);

    private static final String PROP_JSON = "carouselItemsJSON";
    private static final String JSON_PROP_TITLE = "title";
    private static final String JSON_PROP_DESCRIPTION = "description";
    private static final String JSON_PROP_IMAGE_SRC = "imageSrc";

    private final String carouselID = UUID.randomUUID().toString();
    private ArrayList<HashMap<String, String>> carouselItems = new ArrayList<>();

    @Override
    public void activate() throws Exception {

        String[] jsonObject = get(PROP_JSON, String[].class);

        // convert JSON string to object array
        if (jsonObject != null) {
            try {
                carouselItems = jsonStringToObjectArray(jsonObject);
            } catch (JSONException e) {
                log.error("Wrong Format - {}", e.getMessage(), e);
            }
        } else {
            log.error("Data is not valid JSON.");
        }
    }

    private ArrayList<HashMap<String, String>> jsonStringToObjectArray(String[] jsonString) throws JSONException {
        ArrayList<HashMap<String, String>> objectArray = new ArrayList<>();
        @SuppressWarnings("deprecation")
        JSONObject jObj;
        // generate hashmap and insert into array
        for (int i = 0; i < jsonString.length; i++) {
            jObj = new JSONObject(jsonString[i]);
            HashMap<String, String> tempHM = new HashMap<>();
            tempHM.put(JSON_PROP_TITLE, jObj.getString(JSON_PROP_TITLE));
            tempHM.put(JSON_PROP_DESCRIPTION, jObj.getString(JSON_PROP_DESCRIPTION));
            tempHM.put(JSON_PROP_IMAGE_SRC, jObj.getString(JSON_PROP_IMAGE_SRC));
            objectArray.add(tempHM);
        }
        return objectArray;
    }

    public ArrayList<HashMap<String, String>> getCarouselItems() {
        return carouselItems;
    }

    public String getCarouselID() {
        return carouselID;
    }
}
