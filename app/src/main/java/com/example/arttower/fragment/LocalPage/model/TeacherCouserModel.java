package com.example.arttower.fragment.LocalPage.model;

import android.util.Log;

import com.example.arttower.Frame.ApiConfig;
import com.example.arttower.Frame.ICommonModel;
import com.example.arttower.Frame.ICommonView;
import com.example.arttower.Frame.NetManager;

public class TeacherCouserModel implements ICommonModel {

    private int offset;
    private int rows;
    private String uid;

    @Override
    public void getData(ICommonView view, int whichApi, Object[] t) {
        NetManager mManager = NetManager.getNetManager();
        switch (whichApi) {
            //课程
            case ApiConfig.TEACHERCOUSERRLV:
                offset = (int) t[0];
                rows = (int) t[1];
                uid = (String) t[2];
                mManager.method(mManager.getNetManager().getNetService().getTeacherCouserInfo(offset, rows, uid), view, whichApi);
                break;
            //教师评价
            case ApiConfig.TEACHERAPPRAISERLV:
                mManager.method(mManager.getNetManager().getNetService().getTeacherAppraiseInfo(offset, rows, uid), view, whichApi);
                break;
            //教师答疑数据
            case ApiConfig.TEACHERANSWERRLV:
                String uid = (String) t[2];
                mManager.method(mManager.getNetManager().getNetService().getTeacherAnswerInfo(offset, rows, uid), view, whichApi);
                break;
                //课程详情
            case ApiConfig.TEACHERCOUSERDETAILS:
                String courseId = (String) t[0];
                mManager.method(mManager.getNetManager().getNetService().getouserDetails(courseId), view, whichApi);
                break;
        }
    }
}
