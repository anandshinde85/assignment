package andy.example.model.internal.service;

import java.util.UUID;

import andy.example.commons.utils.RestAdapter;
import andy.example.model.callbacks.FactsServiceCallback;
import andy.example.model.entities.FactsResponse;
import andy.example.model.internal.rest.FactsAPI;
import andy.example.model.service.FactsService;
import andy.example.model.utils.BaseError;
import andy.example.model.utils.BaseService;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Implementation for facts service. This class is actually used for fetching response from server
 *
 * @author Anand Shinde
 */
public class FactsServiceImpl extends BaseService<FactsResponse> implements FactsService {
  private FactsServiceCallback factsServiceCallback;

  /**
   * Method to retrieve facts.
   *
   * @param factsServiceCallback - callback handler for passing appropriate response
   */
  @Override
  public void getFacts(FactsServiceCallback factsServiceCallback) {
    this.factsServiceCallback = factsServiceCallback;
    request(UUID.randomUUID().toString());
  }

  /**
   * Handle successful response returned by service
   *
   * @param responseEntity   - specified response object
   * @param retrofitResponse - retrofit response
   * @param conversationId   - conversation id
   */
  @Override
  protected void handleResponse(FactsResponse responseEntity,
                                Response<FactsResponse> retrofitResponse, String conversationId) {
    factsServiceCallback.onSuccess(responseEntity, conversationId);
  }

  /**
   * Handle failure requests
   *
   * @param BaseError      - error object
   * @param conversationId - conversation id
   */
  @Override
  protected void handleError(BaseError BaseError, String conversationId) {
    factsServiceCallback.onFailure(conversationId);
  }

  /**
   * Method to execute service calls
   *
   * @param callback - retrofit callback
   */
  @Override
  protected void execute(Callback<FactsResponse> callback) {
    RestAdapter.getRestAdapter().create(FactsAPI.class).getFacts().enqueue(callback);
  }
}