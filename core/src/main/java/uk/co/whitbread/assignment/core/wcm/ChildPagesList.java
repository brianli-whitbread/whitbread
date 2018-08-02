package uk.co.whitbread.assignment.core.wcm;

import com.adobe.cq.sightly.WCMUsePojo;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.resource.Resource;

import java.util.ArrayList;

public class ChildPagesList extends WCMUsePojo {

    private final String CRX_PROP_PARENT_PAGE_PATH = "parentPagePath";

    private ArrayList<Page> childPages = new ArrayList<>();

    @Override
    public void activate() throws Exception {
        setChildPages(getProperties().get(CRX_PROP_PARENT_PAGE_PATH, String.class));
    }

    private void setChildPages(String parentPagePath) {
        if (parentPagePath != null) {
            Resource pageResource = getResourceResolver().getResource(parentPagePath);
            if (pageResource != null) {
                Page parentPage = pageResource.adaptTo(Page.class);
                if (parentPage != null) {
                    parentPage.listChildren().forEachRemaining(childPages::add);
                }
            }
        }
    }

    public ArrayList<Page> getChildPages() {
        return childPages;
    }
}
