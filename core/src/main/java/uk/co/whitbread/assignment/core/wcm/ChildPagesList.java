package uk.co.whitbread.assignment.core.wcm;

import com.adobe.cq.sightly.WCMUsePojo;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.resource.Resource;

import java.util.ArrayList;
import java.util.Iterator;

public class ChildPagesList extends WCMUsePojo {

    private final String CRX_PROP_PARENT_PAGE_PATH = "parentPagePath";

    private ArrayList<Page> childPages = new ArrayList<>();

    @Override
    public void activate() throws Exception {
        String parentPagePath = getProperties().get(CRX_PROP_PARENT_PAGE_PATH, String.class);
        if (parentPagePath != null) {
            Resource pageResource = getResourceResolver().getResource(parentPagePath);
            if (pageResource != null) {
                Page parentPage = pageResource.adaptTo(Page.class);
                for (Iterator<Page> it = parentPage.listChildren(); it.hasNext(); ) {
                    childPages.add(it.next());
                }
            }
        }
    }

    public ArrayList<Page> getChildPages() {
        return childPages;
    }
}
