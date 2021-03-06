package com.example.arttower.boradcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.example.arttower.local_utils.NetworkUtils;


/**
 * Created by l on 2019/6/4.
 */
public class NetStatusBroadCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            int state = NetworkUtils.getNetWorkState(context);
            if (mNetStatusListener != null) mNetStatusListener.onNetChanged(state);
        }
    }

    private NetStatusListener mNetStatusListener;

    public void setNetStatusListener(NetStatusListener pNetStatusListener) {
        mNetStatusListener = pNetStatusListener;
    }

    public interface NetStatusListener{
        void onNetChanged(int state);
    }
}
