package cn.zsk.common_core.base.mvp.activity;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import cn.zsk.common_core.base.mvp.presenter.AbstractPresenter;
import cn.zsk.common_core.base.mvp.view.AbstractView;
import cn.zsk.common_core.utils.LogUtil;
import cn.zsk.common_core.utils.SystemUtil;

/**
 * Author : ZSK
 * Date : 2020/1/30
 * Description : 抽象基类 主管通用功能的构建
 */
public abstract class BaseActivity<T extends AbstractPresenter> extends AbstractSimpleActivity
        implements AbstractView {

    protected T mPresenter;   //业务逻辑


    @Override
    protected void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroy();
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void requestPermissons() {
        String[] permisssionArray = setPermission();
        if (null != permisssionArray) {
            permisssionArray = checkRequestPermission(permisssionArray);
            if (permisssionArray.length != 0){
                AndPermission.with(this)
                        .runtime()
                        .permission(permisssionArray)
                        .onGranted(permissions -> {

                        })
                        .onDenied(permissions -> {

                        })
                        .start();
            }
        }
    }

    protected String[] checkRequestPermission(String[] permissions) {
        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (PackageManager.PERMISSION_GRANTED == this.getPackageManager().checkPermission(permission, SystemUtil.getPackageName(this))) {
                continue;
            }
            permissionList.add(permission);
        }
        String[] permissionArray = new String[permissionList.size()];
        return permissionList.toArray(permissionArray);
    }

    protected String[] setPermission() {
        return null;
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void cancelLoading() {

    }

    @Override
    public void showFailureMessage(String error) {

    }
}
