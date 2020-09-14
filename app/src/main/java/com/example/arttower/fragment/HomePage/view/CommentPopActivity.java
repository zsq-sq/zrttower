package com.example.arttower.fragment.HomePage.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.arttower.Frame.ApiConfig;
import com.example.arttower.Frame.BaseMvpActivity;
import com.example.arttower.R;
import com.example.arttower.adapter.CommentAdapter;
import com.example.arttower.fragment.HomePage.bean.CommentBean;
import com.example.arttower.fragment.HomePage.model.CommentpopModel;

import java.util.List;

import butterknife.BindView;
/*
* 首页短视频评论
* */
public class CommentPopActivity extends BaseMvpActivity <CommentpopModel>{
    @BindView(R.id.rlv_pop)
    RecyclerView mRlv_pop;
    @BindView(R.id.poprlv_ed)
    EditText mPoprlvEd;
    @BindView(R.id.comment_biaoqing)
    ImageView mCommentBiaoqing;
    @BindView(R.id.poprlv_send)
    ImageView mPoprlvSend;
    private CommentAdapter adapter;
    private final int offset = 1;
    private final int rows = 10;

    @Override
    public int getLayoutId() {
        return R.layout.activity_comment_pop_adapter;
    }

    @Override
    public void initView() {
        this.setFinishOnTouchOutside(false);//设置窗口周围触摸不消失
        getWindow().setDimAmount(0f);//设置窗口周围透明
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.height = (int) (getWindowManager().getDefaultDisplay().getHeight() * 0.7); // 高度设置为屏幕的0.3
        params.width = (int) (getWindowManager().getDefaultDisplay().getWidth() * 1); // 宽度设置为屏幕的0.8
        getWindow().setAttributes(params);
        //接口
        mPresenter.getData(ApiConfig.HOMECOMMENT,offset,rows);
        mRlv_pop.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public void initData() {

    }

    @Override
    public CommentpopModel getModel() {
        return new CommentpopModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.HOMECOMMENT:

                CommentBean list = (CommentBean) t[0];
                List<CommentBean.DataBean.RowsBean> data = list.getData().getRows();
                adapter = new CommentAdapter(this, data);
                mRlv_pop.setAdapter(adapter);
                break;
        }
    }
}