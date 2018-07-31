package andy.example.wiproassignment.listeners;

/**
 * Interaction listener to handle/pass feedback from fragment to activity
 *
 * @author Anand Shinde
 */
public interface FactsFragmentInteractionListener {
    /**
     * Method to update toolbar title.
     *
     * @param title - updated title to be set
     */
    void updateToolbarTitle(String title);
}