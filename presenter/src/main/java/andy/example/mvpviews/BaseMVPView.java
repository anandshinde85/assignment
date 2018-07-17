package andy.example.mvpviews;

import android.content.Context;

/**
 * Base class to be extended by all MVPViews
 *
 * @author Anand Shinde
 */
public interface BaseMVPView {
  /**
   * Returns Context of holding view
   *
   * @return context
   */
  Context getViewContext();

  /**
   * Method to show and hide progress dialog
   *
   * @param show - true to display progress dialog otherwise pass false
   */
  void showLoading(boolean show);

  /**
   * Method to show and hide progress dialog with message
   *
   * @param show - true to show otherwise false
   * @param msg  - message to be displayed
   */
  void showLoading(boolean show, String msg);
}