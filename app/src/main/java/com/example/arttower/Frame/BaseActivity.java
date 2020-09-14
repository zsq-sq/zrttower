package com.example.arttower.Frame;

import android.content.IntentFilter;
import android.os.Bundle;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arttower.R;
import com.example.arttower.boradcast.NetStatusBroadCast;
import com.example.arttower.design.LoadingDialogWithContent;
import com.example.arttower.utils.NormalConfig;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;


import static com.example.arttower.local_utils.NetworkUtils.NETWORK_MOBILE;
import static com.example.arttower.local_utils.NetworkUtils.NETWORK_NONE;
import static com.example.arttower.local_utils.NetworkUtils.NETWORK_WIFI;
import static com.scwang.smartrefresh.layout.util.DensityUtil.px2dp;


/**
 * Created by l on 2019/6/27.
 */
public class BaseActivity extends AppCompatActivity implements NetStatusBroadCast.NetStatusListener {

    private LinearLayoutManager mManager;
    public BaseApplication mApplication;
    public NetStatusBroadCast mNetStatusBroadCast;
    private LoadingDialogWithContent mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLog("一个类：" + this.getClass().getSimpleName());
        mApplication = (BaseApplication) getApplication();
        mDialog = new LoadingDialogWithContent(this, getString(R.string.loading));
    }
    public void showLoadingDialog() {
        if (!mDialog.isShowing()) mDialog.show();
    }

    public void hideLoadingDialog() {
        if (mDialog.isShowing()) mDialog.dismiss();
    }

    public void showLog(Object content) {
        Log.e(NormalConfig.log1, content.toString());
    }

    public void showLog(boolean content) {
        Log.e(NormalConfig.log1, "" + content);
    }

    public void initRecycleView(RecyclerView recyclerView, RefreshLayout refreshLayout) {
        mManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mManager);
        if (refreshLayout != null) {
            refreshLayout.setHeaderHeight(px2dp(120));
            refreshLayout.setFooterHeight(px2dp(100));
            refreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    refresh();
                }
            });
            refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                    loadMore();
                }
            });
        }
    }

    public void registerNetWorkStatus() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        mNetStatusBroadCast = new NetStatusBroadCast();
        mNetStatusBroadCast.setNetStatusListener(this);
        registerReceiver(mNetStatusBroadCast, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDialog != null){
            if (mDialog.isShowing())mDialog.cancel();
        }
    }

    protected int getLoadType(Object[] t) {
        return (int) ((Object[]) t[1])[0];
    }

    public void refresh() {
    }

    public void loadMore() {
    }

    public void showToast(String content) {
        Toast.makeText(getApplicationContext(), content, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(String content) {
        Toast.makeText(getApplicationContext(), content, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNetChanged(int state) {
        if (state == NETWORK_MOBILE || state == NETWORK_WIFI) onNetConnected();
        else if (state == NETWORK_NONE) onNetDisConnected();
    }

    public void onNetConnected() {
    }

    public void onNetDisConnected() {
    }
}
