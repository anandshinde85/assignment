package andy.example.model.callbacks;

import andy.example.model.entities.FactsResponse;

/**
 * Callback handler for facts service which will notify presenter about success and failure of
 * API call
 *
 * @author Anand Shinde
 */
public interface FactsServiceCallback {
    /**
     * Method to be called when response is success
     *
     * @param factsResponse  - instance holding facts
     * @param conversationId - unique server interaction id
     */
    void onSuccess(FactsResponse factsResponse, String conversationId);

    /**
     * Method to notify there was failure while retrieving facts
     *
     * @param conversationId - unique server interaction id
     */
    void onFailure(String conversationId);
}