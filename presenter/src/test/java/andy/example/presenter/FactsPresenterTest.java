package andy.example.presenter;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import andy.example.model.callbacks.FactsServiceCallback;
import andy.example.model.entities.FactsResponse;
import andy.example.model.entities.Row;
import andy.example.model.service.FactsService;
import andy.example.mvpviews.FactsContract;


/**
 * Test cases for Facts presenter
 *
 * @author Anand Shinde
 */

@RunWith(MockitoJUnitRunner.class)
public class FactsPresenterTest {
  @Mock
  FactsContract.View factsView;

  @Mock
  FactsService factsService;

  /**
   * @link ArgumentCaptor} is a powerful Mockito API to capture argument values and use them to
   * perform further actions or assertions on them.
   */
  @Captor
  ArgumentCaptor<FactsServiceCallback> verifyCallback;

  FactsPresenter factsPresenter;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    factsPresenter = new FactsPresenter(factsView, factsService);
  }

  /**
   * Test case to be tested when API returns facts with some data
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