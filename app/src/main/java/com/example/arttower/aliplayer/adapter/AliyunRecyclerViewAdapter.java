package com.example.arttower.aliplayer.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.arttower.R;
import com.example.arttower.activity.OtherpageActivity;
import com.example.arttower.bean.HomeBean;
import com.example.arttower.fragment.HomePage.dialog.Share_popDialog;
import com.example.arttower.utils.image.ImageLoaderImpl;
import com.example.arttower.utils.image.ImageLoaderOptions;
import com.example.arttower.utils.image.ImageLoaderRequestListener;
import com.example.arttower.view.CommentDialog;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 * 加载首页视频数据
 *
 * */
public class AliyunRecyclerViewAdapter extends RecyclerView.Adapter<AliyunRecyclerViewAdapter.MyViewHolder> {

    private static Context mContext;
    private Point mScreenPoint = new Point();
    private List<HomeBean.DataBean> VideoUrlBean;
    private ImageView mThumb;
    private static int offset = 1;
    private static int rows = 10;
    private static String userid;
    private static String videoid;
    private static AppCompatActivity onecontext;
    private View inflate;
    private static ImageView like;
    private static String videoId;

    public AliyunRecyclerViewAdapter(Context context) {
        this.mContext = context;
        this.onecontext = (AppCompatActivity) context;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        mScreenPoint.x = displayMetrics.widthPixels;
        mScreenPoint.y = displayMetrics.heightPixels;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        inflate = LayoutInflater.from(mContext).inflate(R.layout.item_layout_list_player_recyclerview, parent, false);
        //TODO........
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        List<HomeBean.DataBean.VideoUrlBean> videoListBean = VideoUrlBean.get(position).getVideoUrl();
        String imgUrl = VideoUrlBean.get(position).getImgUrl();
        String coverUrlPath;
        if (TextUtils.isEmpty(imgUrl)) {
            coverUrlPath = imgUrl;
        } else {
            coverUrlPath = imgUrl;
        }


        //获取 userid 和videoid
        userid = VideoUrlBean.get(position).getId();
        Log.e("首页视频", "onBindViewHolder: "+userid );

       //赞的颜色
     /*   like = inflate.findViewById(R.id.img_love);
        if (dataBean.getIsPraise().equals("1")){
            like.setColorFilter(mContext.getResources().getColor(R.color.cl_FF0041));
        }else {
            like.setColorFilter(mContext.getResources().getColor(R.color.white));
        }*/

        //  给首页视频绑定数据
        mThumb = holder.getCoverView();
        holder.mData.setText(VideoUrlBean.get(position).getVideoContent());
        holder.mName.setText(VideoUrlBean.get(position).getNickName());
        Glide.with(mContext).load(VideoUrlBean.get(position).getHeadUrl()).into(holder.mImgTouXiang);
        holder.num_share.setText(VideoUrlBean.get(position).getForwardNum());
        holder.num_love.setText(VideoUrlBean.get(position).getIsPraise());
        holder.num_pinglun.setText(VideoUrlBean.get(position).getCommentNum());

        //视频id
        videoId = videoListBean.get(position).getId();

        if (mContext != null) {
            if (mContext instanceof Activity) {
                Activity activity = (Activity) mContext;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    if (!activity.isFinishing() || !activity.isDestroyed()) {
                        loadPicture(holder, coverUrlPath, mThumb);
                    }
                } else {
                    if (!activity.isFinishing()) {
                        loadPicture(holder, coverUrlPath, mThumb);
                    }
                }
            }
        }
    }

    private void loadPicture(final MyViewHolder holder, String coverPath, final ImageView iv) {
        new ImageLoaderImpl().loadImage(mContext, coverPath, new ImageLoaderOptions.Builder()
                .asBitmap()
                .placeholder(android.R.color.black)
                .thumbnail(0.1f)
                .build()
        ).listener(new ImageLoaderRequestListener<Bitmap>() {
            @Override
            public boolean onLoadFailed(String exception, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, boolean isFirstResource) {

                float aspectRatio = (float) resource.getWidth() / resource.getHeight();
                float screenRatio = mScreenPoint.x / (float) mScreenPoint.y;
                if (aspectRatio <= (9f / 16f + 0.01) && aspectRatio >= (9f / 16f - 0.01) //考虑到float值不精确的原因取一个范围值 视频比例 = 9/16
                        && (screenRatio < 9f / 16f - 0.01) //屏幕宽高比例小于9/16(长手机)
                ) {
                    float height = holder.getContainerView().getHeight();
                    float width = height * resource.getWidth() / resource.getHeight();
                    ViewGroup.LayoutParams layoutParams = iv.getLayoutParams();
                    layoutParams.width = (int) width;
                    layoutParams.height = (int) height;
                    iv.setLayoutParams(layoutParams);

                } else {
                    //获取屏幕宽度
                    float screenWith = mScreenPoint.x;
                    ViewGroup.LayoutParams layoutParams = iv.getLayoutParams();
                    //获取imageview的高度
                    float height = screenWith * resource.getHeight() / resource.getWidth();
                    layoutParams.width = (int) screenWith;
                    layoutParams.height = (int) height;
                    iv.setLayoutParams(layoutParams);
                }
                return false;
            }
        }).into(iv);
    }

    @Override
    public int getItemCount() {
        return VideoUrlBean == null ? 0 : VideoUrlBean.size();
    }

    public void setData(List<HomeBean.DataBean> videoUrlBeanList) {
        this.VideoUrlBean = videoUrlBeanList;
    }

    public void addMoreData(List<HomeBean.DataBean> videoListBeanItems) {
        this.VideoUrlBean.addAll(videoListBeanItems);
        notifyItemRangeInserted(this.VideoUrlBean.size() - videoListBeanItems.size(), videoListBeanItems.size());
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        private ImageView love;
        private ImageView share;
        private ImageView pinglun;
        private ImageView mThumb;
        private ViewGroup mRootView;
        private ImageView mImgTouXiang;
        private TextView mName;
        private TextView mData;
        private final TextView num_share;
        private final TextView num_love;
        private final TextView num_pinglun;
        private PopupWindow mPopupWindow;


        public MyViewHolder(View itemView) {
            super(itemView);
            mThumb = itemView.findViewById(R.id.img_thumb);

            mRootView = itemView.findViewById(R.id.root_view);
            mImgTouXiang = itemView.findViewById(R.id.recom_head); //头像
            mName = itemView.findViewById(R.id.recom_name);        //名字
            mData = itemView.findViewById(R.id.recom_content);     //介绍
            num_pinglun = itemView.findViewById(R.id.num_pinglun);
            num_love = itemView.findViewById(R.id.num_love);
            num_share = itemView.findViewById(R.id.num_share);


            love = itemView.findViewById(R.id.img_love);           //喜欢
            share = itemView.findViewById(R.id.img_share);         //分享
            pinglun = itemView.findViewById(R.id.img_pinglun);     //评论
            //TODO 喜欢
            love.setOnClickListener(v -> {

            });
            //TODO 分享
            share.setOnClickListener(v -> {
                Share_popDialog sharePopDialog = new Share_popDialog();
                sharePopDialog.show(onecontext.getSupportFragmentManager(), "share");
            });
            //TODO 评论
            pinglun.setOnClickListener(v -> {
               /* Intent intent = new Intent(mContext, CommentPopActivity.class);
               mContext.startActivity(intent);
               //  mContext.startService(intent);
                Log.e("评论的的的的的的的的的的的", "11111: "+"评论的的的的的的的的的的的" );*/

                CommentDialog commentDialog = new CommentDialog();
                //给Fragment发送当前id
                Bundle bundle = new Bundle();
                bundle.putString("uid", userid);
                bundle.putString("videoId",videoId);
                commentDialog.setArguments(bundle);
                commentDialog.show(onecontext.getSupportFragmentManager(), "comment");


            });
            //TODO 点击头像去他人主页
            mImgTouXiang.setOnClickListener(v -> {
                Intent intent = new Intent(mContext, OtherpageActivity.class);
                intent.putExtra("id", userid);
                mContext.startActivity(intent);
            });

        }

        public ImageView getCoverView() {
            return mThumb;
        }

        public ViewGroup getContainerView() {
            return mRootView;
        }

    }

}
