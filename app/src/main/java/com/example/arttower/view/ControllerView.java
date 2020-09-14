package com.example.arttower.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.arttower.R;
import com.example.arttower.falsedata.VideoBean;
import com.example.arttower.utils.NumUtils;
import com.example.arttower.view.autolinktextview.AutoLinkHerfManager;
import com.example.arttower.view.autolinktextview.AutoLinkTextView;
import com.scwang.smartrefresh.header.material.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.animation.Animation.INFINITE;
/*
*
 * create by libo
 * create on 2020-05-20
 * description*/


public class ControllerView extends RelativeLayout implements View.OnClickListener {
    @BindView(R.id.tv_content)
    AutoLinkTextView autoLinkTextView;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.likeview)
    LikeView likeView;

    @BindView(R.id.img_love)
    ImageView mLove;
    @BindView(R.id.img_share)
    ImageView mShare;
    @BindView(R.id.img_pinglun)
    ImageView mMconnent;
    @BindView(R.id.recom_head)
    ImageView mHead;

    private OnVideoControllerListener listener;
    private VideoBean videoData;

    public ControllerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.recommed_layout, this);
        ButterKnife.bind(this, rootView);

        ivHead.setOnClickListener(this);
        mMconnent.setOnClickListener(this);
        mShare.setOnClickListener(this);
        mLove.setOnClickListener(this);
       // ivFocus.setOnClickListener(this);
        setRotateAnim();
    }

    public void setVideoData(VideoBean videoData) {
        this.videoData = videoData;


        ivHead.setImageResource(videoData.getUserBean().getHead());
        //tvNickname.setText("@" + videoData.getUserBean().getNickName());
        AutoLinkHerfManager.setContent(videoData.getContent(), autoLinkTextView);
        mHead.setImageResource(videoData.getUserBean().getHead());
       // tvLikecount.setText(NumUtils.numberFilter(videoData.getLikeCount()));
       // tvCommentcount.setText(NumUtils.numberFilter(videoData.getCommentCount()));
       // tvSharecount.setText(NumUtils.numberFilter(videoData.getShareCount()));

       // animationView.setAnimation("like.json");


        //点赞状态
        if (videoData.isLiked()) {
            mLove.setColorFilter(getResources().getColor(R.color.cl_FF0041));
        } else {
            mLove.setColorFilter(getResources().getColor(R.color.white));
        }

       /* //关注状态
        if (videoData.isFocused()) {
               ivFocus.setVisibility(GONE);
        } else {
             ivFocus.setVisibility(VISIBLE);
        }*/
    }

    public void setListener(OnVideoControllerListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener == null) {
            return;
        }

        switch (v.getId()) {
            case R.id.iv_head:
                listener.onHeadClick();
                break;
            case R.id.img_love:
                listener.onLikeClick();
                like();
                break;
            case R.id.img_pinglun:
                listener.onCommentClick();
                break;
            case R.id.img_share:
                listener.onShareClick();
                break;
           /* case R.id.iv_focus:
                if (!videoData.isFocused()) {
                    videoData.setLiked(true);
                    ivFocus.setVisibility(GONE);
                }
                break;*/
        }
    }

/**
     * 点赞动作*/


    public void like() {
        if (!videoData.isLiked()) {
            //点赞
           /* animationView.setVisibility(VISIBLE);
            animationView.playAnimation();
            likeView.setTextColor(getResources().getColor(R.color.cl_FF0041));*/
        } else {
            //取消点赞
          /*  animationView.setVisibility(INVISIBLE);
            ivLike.setTextColor(getResources().getColor(R.color.white));*/
        }

        videoData.setLiked(!videoData.isLiked());
    }

// 循环旋转动画


    private void setRotateAnim() {
        RotateAnimation rotateAnimation = new RotateAnimation(0, 359,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setRepeatCount(INFINITE);
        rotateAnimation.setDuration(8000);
        rotateAnimation.setInterpolator(new LinearInterpolator());
      //  rlRecord.startAnimation(rotateAnimation);
    }
}
