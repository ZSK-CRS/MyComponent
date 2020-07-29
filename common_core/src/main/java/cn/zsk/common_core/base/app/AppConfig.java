package cn.zsk.common_core.base.app;

import android.app.Application;

import com.pedaily.yc.ycdialoglib.toast.ToastUtils;

import cn.ycbjie.ycthreadpoollib.PoolThread;
import cn.zsk.common_core.base.callback.BaseLifecycleCallback;
import cn.zsk.common_core.base.callback.LogCallback;
import cn.zsk.common_core.constant.Constant;
import cn.zsk.common_core.utils.SPUtil;

/**
 * @Author : ZSK
 * @Date : 2019/10/25
 * @Description :  app全局配置
 */

// TODO: 2019/10/31 AppConfig应该做全局的配置

/**
 * 1、应该独立出App主题的工具类：例如黑夜模式、皮肤、
 * 2、独立出App的状态工具类：例如是否注册、登录、退出
 * ........
 */
public enum AppConfig {

    //对象
    INSTANCE;

    private PoolThread executor;
    private Application mApplication;

    public void initConfig(Application application) {

        mApplication = application;
        //吐司工具
        ToastUtils.init(application);
        //生命周期监听
        BaseLifecycleCallback.getInstance().init(application);
        //初始化线程池
        initThreadPool();
    }



    /**
     * 初始化线程池管理器
     */
    private void initThreadPool() {
        //创建一个独立的线程池实例
        if (executor == null) {
            executor = PoolThread.ThreadBuilder
                    .createFixed(10)
                    .setPriority(Thread.MAX_PRIORITY)
                    .setCallback(new LogCallback())
                    .build();
        }
    }

    /**
     * 获取线程池管理器对下个，同意维护所有的线程池
     *
     * @return
     */
    public PoolThread getExecutor() {
        initThreadPool();
        return executor;
    }

    /**
     * 关闭一些资源
     */
    public void destory() {
        //关闭线程池
        if (executor != null) {
            executor.close();
            executor = null;
        }
    }

    public Application getApplication() {
        return mApplication;
    }

    public boolean isLogin() {
        return SPUtil.getInstance().getBoolean(Constant.KEY_IS_LOGIN);
    }

    public void setLogin(boolean login,String token) {
        SPUtil.getInstance().put(Constant.KEY_IS_LOGIN, login);
        SPUtil.getInstance().put(Constant.KEY_TOKEN, token);
    }

    //退出登录
    public void takeOut(){
        SPUtil.getInstance().put(Constant.KEY_IS_LOGIN, false);
        SPUtil.getInstance().put(Constant.KEY_TOKEN,"");
    }
}
