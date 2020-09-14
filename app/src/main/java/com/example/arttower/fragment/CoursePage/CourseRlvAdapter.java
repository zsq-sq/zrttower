package com.example.arttower.fragment.CoursePage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.arttower.Frame.BaseRvAdapter;
import com.example.arttower.Frame.BaseRvViewHolder;
import com.example.arttower.R;
import com.example.arttower.fragment.CoursePage.bean.CourseDataBean;

import java.util.List;

import butterknife.BindView;

public class CourseRlvAdapter extends BaseRvAdapter<CourseDataBean.DataBean, CourseRlvAdapter.Holder> {
    public CourseRlvAdapter(Context context, List<CourseDataBean.DataBean> datas) {
        super(context, datas);

    }

    @Override
    protected void onBindData(Holder holder, CourseDataBean.DataBean data, int position) {
        holder.course_name.setText(data.getTagName());
        holder.course_studynum.setText(data.getId());
        Glide.with(context).load(data.getTagImgUrl()).into(holder.img_course);

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.rlv_item_course, null, false);
        return new Holder(inflate);
    }

    public class Holder extends BaseRvViewHolder {
        @BindView(R.id.img_course)
        ImageView img_course;
        @BindView(R.id.course_name)
        TextView course_name;
        @BindView(R.id.course_studynum)
        TextView course_studynum;
        public Holder(View itemView) {
            super(itemView);
        }
    }
}
