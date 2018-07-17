package andy.example.model.internal.rest;

import andy.example.model.entities.FactsResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Retrofit interface to access facts API
 * @author Anand Shinde
 */
public interface FactsAPI {
  /**
   * Method to retrieve facts from FactsAPI
   * @return -
   */
  @GET("s/2iodh4vg0eortkl/facts.json")
  Call<FactsResponse> getFacts();
}