package com.example.arttower.fragment.MinePage.OtherMan;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arttower.Frame.ApiConfig;
import com.example.arttower.Frame.BaseMvpFragment;
import com.example.arttower.R;
import com.example.arttower.adapter.FensiAdapter;
import com.example.arttower.bean.FenSiBean;
import com.example.arttower.model.TaGuanzhuModel;

import java.util.List;

import butterknife.BindView;


public class TaFensiFragment extends BaseMvpFragment<TaGuanzhuModel> {
    @BindView(R.id.textfensi)
    TextView mTextfensi;
    @BindView(R.id.rv_tafans)
    RecyclerView mRvTafans;

    @BindView(R.id.ta_back)
    ImageView mTaBack;
    private int offset = 1;
    private int rows = 10;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_ta_fensi;
    }

    @Override
    public void initView() {
        mRvTafans.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));



    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.FENSI, offset, rows);
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
            case ApiConfig.FENSI:
                FenSiBean fenSiBean = (FenSiBean) t[0];
                //总粉丝数量
                String num = fenSiBean.getData().getFansNum();
                mTextfensi.setText(num);
                List<FenSiBean.DataBean.YptUserDOSBean> list = fenSiBean.getData().getYptUserDOS();
                FensiAdapter adapter = new FensiAdapter(getContext(), list);
                mRvTafans.setAdapter(adapter);
                break;
        }
    }
}
