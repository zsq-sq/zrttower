package com.example.arttower.Frame;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyIntentService extends IntentService {
    public MyIntentService(String name) {
        super("my_intent_service");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Intent intent1 = new Intent();
        intent1.setAction("dialog");
        startActivity(intent1);
    }
}
