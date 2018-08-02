package uk.co.whitbread.assignment.core.wcm;

import org.apache.commons.io.IOUtils;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import javax.script.Bindings;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CarouselTest {

    private final String LOCAL_RESOURCE_CAROUSEL_ITEMS = "carouselItems.json";
    private final String WCMUSEPOJO_PROP_NAME_CAROUSEL_ITEMS_JSON = "carouselItemsJSON";

    @Test
    public void testPassIfCarouselIDIsNotEmpty() {
        Bindings bindings = mock(Bindings.class);
        Carousel carousel = new Carousel();
        carousel.init(bindings);
        Assert.assertTrue(!carousel.getCarouselID().isEmpty());
    }

    @Test
    public void testPassIfWrongJSONFormatReturnsArrayListWith0Items() throws Exception {
        Bindings bindings = mock(Bindings.class);
        String[] carouselItemsJSON = {"_"};
        when(bindings.get(WCMUSEPOJO_PROP_NAME_CAROUSEL_ITEMS_JSON)).thenReturn(carouselItemsJSON);
        Carousel carousel = new Carousel();
        carousel.init(bindings);
        Assert.assertTrue(carousel.getCarouselItems().size() == 0);
    }

    @Test
    public void testPassIfJsonStringReturnsArrayWith3Items() throws IOException, JSONException {
        Bindings bindings = mock(Bindings.class);
        String[] carouselItemsJSON = getCarouselItemsJsonAsStringArray();
        when(bindings.get(WCMUSEPOJO_PROP_NAME_CAROUSEL_ITEMS_JSON)).thenReturn(carouselItemsJSON);
        Carousel carousel = new Carousel();
        carousel.init(bindings);
        Assert.assertTrue(carousel.getCarouselItems().size() == 3);
    }

    @Test
    public void testPassIfWrongValidPropertyReturnsArrayListWith0Items() throws Exception {
        Bindings bindings = mock(Bindings.class);
        Carousel carousel = new Carousel();
        carousel.init(bindings);
        Assert.assertTrue(carousel.getCarouselItems().size() == 0);
    }

    private String[] getCarouselItemsJsonAsStringArray() throws IOException, JSONException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(LOCAL_RESOURCE_CAROUSEL_ITEMS);
        StringWriter writer = new StringWriter();
        IOUtils.copy(is, writer, "UTF-8");
        String jsonString = writer.toString();
        JSONArray jsonArray = new JSONArray(jsonString);
        return toStringArray(jsonArray);
    }

    private static String[] toStringArray(JSONArray array) {
        if (array == null)
            return null;

        String[] arr = new String[array.length()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = array.optString(i);
        }
        return arr;
    }

}