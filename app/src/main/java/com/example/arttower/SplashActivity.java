package com.example.arttower;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.arttower.Frame.BaseMvpActivity;


/*
* 微信
* */
public class SplashActivity extends BaseMvpActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {

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
}
