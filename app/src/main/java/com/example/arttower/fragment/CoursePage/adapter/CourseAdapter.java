package com.example.arttower.fragment.CoursePage.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.arttower.Frame.BaseRvAdapter;
import com.example.arttower.Frame.BaseRvViewHolder;
import com.example.arttower.R;
import com.example.arttower.fragment.CoursePage.bean.CourseDataBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CourseAdapter extends BaseRvAdapter<CourseDataBean.DataBean, CourseAdapter.courseViewholder> {

    public CourseAdapter(Context context, List<CourseDataBean.DataBean> datas) {
        super(context, datas);
        Log.e("TAGAAAA", "CourseAdapter: " );
    }

    @Override
    protected void onBindData(courseViewholder holder, CourseDataBean.DataBean data, int position) {
            holder.mCoursename.setText(data.getTagName());
    }


    @NonNull
    @Override
    public courseViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rlv_item_course, null,false);
        return new courseViewholder(view);
    }

    public class courseViewholder extends BaseRvViewHolder {
        @BindView(R.id.course_name)
        TextView mCoursename;
        @BindView(R.id.course_studynum)
        TextView mCoursestudynum;
        public courseViewholder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }
}
