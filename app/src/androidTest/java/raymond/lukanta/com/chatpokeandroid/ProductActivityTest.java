package raymond.lukanta.com.chatpokeandroid;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import raymond.lukanta.com.chatpokeandroid.productpage.ProductActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by raymondlukanta on 30/05/16.
 */

@RunWith(AndroidJUnit4.class)
public class ProductActivityTest extends InstrumentationTestCase {
    @Rule
    public ActivityTestRule<ProductActivity> mActivityRule =
            new ActivityTestRule<>(ProductActivity.class);

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
    }

    @Test
    public void testProductNameIsCorrect() throws Exception {
        onView(withText("Pokeball")).check(matches(isDisplayed()));
    }

    @Test
    public void testProductPriceIsCorrect() throws Exception {
        onView(withText("$10")).check(matches(isDisplayed()));
    }

    @Test
    public void testProductDescriptionIsCorrect() throws Exception {
        onView(withText("Genuine from Japan. Bought last year from Elite Four center. No nego. Does not include Pokemon inside.")).check(matches(isDisplayed()));
    }
}
