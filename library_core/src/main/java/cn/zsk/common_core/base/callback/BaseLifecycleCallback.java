package cn.zsk.common_core.base.callback;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import cn.zsk.common_core.manager.ActivityManager;


/**
 * @Author : ZSK
 * @Date : 2019/10/31
 * @Description : 生命周期管理类
 */
public class BaseLifecycleCallback implements Application.ActivityLifecycleCallbacks {

    private BaseLifecycleCallback() {
    }

    public static BaseLifecycleCallback getInstance() {
        return HolderClass.INSTANCE;
    }

    private final static class HolderClass {
        private final static BaseLifecycleCallback INSTANCE = new BaseLifecycleCallback();
    }

    /**
     * 必须在Application的onCreate方法中调用
     * @param application
     */
    public void init(Application application){
        application.registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
        ActivityManager.getActivityManager().addActivity(activity);
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        ActivityManager.getActivityManager().removeActivity(activity);
    }
}
