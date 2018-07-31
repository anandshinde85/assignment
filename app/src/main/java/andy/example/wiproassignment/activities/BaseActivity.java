package andy.example.wiproassignment.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import andy.example.commons.utils.AlertUtil;
import andy.example.commons.utils.NetworkUtil;
import andy.example.mvpviews.BaseMVPView;
import andy.example.wiproassignment.R;

/**
 * Parent activity of all the activities to be used in this project.
 *
 * @author Anand Shinde
 */
public class BaseActivity extends AppCompatActivity implements BaseMVPView {
    protected Toolbar toolbar;

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
     * Method to set toolbar for this activity
     *
     * @param toolbar - toolbar
     */
    protected void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    /**
     * Method to set title for toolbar
     *
     * @param title - title to be set
     */
    protected void setToolbarTitle(String title) {
        if (null == toolbar) {
            return;
        }
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (null == actionBar) {
            return;
        }
        actionBar.setTitle(TextUtils.isEmpty(title) ? getString(R.string.facts_title) : title);
    }

    /**
     * Returns Context of holding view
     *
     * @return context
     */
    @Override
    public Context getViewContext() {
        if (isFinishing() || isDestroyed()) {
            return null;
        }
        return this;
    }

    /**
     * Method to show and hide progress dialog
     *
     * @param show - true to display progress dialog otherwise pass false
     */
    @Override
    public void showLoading(boolean show) {
        if (isFinishing() || isDestroyed()) {
            return;
        }
        // Common dialog without loading message goes here
    }

    /**
     * Method to show and hide progress dialog with message
     *
     * @param show - true to show otherwise false
     * @param msg  - message to be displayed
     */
    @Override
    public void showLoading(boolean show, String msg) {
        if (isFinishing() || isDestroyed()) {
            return;
        }
        // Common dialog with supplied message goes here
    }

    /**
     * Method to check if device is connected to network
     *
     * @return - true if network is connected otherwise returns false
     */
    @Override
    public boolean isConnectedToNetwork() {
        if (isFinishing() || isDestroyed()) {
            return false;
        }
        return NetworkUtil.getConnectivityStatus(this) != NetworkUtil.TYPE_NOT_CONNECTED;
    }

    /**
     * Method to display error UI when network is not available
     */
    @Override
    public void showNetworkUnavailableError() {
        if (isFinishing() || isDestroyed()) {
            return;
        }
        String title = getString(R.string.no_network_title);
        String msg = getString(R.string.no_network_msg);
        new AlertUtil().showAlertDialog(this, title, msg);
    }
}