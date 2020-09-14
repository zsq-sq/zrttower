package com.example.arttower.fragment.HomePage;

import com.example.arttower.fragment.HomePage.bean.PressFabulousBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface apiservice {
    @POST("ypt/video/updateThumbsUp")
    @FormUrlEncoded
    Observable<PressFabulousBean> getPressFabulousInfo(@Field("userId") String userId, @Field("videoId") String videoId, @Field("uid") String uid);
}
