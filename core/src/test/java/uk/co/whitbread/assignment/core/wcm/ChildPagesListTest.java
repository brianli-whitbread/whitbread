package uk.co.whitbread.assignment.core.wcm;

import com.day.cq.wcm.api.Page;
import io.wcm.testing.mock.aem.junit.AemContext;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChildPagesListTest {

    @Rule
    public final AemContext context = new AemContext();

    @Mock
    private ChildPagesList childPagesList;

    @Mock
    private Page page1 = mock(Page.class);

    @Mock
    private Page page2 = mock(Page.class);

    @Test
    public void testChildPagesCount0() {
        Assert.assertTrue(childPagesList.getChildPages().size() == 0);
    }

    @Test
    public void testChildPagesCountisGreaterThan1() {
        ArrayList<Page> cPages = new ArrayList<>();
        cPages.add(page1);
        cPages.add(page2);
        when(childPagesList.getChildPages()).thenReturn(cPages);
        Assert.assertTrue(childPagesList.getChildPages().size() == 2);
    }
}