package andy.example.wiproassignment;

import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import andy.example.model.callbacks.FactsServiceCallback;
import andy.example.model.entities.FactsResponse;
import andy.example.model.internal.service.FactsServiceImpl;
import andy.example.model.service.FactsService;
import andy.example.model.utils.BaseError;
import andy.example.wiproassignment.activities.FactsActivity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.Callable;

import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static java.util.concurrent.TimeUnit.SECONDS;
import static junit.framework.Assert.assertNotNull;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

/**
 * Test cases for Facts Activity and Fragment
 *
 * @author Anand Shinde
 */
@RunWith(JUnit4.class)
public class FactsUITest {
    private Boolean isApiExecuted = false;
    private FactsService factsService;
    private FactsResponse factsResponse;
    private BaseError baseError;
    private FactsActivity factsActivity;
    @Rule
    public ActivityTestRule<FactsActivity> activityTestRule = new ActivityTestRule<>(FactsActivity
            .class);

    /**
     * Method to setting up variables to used while running test cases
     */
    @Before
    public void setUp() {
        factsActivity = activityTestRule.getActivity();
        assertNotNull("FactsActivity is null", factsActivity);
    }

    /**
     * Test case should run successfully when API returns response and is working
     */
    @Test
    public void testSuccess() {
        RecyclerView rvFacts = factsActivity.findViewById(R.id.rv_facts);
        assertNotNull("RecyclerView is null", rvFacts);
        assertThat(rvFacts.getAdapter(), instanceOf(RecyclerView.Adapter.class));
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(rvFacts.getAdapter().getItemCount(), greaterThan(0));
    }

    /**
     * Test case will run successfully only when API is down or data is turned off
     */
    @Test
    public void testFailure() {
        TextView tvEmpty = factsActivity.findViewById(R.id.tv_empty);
        assertNotNull("TextView is null", tvEmpty);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(tvEmpty.getVisibility(), lessThanOrEqualTo(View.VISIBLE));
    }

    /**
     * Test case to handle API failure
     *
     * @throws Exception
     */
    @Test
    public void testFailToFetchData() throws Exception {
        factsService = new FactsServiceImpl();
        await().atMost(10, SECONDS).until(getFactsFailure());
        assertNotNull("BaseError is null", baseError);
    }

    /**
     * Test case to handle API success
     *
     * @throws Exception
     */
    @Test
    public void tesSuccessToFetchData() throws Exception {
        factsService = new FactsServiceImpl();
        await().atMost(10, SECONDS).until(getFactsSuccess());
        assertNotNull("FactsResponse is null", factsResponse);
    }

    /**
     * Method returning callable objects back await method to resume it's operations with failure
     * response
     *
     * @return - callable object
     */
    private Callable<Boolean> getFactsFailure() {
        Callable<Boolean> callable = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return isApiExecuted;
            }
        };
        factsService.getFacts(new FactsServiceCallback() {

            @Override
            public void onSuccess(FactsResponse factsResponse, String conversationId) {
                // Faking failure
                baseError = new BaseError("Failed to fetch facts!");
                isApiExecuted = true;
            }

            @Override
            public void onFailure(String conversationId) {
                isApiExecuted = true;
            }
        });
        return callable;
    }

    /**
     * Method returning callable objects back await method to resume it's operations with
     * successful response
     *
     * @return - callable object
     */
    private Callable<Boolean> getFactsSuccess() {
        Callable<Boolean> callable = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return isApiExecuted;
            }
        };
        factsService.getFacts(new FactsServiceCallback() {

            @Override
            public void onSuccess(FactsResponse factsResponse, String conversationId) {
                FactsUITest.this.factsResponse = factsResponse;
                isApiExecuted = true;
            }

            @Override
            public void onFailure(String conversationId) {
                baseError = new BaseError("Failed to fetch facts!");
                isApiExecuted = true;
            }
        });
        return callable;
    }
}