package andy.example.model.internal.rest;

import andy.example.commons.utils.Constants;
import andy.example.model.entities.FactsResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Retrofit interface to access facts API
 *
 * @author Anand Shinde
 */
public interface FactsAPI {
    /**
     * Method to retrieve facts from FactsAPI
     *
     * @return - Facts response
     */
    @GET(Constants.URL_GET_FACTS)
    Call<FactsResponse> getFacts();
}