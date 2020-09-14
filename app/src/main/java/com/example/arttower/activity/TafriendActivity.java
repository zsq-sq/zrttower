package com.example.arttower.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.example.arttower.Frame.BaseMvpActivity;
import com.example.arttower.R;
import com.example.arttower.adapter.CommPagerAdapter;
import com.example.arttower.fragment.MinePage.OtherMan.TaFensiFragment;
import com.example.arttower.fragment.MinePage.OtherMan.TaGuanzhuFragment;
import com.example.arttower.model.HomeModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * 他的好友
 *
 * */
public class TafriendActivity extends BaseMvpActivity {

    @BindView(R.id.ta_back)
    ImageView taBack;

    @BindView(R.id.tx_ta)
    TextView txTa;

    @BindView(R.id.tab_tahaoyou)
    XTabLayout tabTahaoyou;
    private TaGuanzhuFragment taGuanzhuFragment;
    private TaFensiFragment taFensiFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_tafriend;
    }

    @Override
    public void initView() {
       taGuanzhuFragment = new TaGuanzhuFragment();
        taFensiFragment = new TaFensiFragment();
        tabTahaoyou.addTab(tabTahaoyou.newTab().setText("TA的关注"));
        tabTahaoyou.addTab(tabTahaoyou.newTab().setText("TA的粉丝"));

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame, taGuanzhuFragment)
                .add(R.id.frame, taFensiFragment)
                .show(taGuanzhuFragment)
                .hide(taFensiFragment)
                .commit();

            tabTahaoyou.addOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(XTabLayout.Tab tab) {
                if (tab.getPosition()==0){
                    getSupportFragmentManager().beginTransaction()
                            .show(taGuanzhuFragment)
                            .hide(taFensiFragment)
                            .commit();
                }else {
                    getSupportFragmentManager().beginTransaction()
                            .hide(taGuanzhuFragment)
                            .show(taFensiFragment)
                            .commit();
                }
            }

            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(XTabLayout.Tab tab) {

            }
        });



    /*    CommPagerAdapter commPagerAdapter = new CommPagerAdapter(getSupportFragmentManager(), fragments, new String[]{"TA的关注", "TA的粉丝"});
        vpTa.setAdapter(commPagerAdapter);
        tabTahaoyou.setupWithViewPager(vpTa);
        Log.e("关注", "TafriendActivity: " );*/
    }

    @Override
    public void initData() {

    }

    @Override
    public Object getModel() {
        return null;
    }

    @Override
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }*/
}
