package com.example.arttower.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arttower.Frame.ApiConfig;
import com.example.arttower.Frame.BaseMvpActivity;
import com.example.arttower.R;
import com.example.arttower.model.SetPsdModel;
import com.example.arttower.utils.CommonUtils;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
/*
* 设置密码
* */
public class SetPsdActivity extends BaseMvpActivity<SetPsdModel> {

    @BindView(R.id.et_psd)
    EditText etPsd;
    @BindView(R.id.et_confirm_psd)
    EditText etConfirmPsd;
    @BindView(R.id.set_psd_submit)
    Button psdSumbit;
    @BindView(R.id.protocol)
    TextView protocol;
    @BindView(R.id.policy)
    TextView policy;

    private String mEtPsd;
    private String mEtConfirmPsd;
    private String mobile;//上级页面传过来的手机号

    @Override
    public int getLayoutId() {
        return R.layout.activity_set_psd;
    }

    @Override
    public void initView() {
        Bundle data = getIntent().getExtras();
        mobile = data.getString("mobile");


    }

    @Override
    public void initData() {

    }

    @Override
    public SetPsdModel getModel() {
        return new SetPsdModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.EDIT_PASSWORDBYMOBILE:
                //提交
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
    }

    @OnTextChanged({R.id.et_psd, R.id.et_confirm_psd})
    void onTextChanged() {
        mEtPsd = etPsd.getText().toString();
        mEtConfirmPsd = etConfirmPsd.getText().toString();
        if (!"".equals(mEtPsd) && !"".equals(mEtConfirmPsd)) {
            psdSumbit.setClickable(true);
        } else {
            psdSumbit.setClickable(false);
        }
    }

    @OnClick({R.id.set_psd_submit, R.id.policy, R.id.protocol})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.set_psd_submit:
                if (isCheckoutOk()) {
                    //设置密码
                    mPresenter.getData(ApiConfig.EDIT_PASSWORDBYMOBILE, mobile, mEtPsd);
                }
                break;
            case R.id.policy:

                break;
            case R.id.protocol:

                break;
        }
    }

    /**
     * 密码两次文本格式校验
     */
    private Boolean isCheckoutOk() {
        if ("".equals(mEtPsd) && mEtPsd != null) {
            Toast.makeText(this, "请输入密码(6-12为数字字母混排)", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!(mEtPsd.length() >= 6) || !(mEtPsd.length() <= 12)) {
            Toast.makeText(this, "请输入6-12为数字字母混排", Toast.LENGTH_SHORT).show();
            return false;
        }
        if ("".equals(mEtConfirmPsd) && mEtConfirmPsd != null) {
            Toast.makeText(this, "请输入确认密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!(mEtConfirmPsd.length() >= 6) || !(mEtConfirmPsd.length() <= 12)) {
            Toast.makeText(this, "请输入正确的确认密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!mEtPsd.equals(mEtConfirmPsd)) {
            Toast.makeText(this, "两次密码输入不一致，请重新输入", Toast.LENGTH_SHORT).show();
            etPsd.setText("");
            etConfirmPsd.setText("");
            return false;
        }

        boolean identify = CommonUtils.identify(this, mEtPsd);
        if (!identify) {
            Toast.makeText(this, "密码应为6-12为数字字母混排", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }
}
