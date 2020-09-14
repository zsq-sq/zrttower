package com.example.arttower.activity;

import android.content.Intent;
import android.widget.TextView;

import com.example.arttower.Frame.BaseMvpActivity;
import com.example.arttower.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;


public class StarUpActivity extends BaseMvpActivity {


    @BindView(R.id.starup_te)
    TextView starup_te;

    @Override
    public int getLayoutId() {
        return R.layout.activity_star_up;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(StarUpActivity.this, LoginActivity.class));
                finish();
            }
        };
        timer.schedule(timerTask, 2000);


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
}
