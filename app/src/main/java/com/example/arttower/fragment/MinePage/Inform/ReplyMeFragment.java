package com.example.arttower.fragment.MinePage.Inform;

import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arttower.Frame.ApiConfig;
import com.example.arttower.Frame.BaseMvpFragment;
import com.example.arttower.R;
import com.example.arttower.bean.AlterUserBean;
import com.example.arttower.model.HomeModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 * 回复我的
 * */
public class ReplyMeFragment extends BaseMvpFragment<HomeModel> {

    @BindView(R.id.rlv_replyme)
    RecyclerView rlvReplyme;
    private ReplyMeAdapter replyMeAdapter;
    private int offset = 1;
    private int rows = 10;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_reply_me;
    }

    @Override
    public void initView() {

        List<ReplyMeBean> list = new ArrayList<>();
        replyMeAdapter = new ReplyMeAdapter(getContext(), list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rlvReplyme.setLayoutManager(linearLayoutManager);
        rlvReplyme.setAdapter(replyMeAdapter);
    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.NEWSREPLYMY, offset, rows);
    }

    @Override
    public HomeModel getModel() {
        return null;
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        Log.e("回复我的", "onError: "+"获取回复的消息失败" );
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.NEWSREPLYMY:
            AlterUserBean alterUserBean= (AlterUserBean) t[0];
                Toast.makeText(getContext(), alterUserBean.getMsg(), Toast.LENGTH_SHORT).show();
            break;
        }
    }
}
