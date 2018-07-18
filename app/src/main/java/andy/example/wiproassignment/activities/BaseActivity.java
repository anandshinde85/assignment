package andy.example.wiproassignment.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import andy.example.mvpviews.BaseMVPView;

/**
 * Parent activity of all the activities to be used in this project.
 *
 * @author Anand Shinde
 */
public class BaseActivity extends AppCompatActivity implements BaseMVPView {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // Common setup code goes here.
  }

  @Override
  protected void onDestroy() {
    // Common code to release resources goes here
    super.onDestroy();
  }

  /**
   * Returns Context of holding view
   *
   * @return context
   */
  @Override
  public Context getViewContext() {
    return this;
  }

  /**
   * Method to show and hide progress dialog
   *
   * @param show - true to display progress dialog otherwise pass false
   */
  @Override
  public void showLoading(boolean show) {
    // Common dialog with loading message goes here
  }

  /**
   * Method to show and hide progress dialog with message
   *
   * @param show - true to show otherwise false
   * @param msg  - message to be displayed
   */
  @Override
  public void showLoading(boolean show, String msg) {
    // Common dialog with supplied message goes here
  }
}