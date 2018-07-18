package andy.example.commons.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.view.View;

import andy.example.commons.R;

/**
 * Utility class to display alert dialog.
 *
 * @author Anand Shinde
 */
public class AlertUtil {

  /**
   * Method to show snack bar for short duration
   *
   * @param parentView - root view
   * @param msg        - message to be displayed in snack bar
   */
  public void showShortSnackBar(View parentView, String msg) {
    Snackbar snackbar = Snackbar.make(parentView, msg, Snackbar.LENGTH_SHORT);
    snackbar.show();
  }

  /**
   * Method to show snack bar for long duration
   *
   * @param parentView - root view
   * @param msg        - message to be displayed in snack bar
   */
  public void showLongSnackBar(View parentView, String msg) {
    Snackbar snackbar = Snackbar.make(parentView, msg, Snackbar.LENGTH_LONG);
    snackbar.show();
  }

  /**
   * Method to show snack bar for indefinite duration
   *
   * @param parentView      - root view
   * @param msg             - message to be displayed in snack bar
   * @param actionMsg       - message to shown for action
   * @param onClickListener - onclick listener
   */
  public void showIndefiniteSnackBar(View parentView, String msg, String actionMsg, View
      .OnClickListener onClickListener) {
    Snackbar snackbar = Snackbar.make(parentView, msg, Snackbar.LENGTH_INDEFINITE);
    snackbar.setAction(actionMsg, onClickListener);
    snackbar.show();
  }

  /**
   * Method to display alert dialog
   *
   * @param context -  context
   * @param title   - title of dialog
   * @param msg     - message of dialog
   */
  public void showAlertDialog(Context context, String title, String msg) {
    final AlertDialog.Builder builder;
    builder = new AlertDialog.Builder(context, android.R.style.Theme_DeviceDefault_Dialog);
    builder.setTitle(title)
        .setMessage(msg)
        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
          }
        })
        .setCancelable(true)
        .show();
  }
}