package raymond.lukanta.com.chatpokeandroid;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import raymond.lukanta.com.chatpokeandroid.firstpage.FirstPageActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by raymondlukanta on 30/05/16.
 */
@RunWith(AndroidJUnit4.class)
public class FirstPageActivityIntentTest extends InstrumentationTestCase {
    private static final String PRODUCT_ACTIVITY_CLASS_NAME = "raymond.lukanta.com.chatpokeandroid.productpage.ProductActivity";

    @Rule
    public IntentsTestRule<FirstPageActivity> mActivityRule =
            new IntentsTestRule<>(FirstPageActivity.class);

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
    }

    @Test
    public void testClickImageToProductActivity() throws Exception {
        onView(withId(R.id.image_view_first_page_toolbar_image)).perform(click());
        intended(hasComponent(PRODUCT_ACTIVITY_CLASS_NAME));
    }

    @Test
    public void testClickTitleToProductActivity() throws Exception {
        onView(withId(R.id.text_view_first_page_toolbar_title)).perform(click());
        intended(hasComponent(PRODUCT_ACTIVITY_CLASS_NAME));
    }
}
