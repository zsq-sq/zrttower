package com.example.arttower.model;

import android.util.Log;

import com.example.arttower.Frame.ApiConfig;
import com.example.arttower.Frame.ICommonModel;
import com.example.arttower.Frame.ICommonView;
import com.example.arttower.Frame.NetManager;

public class TaGuanzhuModel implements ICommonModel {
    NetManager manager = NetManager.getNetManager();
    private int offset;
    private int rows;

    @Override
    public void getData(ICommonView view, int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.GUANZHU:
                offset = (int) t[0];
                rows = (int) t[1];
                manager.method(manager.getNetService().getTaGuanzhuInfo(offset, rows), view, whichApi);
                break;
            case ApiConfig.FENSI:
                String userId = (String) t[2];
                manager.method(manager.getNetService().getTafensiInfo(offset, rows,userId), view, whichApi);
                break;
        }

    }
}
