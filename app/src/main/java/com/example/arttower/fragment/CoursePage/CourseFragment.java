package com.example.arttower.fragment.CoursePage;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arttower.Frame.ApiConfig;
import com.example.arttower.Frame.BaseMvpFragment;
import com.example.arttower.Frame.BaseRvAdapter;
import com.example.arttower.R;
import com.example.arttower.activity.CourseCollectionActivity;
import com.example.arttower.fragment.CoursePage.bean.CourseDataBean;
import com.example.arttower.fragment.CoursePage.model.CourseModel;
import com.example.arttower.fragment.CoursePage.view.ClasslibraryActivity;
import com.example.arttower.fragment.CoursePage.view.DaShiKeActivity;
import com.example.arttower.fragment.CoursePage.view.HouDongKeActivity;
import com.example.arttower.fragment.CoursePage.view.SpecialtyCustomActivity;


import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * 课程页面
 */
public class CourseFragment extends BaseMvpFragment<CourseModel> {

    static CourseFragment fragment;
    @BindView(R.id.course_guangao)
    ImageView courseGuangao;
    @BindView(R.id.img_kechengku)
    ImageView imgKechengku;
    @BindView(R.id.txt_kck)
    TextView txtKck;
    @BindView(R.id.img_huodongke)
    ImageView imgHuodongke;
    @BindView(R.id.txt_hdk)
    TextView txtHdk;
    @BindView(R.id.img_zhuanyedz)
    ImageView imgZhuanyedz;
    @BindView(R.id.txt_zyd)
    TextView txtZyd;
    @BindView(R.id.img_dashike)
    ImageView imgDashike;
    @BindView(R.id.txt_dsk)
    TextView txtDsk;
    @BindView(R.id.rlv_course)
    RecyclerView rlvCourse;
    @BindView(R.id.rl_course1)
    RelativeLayout relative;
    private int offset = 1;
    private int rows = 10;


    private CourseRlvAdapter courseRlvAdapter;

    public static CourseFragment newInstance() {
        if (fragment == null) fragment = new CourseFragment();
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_course;
    }

    @Override
    public void initView() {
        //布局管理器
        rlvCourse.setLayoutManager(new LinearLayoutManager(getActivity()));


    }


    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.COURSE_DATA, offset, rows);

    }

    //监听事件
    @OnClick({R.id.img_zhuanyedz, R.id.img_dashike, R.id.img_kechengku, R.id.img_huodongke,R.id.rl_course1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
                case R.id.img_kechengku:

                    startActivity(new Intent(getContext(), ClasslibraryActivity.class));
                    break;

                case R.id.img_huodongke:

                    startActivity(new Intent(getContext(), HouDongKeActivity.class));
                    break;

                case R.id.img_zhuanyedz:

                    startActivity(new Intent(getContext(), SpecialtyCustomActivity.class));
                    break;
                case R.id.img_dashike:

                    startActivity(new Intent(getContext(), DaShiKeActivity.class));
                    break;
            case R.id.rl_course1:

                break;
        }
    }


    //关联m层
    @Override
    public CourseModel getModel() {
        return new CourseModel();
    }

    //错误返回
    @Override
    public void onError(int whichApi, Throwable e) {
        Log.e("TAGAAAA", "onError: ");
    }

    //成功返回
    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.COURSE_DATA:
                CourseDataBean courseDataBean = (CourseDataBean) t[0];
                List<CourseDataBean.DataBean> data = courseDataBean.getData();
                courseRlvAdapter = new CourseRlvAdapter(getContext(),data);
                rlvCourse.setAdapter(courseRlvAdapter);
                courseRlvAdapter.notifyDataSetChanged();
                break;
        }

        //条目点击
        courseRlvAdapter.setOnItemClickListener(new BaseRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(getContext(), CourseCollectionActivity.class);
                intent.putExtra("id",getId() );
                startActivity(intent);
            }
        });
    }
}
