package com.wpz.mymvpframe.view.XXX;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.aliyun.vodplayer.media.AliyunLocalSource;
import com.aliyun.vodplayer.media.AliyunPlayAuth;
import com.aliyun.vodplayer.media.IAliyunVodPlayer;
import com.aliyun.vodplayerview.utils.ScreenUtils;
import com.aliyun.vodplayerview.widget.AliyunScreenMode;
import com.aliyun.vodplayerview.widget.AliyunVodPlayerView;
import com.wpz.mymvpframe.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SkinActivity extends AppCompatActivity {

    private AliyunVodPlayerView mAliyunVodPlayerView = null;

    private SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SS");
    private List<String> logStrs = new ArrayList<>();

    private String mVid = null;
    private String mAuthinfo = null;

    private AliyunPlayAuth mPlayAuth = null;
    private AliyunLocalSource mLocalSource = null;

    private ScreenStatusController mScreenStatusController = null;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_log, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.log) {
            LayoutInflater inflater = LayoutInflater.from(this);
            View view = inflater.inflate(R.layout.view_log, null);

            TextView textview = (TextView) view.findViewById(R.id.log);
            if (mAliyunVodPlayerView != null) {
                for (String log : logStrs) {
                    textview.append("     " + log + "\n");
                }
            }
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("播放器日志：");
            alertDialog.setView(view);
            alertDialog.setPositiveButton("OK", null);
            AlertDialog alert = alertDialog.create();
            alert.show();
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin);

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
        String sdDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/test_save_cache";
        mAliyunVodPlayerView.setPlayingCache(true,sdDir,60*60 /*时长, s */,300 /*大小，MB*/);
        mAliyunVodPlayerView.setOnPreparedListener(new IAliyunVodPlayer.OnPreparedListener() {
            @Override
            public void onPrepared() {
                logStrs.add(format.format(new Date()) + " 准备成功");
                Toast.makeText(SkinActivity.this, "准备成功", Toast.LENGTH_SHORT).show();
            }
        });

        mAliyunVodPlayerView.setOnCompletionListener(new IAliyunVodPlayer.OnCompletionListener() {
            @Override
            public void onCompletion() {
                logStrs.add(format.format(new Date()) + " 播放结束");
                Toast.makeText(SkinActivity.this, "播放结束", Toast.LENGTH_SHORT).show();
            }
        });

        mAliyunVodPlayerView.setOnFirstFrameStartListener(new IAliyunVodPlayer.OnFirstFrameStartListener() {
            @Override
            public void onFirstFrameStart() {
                Map<String, String> debugInfo = mAliyunVodPlayerView.getAllDebugInfo();
                long createPts = 0;
                if (debugInfo.get("create_player") != null) {
                    String time = debugInfo.get("create_player");
                    createPts = (long) Double.parseDouble(time);
                    logStrs.add(format.format(new Date(createPts)) + " 播放创建成功");
                }
                if (debugInfo.get("open-url") != null) {
                    String time = debugInfo.get("open-url");
                    long openPts = (long) Double.parseDouble(time) + createPts;
                    logStrs.add(format.format(new Date(openPts)) + " url请求成功");
                }
                if (debugInfo.get("find-stream") != null) {
                    String time = debugInfo.get("find-stream");
                    long findPts = (long) Double.parseDouble(time) + createPts;
                    logStrs.add(format.format(new Date(findPts)) + " 请求流成功");
                }
                if (debugInfo.get("open-stream") != null) {
                    String time = debugInfo.get("open-stream");
                    long openPts = (long) Double.parseDouble(time) + createPts;
                    logStrs.add(format.format(new Date(openPts)) + " 开始传输码流");
                }
                logStrs.add(format.format(new Date()) + " 第一帧播放完成");
                logStrs.add(format.format(new Date()) + " 第一帧播放完成");
            }
        });

        mAliyunVodPlayerView.setOnChangeQualityListener(new IAliyunVodPlayer.OnChangeQualityListener() {
            @Override
            public void onChangeQualitySuccess(String finalQuality) {
                logStrs.add(format.format(new Date()) + " 切换分辨率 " + finalQuality + " 成功");
                Toast.makeText(SkinActivity.this, "切换分辨率 " + finalQuality + " 成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChangeQualityFail(int code, String msg) {
                logStrs.add(format.format(new Date()) + " 切换分辨率失败：" + msg);
                Toast.makeText(SkinActivity.this, "切换分辨率失败: " + msg, Toast.LENGTH_SHORT).show();
            }
        });

        mAliyunVodPlayerView.setOnStoppedListner(new IAliyunVodPlayer.OnStoppedListener() {
            @Override
            public void onStopped() {
                Toast.makeText(SkinActivity.this, "播放器停止成功", Toast.LENGTH_SHORT).show();
            }
        });



        mAliyunVodPlayerView.enableNativeLog();

        setPlaySource();

        mScreenStatusController = new ScreenStatusController(this);
        mScreenStatusController.setScreenStatusListener(new ScreenStatusController.ScreenStatusListener() {
            @Override
            public void onScreenOn() {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }

            @Override
            public void onScreenOff() {

            }
        });

        mScreenStatusController.startListen();
    }

    private void setPlaySource() {
        String type = getIntent().getStringExtra("type");
        if ("authInfo".equals(type)) {
            //auth方式
            //NOTE： 注意过期时间。特别是重播的时候，可能已经过期。所以重播的时候最好重新请求一次服务器。
            mVid = getIntent().getStringExtra("vid");
            String authInfo = getIntent().getStringExtra("authinfo");
            AliyunPlayAuth.AliyunPlayAuthBuilder aliyunPlayAuthBuilder = new AliyunPlayAuth.AliyunPlayAuthBuilder();
            aliyunPlayAuthBuilder.setVid(mVid);
            aliyunPlayAuthBuilder.setPlayAuth(authInfo);
            aliyunPlayAuthBuilder.setQuality(IAliyunVodPlayer.QualityValue.QUALITY_ORIGINAL);
            mPlayAuth = aliyunPlayAuthBuilder.build();
            mAliyunVodPlayerView.setAuthInfo(mPlayAuth);
        } else if ("localSource".equals(type)) {
            //本地播放
            String url = getIntent().getStringExtra("url");
            AliyunLocalSource.AliyunLocalSourceBuilder asb = new AliyunLocalSource.AliyunLocalSourceBuilder();
            asb.setSource(url);
            mLocalSource = asb.build();
            mAliyunVodPlayerView.setLocalSource(mLocalSource);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        updatePlayerViewMode();
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
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updatePlayerViewMode();
    }

    private void updatePlayerViewMode() {
        if (mAliyunVodPlayerView != null) {
            int orientation = getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {                //转为竖屏了。
                //显示状态栏
                if (Build.DEVICE.equalsIgnoreCase("mx5")
                        || Build.DEVICE.equalsIgnoreCase("Redmi Note2")
                        || Build.DEVICE.equalsIgnoreCase("Z00A_1")) {
                    getSupportActionBar().show();
                }
                this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                mAliyunVodPlayerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);

                //设置view的布局，宽高之类
                ViewGroup.LayoutParams aliVcVideoViewLayoutParams = mAliyunVodPlayerView.getLayoutParams();
                aliVcVideoViewLayoutParams.height = (int) (ScreenUtils.getWight(this) * 9.0f / 16);
                aliVcVideoViewLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;

                //设置为小屏状态
                mAliyunVodPlayerView.changeScreenMode(AliyunScreenMode.Small);
            } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {                //转到横屏了。
                //隐藏状态栏
                if (Build.DEVICE.equalsIgnoreCase("mx5")
                        || Build.DEVICE.equalsIgnoreCase("Redmi Note2")
                        || Build.DEVICE.equalsIgnoreCase("Z00A_1")) {
                    getSupportActionBar().hide();
                } else if (!(Build.DEVICE.equalsIgnoreCase("V4") && Build.MANUFACTURER.equalsIgnoreCase("Meitu"))){
                    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    mAliyunVodPlayerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                }

                //设置view的布局，宽高
                ViewGroup.LayoutParams aliVcVideoViewLayoutParams = mAliyunVodPlayerView.getLayoutParams();
                aliVcVideoViewLayoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                aliVcVideoViewLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;

                //设置为全屏状态
                mAliyunVodPlayerView.changeScreenMode(AliyunScreenMode.Full);
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAliyunVodPlayerView != null) {
            mAliyunVodPlayerView.destroy();
            mAliyunVodPlayerView = null;
        }

        mScreenStatusController.stopListen();
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
