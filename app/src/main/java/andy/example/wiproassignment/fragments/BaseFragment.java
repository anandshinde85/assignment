package andy.example.wiproassignment.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import andy.example.commons.utils.AlertUtil;
import andy.example.commons.utils.NetworkUtil;
import andy.example.mvpviews.BaseMVPView;
import andy.example.wiproassignment.R;

public class BaseFragment extends Fragment implements BaseMVPView {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Common setup code goes here
    }

    @Override
    public void onDestroy() {
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
        if (!isAdded()) {
            return null;
        }
        return getContext();
    }

    /**
     * Method to show and hide progress dialog
     *
     * @param show - true to display progress dialog otherwise pass false
     */
    @Override
    public void showLoading(boolean show) {
        if (!isAdded()) {
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
        if (!isAdded()) {
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
        if (!isAdded()) {
            return false;
        }
        return NetworkUtil.getConnectivityStatus(getContext()) != NetworkUtil.TYPE_NOT_CONNECTED;
    }

    /**
     * Method to display error UI when network is not available
     */
    @Override
    public void showNetworkUnavailableError() {
        if (!isAdded()) {
            return;
        }
        String title = getString(R.string.no_network_title);
        String msg = getString(R.string.no_network_msg);
        new AlertUtil().showAlertDialog(getContext(), title, msg);
    }
}