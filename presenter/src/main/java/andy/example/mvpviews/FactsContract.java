package andy.example.mvpviews;

import andy.example.model.entities.FactsResponse;

/**
 * Contract to be fulfilled by both View and Presenter layer for fetching FactsAPI and displaying
 * response to user.
 *
 * @author Anand Shinde
 */
public interface FactsContract {
    /**
     * Contract to be fulfilled by underlying activity or fragment for updating UI before and after
     * fetching response.
     *
     * @author Anand Shinde
     */
    interface View extends BaseMVPView {
        /**
         * Method to handle success response.
         *
         * @param factsResponse - response from API
         */
        void onFactsSuccess(FactsResponse factsResponse);

        /**
         * Method to handle failure response.
         */
        void onFactsFailure();

        /**
         * Method to handle UI when API returns empty data.
         */
        void showEmptyError();
    }

    /**
     * Contract to be fulfilled by Presenter layer for fetching facts data from server.
     *
     * @author Anand Shinde
     */
    interface UserActionListener {
        /**
         * Method to request facts from server.
         */
        void fetchFacts();
    }
}