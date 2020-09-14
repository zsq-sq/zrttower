package com.example.arttower.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.arttower.R;
import com.example.arttower.design.CommonTitle;

public class PaymentActivity extends AppCompatActivity implements View.OnClickListener {

    private CommonTitle mZfComm;
    private ImageView mZfImage;
    private ImageView mZfWubi;
    private RelativeLayout mZfRelat;
    private RelativeLayout mZfRelative;
    /**
     * 立即支付
     */
    private Button mZfGoumai;
    private RelativeLayout mZfRelativelayout;
    /**
     * 柔和芭蕾
     */
    private TextView mZfName;
    /**
     * 4699
     */
    private TextView mZfPric;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        initView();
    }

    private void initView() {
        mZfComm = (CommonTitle) findViewById(R.id.zf_comm);
        mZfImage = (ImageView) findViewById(R.id.zf_image);
        mZfWubi = (ImageView) findViewById(R.id.zf_wubi);
        mZfRelat = (RelativeLayout) findViewById(R.id.zf_relat);
        mZfRelative = (RelativeLayout) findViewById(R.id.zf_relative);
        mZfGoumai = (Button) findViewById(R.id.zf_goumai);
        mZfGoumai.setOnClickListener(this);
        mZfRelativelayout = (RelativeLayout) findViewById(R.id.zf_relativelayout);
        mZfName = (TextView) findViewById(R.id.zf_name);
        mZfPric = (TextView) findViewById(R.id.zf_pric);

        Intent intent = getIntent();
        String price = intent.getStringExtra("price");
        String courseName = intent.getStringExtra("courseName");
        String coverImg = intent.getStringExtra("coverImg");

        Glide.with(this).load(coverImg).into(mZfImage);
        mZfName.setText(courseName);
        mZfPric.setText(price);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.zf_goumai:
                Intent intent = new Intent(PaymentActivity.this, CustomiActivity.class);

                startActivity(intent);
                break;
        }
    }
}