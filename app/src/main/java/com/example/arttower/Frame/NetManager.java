package com.example.arttower.Frame;

import android.text.TextUtils;
import android.util.Log;


import com.example.arttower.Frame.convert.MyGsonConverterFactory;

import java.io.File;
import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by l on 2019/6/27.
 */
public class NetManager {
    private static volatile NetManager sNetManager;

    private NetManager() {
    }

    public static NetManager getNetManager() {
        if (sNetManager == null) {//考虑效率问题
            synchronized (NetManager.class) {
                if (sNetManager == null) {//考虑多个线程问题
                    sNetManager = new NetManager();
                }
            }
        }
        return sNetManager;
    }

    //只带参数上传
    public <T> INetService getNetService(T... t) {
        INetService service = new Retrofit.Builder()
                .baseUrl(t != null && t.length != 0 && !TextUtils.isEmpty((String) t[0]) ? (String) t[0] : NetConfig.BaseUrl)
                .addConverterFactory(MyGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(NetInterceptor.getNetInterceptor().getClientWithoutCache())
                .build().create(INetService.class);
        return service;
    }

    //上传文件
    public <T> INetService getNetShujuService(T... t) {
        INetService service = new Retrofit.Builder()
                .baseUrl(t != null && t.length != 0 && !TextUtils.isEmpty((String) t[0]) ? (String) t[0] : NetConfig.BaseUrl)
                .addConverterFactory(MyGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(NetInterceptor.getNetInterceptor().getClientWithoutCache())

                .build().create(INetService.class);
        return service;
    }

    public <T> void method(Observable<T> leftMenuInfo, final ICommonView view, final int whichApi, final int... t) {

        leftMenuInfo.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSuccess(Object value) {
                        view.onResponse(whichApi, value, (t != null && t.length != 0) ? t[0] : 0);

                    }

                    @Override
                    public void onFailed(Throwable e) {
                        view.onError(whichApi, e);

                    }
                });
    }

}
