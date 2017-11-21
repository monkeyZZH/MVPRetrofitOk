package com.wpz.mymvpframe.view.XXX;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aliyun.vodplayer.media.AliyunErrorCode;
import com.aliyun.vodplayer.media.AliyunPlayAuth;
import com.aliyun.vodplayer.media.AliyunVodPlayer;
import com.aliyun.vodplayer.media.IAliyunVodPlayer;
import com.wpz.mymvpframe.R;
import com.wpz.mymvpframe.base.BaseActivity;
import com.wpz.mymvpframe.model.bean.VideoBean;
import com.wpz.mymvpframe.presenter.BoSkinPresenter;
import com.wpz.mymvpframe.view.iview.IBoSkinView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoSkinActivity extends BaseActivity<BoSkinPresenter> implements IBoSkinView,View.OnClickListener {

    private static final String TAG = NoSkinActivity.class.getSimpleName();

    private SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SS");

    private SurfaceView surfaceView;


    private TextView positionTxt;
    private TextView durationTxt;
    private SeekBar progressBar;


    private List<String> logStrs = new ArrayList<>();

    private AliyunVodPlayer aliyunVodPlayer;
    private boolean inSeek = false;
    private AliyunPlayAuth mPlayAuth;


    public NoSkinActivity() {
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_log, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.log) {

            LayoutInflater inflater= LayoutInflater.from(this);
            View view=inflater.inflate(R.layout.view_log, null);

            TextView textview=(TextView)view.findViewById(R.id.log);
            if(aliyunVodPlayer != null) {
                for(String log: logStrs) {
                    textview.append("     "+log+"\n");
                }
            }
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("播放器日志：");
            alertDialog.setView(view);
            alertDialog.setPositiveButton("OK",null);
            AlertDialog alert = alertDialog.create();
            alert.show();
        }
        return true;
    }




    private Button btn;

      private Handler mHandler=new Handler(){
              @Override
              public void handleMessage(Message msg) {


                  Bundle bundle = (Bundle) msg.obj;
                  String r = bundle.getString("r");
                  String p = bundle.getString("p");

                    diyi(r,p);

              }
          };
    private int num = 0;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);

        positionTxt = (TextView) findViewById(R.id.currentPosition);
        durationTxt = (TextView) findViewById(R.id.totalDuration);
        progressBar = (SeekBar) findViewById(R.id.progress);
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Log.d(TAG, "surfaceCreated");
                aliyunVodPlayer.setDisplay(holder);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                Log.d(TAG, "surfaceChanged");
                aliyunVodPlayer.surfaceChanged();
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                Log.d(TAG, "surfaceDestroyed");
            }
        });
        //装视频的id列表，也就是请求视频信息的那个参数
        list = new ArrayList<>();
        list.add("aacf07e1609243bfaeed24533ba478fc");
        list.add("f645ee79b7584057befb0c6743a75afb");

        if(getIntent().getIntExtra("num",0) == 0){

            mPresenter.getVideoData(this,list.get(num));
        }else{
            num = getIntent().getIntExtra("num",0);
            mPresenter.getVideoData(this,list.get(num));
        }

        initVodPlayer();

    }


    @Override
    protected void createPresenter() {
            mPresenter = new BoSkinPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_noskin;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDatas() {

    }

    //第一次提取
    public void diyi( String r,String p ){


        AliyunPlayAuth.AliyunPlayAuthBuilder aliyunPlayAuthBuilder = new AliyunPlayAuth.AliyunPlayAuthBuilder();
        aliyunPlayAuthBuilder.setVid(r);
        aliyunPlayAuthBuilder.setPlayAuth(p);
        aliyunPlayAuthBuilder.setQuality(IAliyunVodPlayer.QualityValue.QUALITY_STAND);
        mPlayAuth = aliyunPlayAuthBuilder.build();
        logStrs.add(format.format(new Date())+" 准备请求码流");


            aliyunVodPlayer.prepareAsync(mPlayAuth);


    }



    private void initVodPlayer() {
        aliyunVodPlayer = new AliyunVodPlayer(this);
        String sdDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/test_save_cache";
       //边下边缓存配置
        aliyunVodPlayer.setPlayingCache(true,sdDir,60 * 60 /*时长, s */,300 /*大小，MB*/);
        //设置视频准备结束的监听事件
        aliyunVodPlayer.setOnPreparedListener(new IAliyunVodPlayer.OnPreparedListener() {
            @Override
            public void onPrepared() {
                Toast.makeText(NoSkinActivity.this, "准备成功", Toast.LENGTH_SHORT).show();
                logStrs.add(format.format(new Date())  + " 准备成功");
                //准备成功之后可以调用start方法开始播放
                inSeek = false;

                showVideoProgressInfo();

                aliyunVodPlayer.start();
                logStrs.add(format.format(new Date()) + " 开始请求播放");

            }
        });

        aliyunVodPlayer.setOnErrorListener(new IAliyunVodPlayer.OnErrorListener() {
            @Override
            public void onError(int arg0, int arg1, String msg) {

                if(arg0 == AliyunErrorCode.ALIVC_ERR_INVALID_INPUTFILE.getCode()){
                    //当播放本地报错4003的时候，可能是文件地址不对，也有可能是没有权限。
                    //如果是没有权限导致的，就做一个权限的错误提示。其他还是正常提示：
                    int storagePermissionRet = ContextCompat.checkSelfPermission(NoSkinActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    if(storagePermissionRet != PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(NoSkinActivity.this, "失败！！！！原因：无本地存储访问权限", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                Toast.makeText(NoSkinActivity.this, "失败！！！！原因：" + msg, Toast.LENGTH_SHORT).show();
            }
        });
        //结束监听
        aliyunVodPlayer.setOnCompletionListener(new IAliyunVodPlayer.OnCompletionListener() {
            @Override
            public void onCompletion() {
//                Log.d(TAG, "播放结束--- ");
                showVideoProgressInfo();
                stopUpdateTimer();

                num++;

                if(num>=list.size())
                {
                    Intent intent = new Intent(NoSkinActivity.this,lei.class);
                    startActivity(intent);

                }else{
                    Intent intent = new Intent(NoSkinActivity.this,NoSkinActivity.class);
                    intent.putExtra("num",num);
                    startActivity(intent);
                    finish();
                }



            }
        });
        //设置拖动结束监听事件
        aliyunVodPlayer.setOnSeekCompleteListener(new IAliyunVodPlayer.OnSeekCompleteListener() {
            @Override
            public void onSeekComplete() {
                logStrs.add(format.format(new Date()) + " seek结束");
                inSeek = false;
            }
        });

        //设置视频整体缓冲进度的监听事件
        aliyunVodPlayer.setOnBufferingUpdateListener(new IAliyunVodPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(int percent) {
                Log.d(TAG, "缓冲进度更新--- " + percent);
                updateBufferingProgress(percent);
            }
        });

        aliyunVodPlayer.enableNativeLog();

    }

    private void updateBufferingProgress(int percent) {
        int duration = (int) aliyunVodPlayer.getDuration();
        int secondaryProgress = (int) (duration * percent * 1.0f / 100);
        Log.d(TAG, "lfj0918 duration = " + duration + " , buffer percent = " +percent + " , secondaryProgress =" + secondaryProgress);
        progressBar.setSecondaryProgress(secondaryProgress);
    }

    private void showVideoProgressInfo() {
        if((aliyunVodPlayer.getPlayerState().equals(AliyunVodPlayer.PlayerState.Started)
                || aliyunVodPlayer.getPlayerState().equals(AliyunVodPlayer.PlayerState.Replay))
                && !inSeek) {
            int curPosition = (int) aliyunVodPlayer.getCurrentPosition();
            positionTxt.setText(Formatter.formatTime(curPosition));
            int duration = (int) aliyunVodPlayer.getDuration();
            durationTxt.setText(Formatter.formatTime(duration));
            Log.d(TAG, "lfj0918 duration = " + duration + " , curPosition = " + curPosition );
            progressBar.setMax(duration);
            progressBar.setProgress(curPosition);
        }

        startUpdateTimer();
    }

    private void startUpdateTimer() {
        Log.d(TAG, "播放进度更新--- ");
        progressUpdateTimer.removeMessages(0);
        progressUpdateTimer.sendEmptyMessageDelayed(0, 1000);
    }

    private void stopUpdateTimer() {
        progressUpdateTimer.removeMessages(0);
    }

    private Handler progressUpdateTimer = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            showVideoProgressInfo();
        }
    };

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        aliyunVodPlayer.stop();
        aliyunVodPlayer.release();
        stopUpdateTimer();
        progressUpdateTimer = null;
        super.onDestroy();
    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public void onsuccess(Object o) {
        VideoBean videoBean = (VideoBean) o ;
        final String playAuth = videoBean.getData().getPlayAuth();
        final String requestId = videoBean.getData().getRequestId();

                Bundle bundle = new Bundle();
                bundle.putString("p",playAuth);
                bundle.putString("r",requestId);
                Message msg= Message.obtain();
                msg.what=1;
                msg.obj = bundle;
                mHandler.sendMessage(msg);

    }

    @Override
    public void onError(Object o) {

    }
}
