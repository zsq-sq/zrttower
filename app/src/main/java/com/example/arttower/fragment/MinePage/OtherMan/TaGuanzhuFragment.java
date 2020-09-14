package com.example.arttower.fragment.MinePage.OtherMan;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arttower.Frame.ApiConfig;
import com.example.arttower.Frame.BaseMvpFragment;
import com.example.arttower.R;
import com.example.arttower.adapter.TaGuanzhuAdapter;
import com.example.arttower.bean.TaGuanzhuBean;
import com.example.arttower.model.TaGuanzhuModel;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaGuanzhuFragment extends BaseMvpFragment<TaGuanzhuModel> {
    @BindView(R.id.rv_commongz)
    RecyclerView mRv_commongz;

    @BindView(R.id.rv_allgz)
    RecyclerView mRv_allgz;
    private final int offset = 1;
    private final int rows = 10;
    private TaGuanzhuAdapter taGuanzhuAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_ta_guanzhu;
    }

    @Override
    public void initView() {

        mRv_allgz.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void initData() {
        //我的关注
        mPresenter.getData(ApiConfig.GUANZHU, offset, rows);
        //共同关注

    }

    @Override
    public TaGuanzhuModel getModel() {
        return new TaGuanzhuModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.GUANZHU:
                TaGuanzhuBean taGuanzhuBean = (TaGuanzhuBean) t[0];
                List<TaGuanzhuBean.DataBean> data = taGuanzhuBean.getData();
                taGuanzhuAdapter = new TaGuanzhuAdapter(getContext(), data);
                mRv_allgz.setAdapter(taGuanzhuAdapter);
                break;
        }
    }
}
