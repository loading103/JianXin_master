package com.congda.baselibrary.base;

import android.Manifest;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.congda.baselibrary.R;
import com.congda.baselibrary.utils.IMLogUtil;
import com.congda.baselibrary.utils.IMStatusBarUtil;
import com.congda.baselibrary.widget.ActivityUtils;
import com.congda.baselibrary.widget.IMRefreshUtils;
import com.congda.baselibrary.widget.loading.ShowLoadiongUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * @author jinxin
 * 剑之所指，心之所向
 * @date 2019/8/4
 */
public abstract class BaseActivity extends BaseSwipeBackActivity implements EasyPermissions.PermissionCallbacks{
    String[] perms = { Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    public IMRefreshUtils  imRefreshUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initStatusBar();
        requestPermissions();
        if (useEventBus()) {
            EventBus.getDefault().register(this);//注册eventBus
        }
        imRefreshUtils=new IMRefreshUtils(this);
        initView();
        initListener();
        initData();

    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract void initData();
    /**
     * 是否使用eventBus
     */
    protected  boolean useEventBus(){
        return false;
    }
    /**
     * 默认状态栏白底黑字
     */
    public void initStatusBar() {
//        IMStatusBarUtil.setColor(this, getResources().getColor(R.color.title_color_f5f5f5),0);
//        IMStatusBarUtil.setLightMode(this);
        IMStatusBarUtil.setTranslucent(this, 0);
        IMStatusBarUtil.setLightMode(this);
    }
    /**
     * 动态添加权限
     */
    private void requestPermissions() {
        if (EasyPermissions.hasPermissions(this, perms)) {
            //todo 已经获取了权限
            IMLogUtil.d("tag", "BaseActivity " +"(requestPermissions) " + true , 45, "perms = " + perms);
        } else {
            //没有获取了权限，申请权限
            EasyPermissions.requestPermissions(this, "为了优化您的使用体验，请打开下列必要的权限", 0, perms);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }
    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

    /**
     * 加载中
     */
    protected void showLoadingDialog() {
        ShowLoadiongUtils.showLoadingDialogTypeTwo(this, getResources().getString(R.string.im_loading));
    }
    protected void dissLoadingDialog() {
        ShowLoadiongUtils.dismissLoadingDialogTypeTwo();
    }

    /**
     * 通过Class跳转界面
     **/
    protected void startActivity(Class<?> cls, boolean isFinish) {
        ActivityUtils.next(this,cls,isFinish);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    protected void startActivity(Class<?> cls, Bundle bundle, boolean isFinish) {
        ActivityUtils.next(this,cls,bundle,isFinish);
    }

    /**
     * Toast吐司
     **/
    protected void showMessage(String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (useEventBus()) {
            if (EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().unregister(this);//注销eventBus
            }
        }

    }
}
