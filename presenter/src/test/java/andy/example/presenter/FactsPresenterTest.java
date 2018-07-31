package andy.example.presenter;

import andy.example.model.callbacks.FactsServiceCallback;
import andy.example.model.entities.FactsResponse;
import andy.example.model.entities.Row;
import andy.example.model.service.FactsService;
import andy.example.mvpviews.FactsContract;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


/**
 * Test cases for Facts presenter
 *
 * @author Anand Shinde
 */

@RunWith(MockitoJUnitRunner.class)
public class FactsPresenterTest {
    @Mock
    private FactsContract.View factsView;

    @Mock
    private FactsService factsService;

    /**
     * @link ArgumentCaptor} is a powerful Mockito API to capture argument values and use them to
     * perform further actions or assertions on them.
     */
    @Captor
    private ArgumentCaptor<FactsServiceCallback> verifyCallback;

    private FactsPresenter factsPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        // Initialising facts presenter
        factsPresenter = new FactsPresenter(factsView, factsService);
    }

    /**
     * Test case to be run successfully when API returns facts with some data
     */
    @Test
    public void testSucceedToFetchFactsWithData() {
        FactsResponse factsResponse = generateFactsWithData();
        String conversationId = "";

        factsPresenter.fetchFacts();

        // Facts view should call showLoading true method
        Mockito.verify(factsView).showLoading(true);

        // factsService then call get facts method which will be capture in callback
        Mockito.verify(factsService).getFacts(verifyCallback.capture());

        // Mocking/ capturing onSuccess method on argument captor
        verifyCallback.getValue().onSuccess(factsResponse, conversationId);

        // Verifying factsView calling onFactsSuccess method
        Mockito.verify(factsView).onFactsSuccess(factsResponse);
    }

    /**
     * Test case to be ran successfully when API return facts with no data
     */
    @Test
    public void testSucceedToFetchFactsWithNoData() {
        // Facts response with title and rows == null
        FactsResponse factsResponse = new FactsResponse();
        String conversationId = "";

        factsPresenter.fetchFacts();
        Mockito.verify(factsView).showLoading(true);
        Mockito.verify(factsService).getFacts(verifyCallback.capture());

        // This will return facts response with rows == null
        verifyCallback.getValue().onSuccess(factsResponse, conversationId);

        // Verify since rows are null factsPresenter calls factsView's showEmptyError
        Mockito.verify(factsView).showEmptyError();
    }

    /**
     * Test case to be run successfully when fetching facts from server fails
     */
    @Test
    public void testFailedToFetchFacts() {
        String conversationId = "";

        factsPresenter.fetchFacts();
        Mockito.verify(factsView).showLoading(true);
        Mockito.verify(factsService).getFacts(verifyCallback.capture());

        // Mocking/capturing onFailure method on argument captor
        verifyCallback.getValue().onFailure(conversationId);

        // Verify facts view call onFactsFailure method
        Mockito.verify(factsView).onFactsFailure();
    }

    /**
     * Method to generate and return facts
     *
     * @return - factsResponse
     */
    private FactsResponse generateFactsWithData() {
        FactsResponse factsResponse = new FactsResponse();
        factsResponse.setTitle("Some Title");
        List<Row> rows = new ArrayList<>();
        rows.add(new Row());
        factsResponse.setRows(rows);
        return factsResponse;
    }
}