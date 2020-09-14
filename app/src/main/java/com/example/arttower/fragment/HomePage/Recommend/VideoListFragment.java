package com.example.arttower.fragment.HomePage.Recommend;

import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.example.arttower.Frame.ApiConfig;
import com.example.arttower.Frame.BaseMvpFragment;
import com.example.arttower.R;
import com.example.arttower.aliplayer.view.AliyunListPlayerView;
import com.example.arttower.bean.HomeBean;
import com.example.arttower.eventbus.EventBusUtils;
import com.example.arttower.model.HomeModel;
import com.example.arttower.utils.NetWatchdog;
import com.example.arttower.utils.PauseVideoEvent;
import com.example.arttower.utils.ToastUtils;

import org.greenrobot.eventbus.Subscribe;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;

public class VideoListFragment extends BaseMvpFragment<HomeModel> {

    @BindView(R.id.list_player_view)
    AliyunListPlayerView mListPlayerView;
    @BindView(R.id.iv_back)
    ImageView mBackImageView;

    private NetWatchdog mNetWatchDog;
    private boolean mIsLoadMore = false;

    private String userId;
    private int index = 1;
    private int offset = 1;
    private int rows = 10;
    // 总共有几页
    private int total = 0;
    private PopupWindow popupWindow;
    private RelativeLayout view;
    private SparseArray<String> sparseArray;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_videolist;
    }

    @Override
    public void initView() {
        initListener();


    }

    @Override
    public void initData() {
        EventBusUtils.register(this);
        mPresenter.getData(ApiConfig.VIDEO_DATA, offset, rows);
    }

    private void refreshListDatas() {
        offset = 1;
        getDatas(offset);
    }

    private void getDatas(int offset) {
        mPresenter.getData(ApiConfig.VIDEO_DATA, offset, rows);

    }

    ;

    private void initListener() {
        mNetWatchDog = new NetWatchdog(getActivity());
        mNetWatchDog.setNetChangeListener(new MyNetChangeListener(this));
        mNetWatchDog.setNetConnectedListener(new MyNetConnectedListener(this));
        mListPlayerView.setOnRefreshDataListener(new MyOnRefreshListener(this));
        mBackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }

    @Override
    public HomeModel getModel() {
        return new HomeModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        Log.e("TAG", "onError: " + "未请求到数据");

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.VIDEO_DATA:
                HomeBean homeBean = (HomeBean) t[0];
                //total = videoInfo.getData().getTotal() / 10;
                List<HomeBean.DataBean> data = homeBean.getData();

                if (mListPlayerView!=null&&data!=null){
                    if (!mIsLoadMore){
                        sparseArray = new SparseArray<>();
                        mListPlayerView.setData(data);
                    }else {
                        sparseArray = mListPlayerView.getCorrelationTable();
                        mListPlayerView.addMoreData(data);
                    }
                    //遍历资源   添加到播放器列表中
                    int size = sparseArray.size();
                    for (int i=0;i<data.size();i++){

                        String randuuid = UUID.randomUUID().toString();
                        mListPlayerView.addUrl(data.get(i).getVideoUrl().get(0).getVideoUrl(),randuuid);
                        sparseArray.put(size+1,randuuid);

                    }
                    mListPlayerView.setCorrelationTable(sparseArray);
                }

          /*      for (int i = 0; i < data.size(); i++) {
                    List<HomeBean.DataBean.VideoUrlBean> videoUrls = data.get(i).getVideoUrl();
                    SparseArray<String> mSparseArray = null;
                    if (mListPlayerView != null && data != null) {
                        if (!mIsLoadMore) {
                            mSparseArray = new SparseArray<>();
                            mListPlayerView.setData(videoUrls);
                        } else {
                            mSparseArray = mListPlayerView.getCorrelationTable();
                            mListPlayerView.addMoreData(videoUrls);
                        }
                    }
                    //遍历资源,添加到列表播放器当中
                    int size = mSparseArray.size();
                    for (int y = 0; y < (data).size(); y++) {
                        List<HomeBean.DataBean.VideoUrlBean> vs = data.get(y).getVideoUrl();
                        for (HomeBean.DataBean.VideoUrlBean bean : vs) {
                            String randomUUID = UUID.randomUUID().toString();
                         Log.e("assssssssssssss",bean.getVideoUrl()+"||||||||||||||||||||"+randomUUID);
                            mListPlayerView.addUrl(bean.getVideoUrl(), randomUUID);
                            mSparseArray.put(size + i, randomUUID);

                        }

                    }
                    mListPlayerView.setCorrelationTable(mSparseArray);
                }
*/
                if (mListPlayerView != null) {
                    mListPlayerView.hideRefresh();
                }

                break;

        }
    }

    @Subscribe
    public void getEventBus(PauseVideoEvent event) {
        if (event != null) {
            if (event.isPlayOrPause()) {
                mListPlayerView.setOnBackground(false);
            } else {
                mListPlayerView.setOnBackground(true);
            }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            if (mListPlayerView != null) {
                mListPlayerView.setOnBackground(false);
            }
            if (mNetWatchDog != null) {
                mNetWatchDog.startWatch();
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mListPlayerView != null) {
            mListPlayerView.setOnBackground(false);
        }
        if (mNetWatchDog != null) {
            mNetWatchDog.startWatch();
        }
    }

    /*    if (index == 1) {
            // 马上去认证的弹窗
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.go_attestation, null);
            popupWindow = new PopupWindow(getContext());
            popupWindow.setContentView(inflate);
            popupWindow.setWidth(774);
            popupWindow.setHeight(700);
            //获取焦点
            popupWindow.setFocusable(true);
            //点击弹窗以外的弹窗关闭
            popupWindow.setOutsideTouchable(true);
            popupWindow.showAtLocation(inflate, Gravity.CENTER, 0, 0);
            index++;
            //popupwindow的点击事件
            View view = popupWindow.getContentView();
            ImageView down = view.findViewById(R.id.down);
            TextView go = view.findViewById(R.id.go_attestation);
            //关闭弹窗
            down.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });
            go.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "跳转到认证页面", Toast.LENGTH_SHORT).show();
                    popupWindow.dismiss();

                }
            });
        }*//*
    }
*/

    @Override
    public void onPause() {
        super.onPause();
        if (mListPlayerView != null) {
            mListPlayerView.setOnBackground(true);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mListPlayerView != null) {
            mListPlayerView.setOnBackground(true);
        }
        if (mNetWatchDog != null) {
            mNetWatchDog.stopWatch();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mListPlayerView != null) {
            mListPlayerView.destroy();
        }
    }


    /**
     * 刷新数据监听
     */
    private static class MyOnRefreshListener implements AliyunListPlayerView.OnRefreshDataListener {

        private WeakReference<VideoListFragment> weakReference;

        public MyOnRefreshListener(VideoListFragment videoListFragment) {
            weakReference = new WeakReference<>(videoListFragment);
        }

        //刷新
        @Override
        public void onRefresh() {
            VideoListFragment videoListFragment = weakReference.get();
            if (videoListFragment != null) {
                videoListFragment.onRefresh();
            }
        }


        //加载更多
        @Override
        public void onLoadMore() {
            VideoListFragment videoListFragment = weakReference.get();
            if (videoListFragment != null) {
                videoListFragment.onLoadMore();
            }
        }
    }

    private void onRefresh() {
        mIsLoadMore = false;
        refreshListDatas();
    }

    private void onLoadMore() {
        mIsLoadMore = true;
        /*if (offset < total) {

        }*/
        offset++;
        getDatas(offset);
    }


    private static class MyNetChangeListener implements NetWatchdog.NetChangeListener {

        private WeakReference<VideoListFragment> weakReference;

        public MyNetChangeListener(VideoListFragment videoListFragment) {
            weakReference = new WeakReference<>(videoListFragment);
        }

        @Override
        public void onWifiTo4G() {
            VideoListFragment videoListFragment = weakReference.get();
            if (videoListFragment != null) {
                videoListFragment.onWifiTo4G();
            }
        }

        @Override
        public void on4GToWifi() {
            VideoListFragment videoListFragment = weakReference.get();
            if (videoListFragment != null) {
                videoListFragment.on4GToWifi();
            }
        }

        @Override
        public void onNetDisconnected() {
            VideoListFragment videoListFragment = weakReference.get();
            if (videoListFragment != null) {
                videoListFragment.onNetDisconnected();
            }
        }
    }

    private void onNetDisconnected() {
    }

    private void on4GToWifi() {

    }

    private void onWifiTo4G() {
        ToastUtils.show(getActivity(), getString(R.string.alivc_operator_play));
    }

    private static class MyNetConnectedListener implements NetWatchdog.NetConnectedListener {

        private final WeakReference<VideoListFragment> weakReference;

        public MyNetConnectedListener(VideoListFragment videoListFragment) {
            weakReference = new WeakReference<>(videoListFragment);
        }

        @Override
        public void onReNetConnected(boolean isReconnect) {
            VideoListFragment videoListFragment = weakReference.get();
            if (videoListFragment != null) {
                videoListFragment.onReNetConnected(isReconnect);
            }

        }

        @Override
        public void onNetUnConnected() {
            VideoListFragment videoListFragment = weakReference.get();
            if (videoListFragment != null) {
                videoListFragment.onNetUnConnected();
            }

        }
    }

    //无网络
    private void onNetUnConnected() {
        ToastUtils.show(getActivity(), getString(R.string.alivc_player_net_unconnect));
    }

    private void onReNetConnected(boolean isReconnect) {
    }
}
