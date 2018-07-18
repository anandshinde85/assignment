package andy.example.wiproassignment.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import andy.example.wiproassignment.R;
import andy.example.wiproassignment.fragments.FactsFragment;
import andy.example.wiproassignment.listeners.FactsFragmentInteractionListener;

/**
 * Activity to display facts UI to user.
 *
 * @author Anand Shinde
 */
public class FactsActivity extends BaseActivity implements FactsFragmentInteractionListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_facts);
    initUI();
    addFactsFragment();
  }

  /**
   * Method to initialise UI components
   */
  private void initUI() {
    Toolbar toolbar = findViewById(R.id.toolbar);
    setToolbar(toolbar);
    setToolbarTitle(getString(R.string.facts_title));
  }

  /**
   * Method to add facts fragment
   */
  private void addFactsFragment() {
    FactsFragment factsFragment = FactsFragment.newInstance();
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.fl_container, factsFragment)
        .commit();
  }

  /**
   * Method to update toolbar title.
   *
   * @param title - updated title to be set
   */
  @Override
  public void updateToolbarTitle(String title) {
    setToolbarTitle(title);
  }
}