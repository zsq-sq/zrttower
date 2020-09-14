package com.example.arttower.utils;

import android.app.Activity;
import android.content.Context;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;


import java.util.SortedMap;
import java.util.TreeMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * author：congge
 * date: 2018/8/27 16:43
 * desc:支付工具类
 **/
public class PayUtils {

    /**
     * desc:支付宝支付
     * Created by congge on 2018/8/27 17:20
     * @param orderInfo 接口返回的订单
     **/
    public static void aliPay(final Activity activity, final String orderInfo, final OrderListener orderListener) {
        Observable.just(orderInfo)
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String orderInfo) throws Exception {
                        //用户在商户app内部点击付款，是否需要一个loading做为在钱包唤起之前的过渡，这个值设置为true
                        return new PayTask(activity).pay(orderInfo, true);
                    }

                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String payResult) throws Exception {
                        orderListener.onPayResult(payResult);
                    }

                });
    }


    public interface OrderListener {

        void onPayResult(String payResult);
    }


}
