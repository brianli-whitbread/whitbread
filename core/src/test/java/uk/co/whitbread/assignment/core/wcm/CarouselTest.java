package uk.co.whitbread.assignment.core.wcm;

import com.adobe.cq.commerce.common.ValueMapDecorator;
import com.adobe.cq.sightly.WCMBindings;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.io.IOUtils;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import javax.script.Bindings;
import javax.script.SimpleBindings;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;

import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CarouselTest {

    private final String LOCAL_RESOURCE_CAROUSEL_ITEMS = "carouselItems.json";
    private final String WCMUSEPOJO_PROP_NAME_CAROUSEL_ITEMS_JSON = "carouselItemsJSON";

    private Carousel carousel = new Carousel();
    private SimpleBindings simpleBindings = new SimpleBindings();

    @Test
    public void testPassIfCarouselIDIsNotEmpty() {
        carousel.init(simpleBindings);
        Assert.assertTrue(!carousel.getCarouselID().isEmpty());
    }

    @Test
    public void testPassIfWrongJSONFormatReturnsArrayListWith0Items() throws Exception {
        String[] carouselItemsJSON = {"_"};
        ValueMap properties = new ValueMapDecorator(ImmutableMap.<String, Object> of(WCMUSEPOJO_PROP_NAME_CAROUSEL_ITEMS_JSON, carouselItemsJSON));
        simpleBindings.put(WCMBindings.PROPERTIES, properties);
        carousel.init(simpleBindings);
        Assert.assertTrue( carousel.getCarouselItems().size() == 0);
    }

    @Test
    public void testPassIfJsonStringReturnsArrayWith3Items() throws IOException, JSONException {
        String[] carouselItemsJSON = getCarouselItemsJsonAsStringArray();
        ValueMap properties = new ValueMapDecorator(ImmutableMap.<String, Object> of(WCMUSEPOJO_PROP_NAME_CAROUSEL_ITEMS_JSON, carouselItemsJSON));
        simpleBindings.put(WCMBindings.PROPERTIES, properties);
        carousel.init(simpleBindings);
        Assert.assertTrue( carousel.getCarouselItems().size() == 3);
    }

    @Test
    public void testPassIfWrongValidPropertyReturnsArrayListWith0Items() throws Exception {
        String[] carouselItemsJSON = getCarouselItemsJsonAsStringArray();
        ValueMap properties = new ValueMapDecorator(ImmutableMap.<String, Object> of("carouselItemsJSON#", carouselItemsJSON));
        simpleBindings.put(WCMBindings.PROPERTIES, properties);
        carousel.init(simpleBindings);
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