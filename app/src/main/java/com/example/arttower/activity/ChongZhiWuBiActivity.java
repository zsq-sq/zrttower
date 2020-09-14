package com.example.arttower.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.common.utils.HttpUtil;
import com.example.arttower.Frame.ApiConfig;
import com.example.arttower.Frame.BaseMvpActivity;
import com.example.arttower.Frame.BaseObserver;
import com.example.arttower.Frame.NetManager;
import com.example.arttower.R;
import com.example.arttower.bean.AliPayOrderInfoBean;
import com.example.arttower.bean.CouserDetailsBean;
import com.example.arttower.model.BuyWubiModel;
import com.example.arttower.model.CoursedetailsModel;
import com.example.arttower.utils.PayResult;
import com.example.arttower.utils.PayUtils;
import com.example.arttower.utils.ToastUtils;
import com.youth.banner.util.LogUtils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 * 充值页面
 * */
public class ChongZhiWuBiActivity extends BaseMvpActivity<BuyWubiModel> {
    @BindView(R.id.money_one)//一元
            LinearLayout mOne;
    @BindView(R.id.money_onehundred)//十元
            LinearLayout mOneHundred;
    @BindView(R.id.money_threehundred)//三十元元
            LinearLayout mThreeHundred;
    @BindView(R.id.money_onethousand)//一百元
            LinearLayout mOnetHousand;
    @BindView(R.id.money_twothousand)//二百元
            LinearLayout mTwoHousand;
    @BindView(R.id.money_fivethousand)//五百元
            LinearLayout mFiveHousand;
    @BindView(R.id.money_button)//立即充值
            Button mButton;

    @BindView(R.id.ten_wu)//10舞币
            TextView mTenWu;
    @BindView(R.id.yibai_wu)//100舞币
            TextView mYibaiWu;
    @BindView(R.id.sanbai_wu)//300舞币
            TextView mSanBaiWu;
    @BindView(R.id.yiqian_wu)//1000舞币
            TextView mYiQianWu;
    @BindView(R.id.erqian_wu)//2000舞币
            TextView mErQianWu;
    @BindView(R.id.wuqian_wu)//5000舞币
            TextView mWuQianWu;

    @BindView(R.id.one_rmb)//1rmb
            TextView mOneRmb;
    @BindView(R.id.shi_rmb)
    TextView mSHiRmb;
    @BindView(R.id.threeten_rmb)
    TextView mThreeRmb;
    @BindView(R.id.one_hundred_rmb)
    TextView mOneHundredRmb;
    @BindView(R.id.two_hundred_rmb)
    TextView mTwoHundredRmb;
    @BindView(R.id.five_hundred_rmb)
    TextView mFiveHundredRmb;


    @Override
    public int getLayoutId() {
        return R.layout.activity_chong_zhi_wu_bi;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @SuppressLint("ResourceAsColor")
    @OnClick({R.id.money_fivethousand, R.id.money_one,
            R.id.money_onehundred, R.id.money_threehundred,
            R.id.money_onethousand, R.id.money_twothousand,R.id.money_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.money_one:

                break;
            case R.id.money_onehundred:

                break;
            case R.id.money_threehundred:

                break;
            case R.id.money_onethousand:

                break;
            case R.id.money_twothousand:

                break;
            case R.id.money_fivethousand:

                break;
            case R.id.money_button:
                //mPresenter.getData(ApiConfig.AliOrder, "12");
                String orderInfo = "alipay_sdk=alipay-sdk-java-4.5.0.ALL&app_id=2017062007529139&biz_content=%7B%22body%22%3A%22%E8%B4%AD%E4%B9%B0%E4%BA%86%E9%87%91%E5%B8%81%E7%9A%84%E8%B4%B9%E7%94%A8%22%2C%22out_trade_no%22%3A%22524acafa140548a086386c72d4e2fd0f%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E9%87%91%E5%B8%81400%E4%B8%AA%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.03%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F118.178.238.32%3A7074%2FpayAsyncNotify%2FgoldAlipay&sign=AC%2BQtXEvOHar%2BIqWMQVzWi2PDUTFijCrc834U1zCx9SN85y3xoxQfLlWMCINi2%2BUBRilK1wRN43fpHJWuFHakvduZfPA%2FM7KaqIMctD18689CbDQFmoectczgG5ypaPZNdQR0mw%2Fl8ZIWa%2B6Cg187M6ERfVpiBS6tOorvpr4AT8U8ITllW7sAsBmxd9t38qnV%2FSpHo9f0SPEGqoB0h6Ae9TTPuMXF%2B3rMTr3%2FB7y%2F2JuuFulKvpCDs2NpwV41jycmNTDAct38Qj8aDcIRJP57iHj%2BiwN7eYeuyV5LBvuQn0IrjLsJw6E%2FOICiHO%2FO5eGR7uSwVzr5W1c8UrsXOnPEg%3D%3D&sign_type=RSA2&timestamp=2020-09-13+20%3A23%3A59&version=1.0";
                PayUtils.aliPay(this, orderInfo, new PayUtils.OrderListener() {
                    @Override
                    public void onPayResult(String payResult) {
                        PayResult pr = new PayResult(payResult);
                        String rs = pr.getResultStatus();
                        String r = pr.getResult();
                        switch (rs) {
                            case "9000":
                                ToastUtils.show(ChongZhiWuBiActivity.this,"支付成功");
                                //通知接口支付成功
                                //getPresenter().alipayVerify(new VerifyBody(InfoUtils.getUserId(), rs, r, result.getExtraParam()));
                                break;

                            default:
                                ToastUtils.show(ChongZhiWuBiActivity.this,"支付失败");
                                //通知接口支付失败，取消订单
                                //getPresenter().orderCancel(new CancelBody(result.getExtraParam()));
                        }

                    }
                });
                break;

        }
    }




    @Override
    public BuyWubiModel getModel() {
        return new BuyWubiModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.AliOrder:

                //AliPayOrderInfoBean aliPayOrderInfoBean = (AliPayOrderInfoBean)t[0];
                //Log.i("aliPayOrderInfoBean1",aliPayOrderInfoBean.getMsg());
                break;
        }

    }


}