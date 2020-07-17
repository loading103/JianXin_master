package com.congda.tianjianxin.ui.activity;

import android.content.pm.ActivityInfo;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.congda.baselibrary.base.BaseActivity;
import com.congda.baselibrary.utils.IMDensityUtil;
import com.congda.baselibrary.utils.IMStatusBarUtil;
import com.congda.tianjianxin.R;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;


public class PlayActivity extends BaseActivity {
    StandardGSYVideoPlayer videoPlayer;
    OrientationUtils orientationUtils;
    boolean isV=false;
    private int heights;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_play;
    }

    @Override
    public void initStatusBar() {
        IMStatusBarUtil.setTranslucentForImageView(this, 0, null);
    }

    @Override
    protected void initView() {
        videoPlayer =  findViewById(R.id.video_player);
        String playUrl="http://47.75.111.156/data/upload/video/1.mp4";
        videoPlayer.setUp(playUrl, true, "");
    }

    @Override
    protected void initListener() {
    }

    @Override
    protected void initData() {
        //增加封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        videoPlayer.setThumbImageView(imageView);
        //增加title
        videoPlayer.getTitleTextView().setVisibility(View.VISIBLE);
        videoPlayer.getTitleTextView().setText("2113434343434343434");
        //设置返回键
        videoPlayer.getBackButton().setVisibility(View.VISIBLE);
        //设置旋转
        orientationUtils = new OrientationUtils(this, videoPlayer);
        ViewGroup.LayoutParams layoutParams = videoPlayer.getLayoutParams();
        heights = layoutParams.height;
        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams layoutParams = videoPlayer.getLayoutParams();
                if(!isV){
                    layoutParams.width= IMDensityUtil.getScreenHeight(PlayActivity.this);
                    layoutParams.height= IMDensityUtil.getScreenWidth(PlayActivity.this);
                    isV=true;
                    videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            videoPlayer.getFullscreenButton().performClick();
                        }
                    });
                }else {
                    layoutParams.width= IMDensityUtil.getScreenHeight(PlayActivity.this);
                    layoutParams.height=heights;
                    isV=false;
                    videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                          finish();
                        }
                    });
                }
                orientationUtils.resolveByClick();
                videoPlayer.getTitleTextView().setVisibility(View.VISIBLE);
            }
        });
        //是否可以滑动调整
        videoPlayer.setIsTouchWiget(true);
        //设置返回按键功能
        videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        videoPlayer.startPlayLogic();
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoPlayer.onVideoPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoPlayer.onVideoResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    public void onBackPressed() {
        //先返回正常状态
        if (orientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            videoPlayer.getFullscreenButton().performClick();
            return;
        }
        //释放所有
        videoPlayer.setVideoAllCallBack(null);
        super.onBackPressed();
    }
}
