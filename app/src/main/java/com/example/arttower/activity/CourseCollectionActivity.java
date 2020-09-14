package com.example.arttower.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arttower.Frame.ApiConfig;
import com.example.arttower.Frame.BaseMvpActivity;
import com.example.arttower.Frame.BaseRvAdapter;
import com.example.arttower.R;
import com.example.arttower.adapter.CompilationsAdapter;
import com.example.arttower.bean.ParticularsBean;
import com.example.arttower.fragment.CoursePage.model.PartMolder;
import com.example.arttower.utils.StatusBarUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

// TODO 合集名称
public class CourseCollectionActivity extends BaseMvpActivity<PartMolder> {

    @BindView(R.id.bg_course)
    ImageView bgCourse;
    @BindView(R.id.back_course)
    ImageView backCourse;
    @BindView(R.id.rlv_collection)
    RecyclerView rlv_collection;

    private int offset = 1;
    private int rows = 10;
    private CompilationsAdapter adapter;
    private List<ParticularsBean.DataBean.RowsBean> rows1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_course_collection;
    }

    @Override
    public void initView() {
        StatusBarUtil.setTranslucentStatus(this);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");


        //布局管理器
        rlv_collection.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.XIANGQINg_DATA, offset, rows);
    }

    @Override
    public PartMolder getModel() {
        return new PartMolder();
    }

    @Override
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.XIANGQINg_DATA:
                ParticularsBean parti = (ParticularsBean) t[0];
                rows1 = parti.getData().getRows();
                adapter = new CompilationsAdapter(this, rows1);
                rlv_collection.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                break;
        }
        adapter.setOnItemClickListener(new BaseRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(CourseCollectionActivity.this, BuyCourseActivity.class);
                intent.putExtra("courseName",rows1.get(position).getCourseName());
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.bg_course, R.id.back_course})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bg_course:
                break;
            case R.id.back_course:
                finish();
                break;
        }
    }
}
