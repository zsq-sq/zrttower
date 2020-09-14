package com.example.arttower.fragment.LocalPage.model;


import com.example.arttower.Frame.ApiConfig;
import com.example.arttower.Frame.ICommonModel;
import com.example.arttower.Frame.ICommonView;
import com.example.arttower.Frame.NetManager;

public class InlandTeacherModel implements ICommonModel {
    NetManager mManager = NetManager.getNetManager();
    @Override
    public void getData(ICommonView view, int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.INLANDTEACHER_DATA:
                int offset = (int) t[0];
                int rows = (int) t[1];
                mManager.method(mManager.getNetManager().getNetService().getInlandTeacherInfo(offset,rows), view, whichApi);
                break;
            case ApiConfig.THEACHERBANNER:
                mManager.method(mManager.getNetManager().getNetService().getInlandteacherBannerInfo(), view, whichApi);
                break;

        }
    }
}
