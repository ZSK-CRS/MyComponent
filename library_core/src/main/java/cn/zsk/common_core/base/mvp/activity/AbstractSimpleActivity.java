package cn.zsk.common_core.base.mvp.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import cn.zsk.common_core.utils.LogUtil;


/**
 * Author : ZSK
 * Date : 2020/1/30
 * Description : 抽象基类 主管模版的构建
 */
public abstract class AbstractSimpleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeContentView();
        setContentView(setLayout());
        ButterKnife.bind(this);
        setRequestedOrientation(setScreenOrientation());  //设置屏幕方向
        LogUtil.printCurrentActivity(this);
        requestPermissons();
        attachView();
        initView();
        initParams();
        initToolbar();
        initData();
        initListener();
    }


    /**
     * 设置屏幕方向
     * 竖屏 ： ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
     * 横屏 ： ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
     * @return
     */
    protected int setScreenOrientation(){
        return ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
    }

    protected void beforeContentView(){}


    protected void requestPermissons() {

    }

    protected abstract int setLayout();

    protected void initParams() {
    }

    protected void initToolbar() {
    }

    protected abstract void initData();

    protected void initView() {

    }

    protected void initListener() {
    }

    protected void attachView() {
    }


}
