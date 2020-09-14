package com.example.arttower.fragment.MinePage.Inform;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arttower.Frame.ApiConfig;
import com.example.arttower.Frame.BaseMvpFragment;
import com.example.arttower.R;
import com.example.arttower.model.HomeModel;
import com.fasterxml.jackson.databind.ser.PropertyWriter;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * 点赞我的
 */
public class LikeMeFragment extends BaseMvpFragment<HomeModel> {
    @BindView(R.id.xiaoxi_like_My)
    RecyclerView mLike_my;
    private int offset = 1;
    private int rows = 1;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_like_me;
    }

    @Override
    public void initView() {
        mLike_my.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.LIKEMY, offset, rows);
    }

    @Override
    public HomeModel getModel() {
        return new HomeModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        switch (whichApi) {
            case ApiConfig.LIKEMY:
                Log.e("点赞我的", "onError: "+e.getMessage() );
                break;
        }
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.LIKEMY:

                break;
        }
    }
}
