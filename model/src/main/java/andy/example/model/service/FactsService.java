package andy.example.model.service;

import andy.example.model.callbacks.FactsServiceCallback;

/**
 * Service to fetch facts using retrofit API.
 *
 * @author Anand Shinde
 */
public interface FactsService {
  /**
   * Method to retrieve facts.
   *
   * @param factsServiceCallback - callback handler for passing appropriate response
   */
  void getFacts(FactsServiceCallback factsServiceCallback);
}