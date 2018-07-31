package andy.example.presenter;

import andy.example.commons.utils.CollectionUtil;
import andy.example.model.callbacks.FactsServiceCallback;
import andy.example.model.entities.FactsResponse;
import andy.example.model.internal.service.FactsServiceImpl;
import andy.example.model.service.FactsService;
import andy.example.mvpviews.FactsContract;

/**
 * Presenter for initiating requests to fetcg facts and passing on the appropriate
 * response to view layer.
 *
 * @author Anand Shinde
 */
public class FactsPresenter implements BasePresenter, FactsContract.UserActionListener,
        FactsServiceCallback {
    private final FactsContract.View factsView;
    private final FactsService factsService;

    public FactsPresenter(FactsContract.View factsView) {
        this.factsView = factsView;
        factsService = new FactsServiceImpl();
    }

    /**
     * STRICTLY USED WHILE CONSTRUCTING TEST CASE OBJECT
     *
     * @param factsView - mocked facts view
     * @param factsView - mocked facts view
     */
    public FactsPresenter(FactsContract.View factsView, FactsService factsService) {
        this.factsView = factsView;
        this.factsService = factsService;
    }

    /**
     * To be called in onStart method of activity/fragment
     */
    @Override
    public void start() {

    }

    /**
     * To be called in onStop method of activity/fragment
     */
    @Override
    public void stop() {

    }

    /**
     * Method to be called when response is success
     *
     * @param factsResponse  - instance holding facts
     * @param conversationId - unique server interaction id
     */
    @Override
    public void onSuccess(FactsResponse factsResponse, String conversationId) {
        if (null == factsView) {
            return;
        }
        factsView.showLoading(false);
        if (null == factsResponse || CollectionUtil.isEmpty(factsResponse.getRows())) {
            factsView.showEmptyError();
            return;
        }

        factsView.onFactsSuccess(factsResponse);
    }

    /**
     * Method to notify there was failure while retrieving facts
     *
     * @param conversationId - unique server interaction id
     */
    @Override
    public void onFailure(String conversationId) {
        if (null == factsView) {
            return;
        }
        factsView.onFactsFailure();
    }

    /**
     * Method to request facts from server.
     */
    @Override
    public void fetchFacts() {
        if (null != factsView) {
            factsView.showLoading(true);
        }
        factsService.getFacts(this);
    }
}