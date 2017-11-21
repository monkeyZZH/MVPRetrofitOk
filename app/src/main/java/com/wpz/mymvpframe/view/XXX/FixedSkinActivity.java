package com.wpz.mymvpframe.view.XXX;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.aliyun.vodplayer.media.AliyunLocalSource;
import com.aliyun.vodplayer.media.AliyunPlayAuth;
import com.aliyun.vodplayer.media.IAliyunVodPlayer;
import com.aliyun.vodplayerview.widget.AliyunVodPlayerView;
import com.wpz.mymvpframe.R;

public class FixedSkinActivity extends AppCompatActivity {

    private AliyunVodPlayerView mAliyunVodPlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        /*findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAliyunVodPlayerView.start();
            }
        });
        findViewById(R.id.pause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAliyunVodPlayerView.pause();
            }
        });

        findViewById(R.id.replay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAliyunVodPlayerView.rePlay();
            }
        });*/

        mAliyunVodPlayerView = (AliyunVodPlayerView) findViewById(R.id.video_view);
        mAliyunVodPlayerView.setOnPreparedListener(new IAliyunVodPlayer.OnPreparedListener() {
            @Override
            public void onPrepared() {
                Toast.makeText(FixedSkinActivity.this, "准备成功", Toast.LENGTH_SHORT).show();
            }
        });

        mAliyunVodPlayerView.setLockPortraitMode(new IAliyunVodPlayer.LockPortraitListener() {
            @Override
            public void onLockScreenMode(int screenMode) {
                if(screenMode == 1) //跳到小屏
                {
                    if(Build.DEVICE.equalsIgnoreCase("mx5")
                            || Build.DEVICE.equalsIgnoreCase("Redmi Note2")
                            || Build.DEVICE.equalsIgnoreCase("Z00A_1")) {
                        getSupportActionBar().show();
                    }
                    FixedSkinActivity.this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                } else {
                    if(Build.DEVICE.equalsIgnoreCase("mx5")
                            || Build.DEVICE.equalsIgnoreCase("Redmi Note2")
                            || Build.DEVICE.equalsIgnoreCase("Z00A_1")) {
                        getSupportActionBar().hide();
                    } else if (!(Build.DEVICE.equalsIgnoreCase("V4") && Build.MANUFACTURER.equalsIgnoreCase("Meitu"))) {
                        FixedSkinActivity.this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                        mAliyunVodPlayerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                    }
                }
            }
        });

        mAliyunVodPlayerView.enableNativeLog();

        setPlaySource();
    }

    private void setPlaySource() {
        String type = getIntent().getStringExtra("type");
            if("authInfo".equals(type)) {
            //auth方式
            //NOTE： 注意过期时间。特别是重播的时候，可能已经过期。所以重播的时候最好重新请求一次服务器。
            String vid = getIntent().getStringExtra("vid");
            String authInfo = getIntent().getStringExtra("authinfo");
            AliyunPlayAuth.AliyunPlayAuthBuilder aliyunPlayAuthBuilder = new AliyunPlayAuth.AliyunPlayAuthBuilder();
            aliyunPlayAuthBuilder.setVid(vid);
            aliyunPlayAuthBuilder.setPlayAuth(authInfo);
            aliyunPlayAuthBuilder.setQuality(IAliyunVodPlayer.QualityValue.QUALITY_ORIGINAL);
            mAliyunVodPlayerView.setAuthInfo(aliyunPlayAuthBuilder.build());
        }else if("localSource".equals(type)){
            //本地播放
            String url = getIntent().getStringExtra("url");
            AliyunLocalSource.AliyunLocalSourceBuilder asb = new AliyunLocalSource.AliyunLocalSourceBuilder();
            asb.setSource(url);
            mAliyunVodPlayerView.setLocalSource(asb.build());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if (mAliyunVodPlayerView != null) {
            mAliyunVodPlayerView.resume();
        }

    }


    @Override
    protected void onStop() {
        super.onStop();

        if (mAliyunVodPlayerView != null) {
            mAliyunVodPlayerView.stop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAliyunVodPlayerView != null) {
            mAliyunVodPlayerView.destroy();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(mAliyunVodPlayerView != null) {
            boolean handler = mAliyunVodPlayerView.onKeyDown(keyCode, event);
            if (!handler) {
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
