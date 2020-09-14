package com.example.arttower.Frame;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.aliyun.apsara.alivclittlevideo.net.LittleHttpManager;
import com.aliyun.common.httpfinal.QupaiHttpFinal;
import com.aliyun.private_service.PrivateService;
import com.aliyun.svideo.downloader.DownloaderManager;
import com.aliyun.sys.AlivcSdkCore;
import com.example.arttower.local_utils.DeviceUuidFactory;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import java.util.UUID;

public class BaseApplication extends MultiDexApplication {
    public static BaseApplication baseApplication;
    public UUID mUuid;
    public String mToken = "";
    public String mUserPhoto = "";
    public boolean mImIsLogin = false;
    public boolean mPlayInWifi;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        mUuid = DeviceUuidFactory.getInstance(getApplicationContext()).getDeviceUuid();
        MultiDex.install(this);
        Log.e("uuid:", mUuid + "\n" + mUuid.toString());
        UMConfigure.init(this, "5ef9492cdbc2ec08212b5a4a"
                , "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0
        umeng();

        //初始化播放器
        initHttp();
        initDownLoader();

        //短视频sdk，暂时只支持api 18以上的版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            AlivcSdkCore.register(getApplicationContext());
            AlivcSdkCore.setLogLevel(AlivcSdkCore.AlivcLogLevel.AlivcLogDebug);

        }
        // 选用AUTO页面采集模式
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
        LittleHttpManager.getInstance().init(this);
    }

    private void umeng() {
        PlatformConfig.setWeixin("wxae0b5f2cae5a6290", "39b74e943ff7f3261db7576a16aebe31");
        //豆瓣RENREN平台目前只能在服务器端配置
        PlatformConfig.setSinaWeibo("1822829346", "351e42bdf274d353dfbf3451850d3a7c", "http://sns.whalecloud.com");
        PlatformConfig.setQQZone("101883897", "450f8eecb4fcb61e9f82a1187cd2ff29");


        PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO");
      PlatformConfig.setAlipay("2015111700822536");
        PlatformConfig.setLaiwang("laiwangd497e70d4", "d497e70d4c3e4efeab1381476bac4c5e");
        PlatformConfig.setPinterest("1439206");
        PlatformConfig.setKakao("e4f60e065048eb031e235c806b31c70f");
        PlatformConfig.setDing("dingoalmlnohc0wggfedpk");
        PlatformConfig.setVKontakte("5764965","5My6SNliAaLxEm3Lyd9J");
        PlatformConfig.setDropbox("oz8v5apet3arcdy","h7p2pjbzkkxt02a");
       PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf");
       // PlatformConfig.setYnote("9c82bf470cba7bd2f1819b0ee26f86c6ce670e9b");
    }

    public static BaseApplication getInstance() {
        return baseApplication;
    }

    public static Context getAppContext() {
        return baseApplication.getApplicationContext();
    }

    private void initHttp() {
        QupaiHttpFinal.getInstance().initOkHttpFinal();
    }
    private void initDownLoader() {
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/aliyun/encryptedApp.dat";
        PrivateService.initService(this, filePath );
        DownloaderManager.getInstance().init(this);

    }

}
