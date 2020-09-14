package com.example.arttower.dingwei;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import com.baidu.mapapi.map.MapView;
import com.example.arttower.Frame.BaseMvpActivity;
import com.example.arttower.R;

import butterknife.BindView;

public class MapActivity extends BaseMvpActivity {

    @BindView(R.id.dingwei)
    Button md;
    @BindView(R.id.map)
    MapView mapView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_map;
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