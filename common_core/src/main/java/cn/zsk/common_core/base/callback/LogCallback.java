package cn.zsk.common_core.base.callback;


import cn.ycbjie.ycthreadpoollib.callback.ThreadCallback;
import cn.zsk.common_core.utils.LogUtil;

/**
 * @Author : ZSK
 * @Date : 2019/10/31
 * @Description : 线程回调数据
 */
public class LogCallback implements ThreadCallback {

    private final String TAG = "LogCallback";

    @Override
    public void onError(String threadName, Throwable t) {
        LogUtil.e(TAG,"LogCallback"+"------onError");
        LogUtil.e(TAG, String.format("[任务线程%s]/[回调线程%s]执行失败: %s", threadName, Thread.currentThread(), t.getMessage()));
    }

    @Override
    public void onCompleted(String threadName) {
        LogUtil.e(TAG,"LogCallback"+"------onCompleted");
        LogUtil.e(TAG, String.format("[任务线程%s]/[回调线程%s]执行完毕：", threadName, Thread.currentThread()));
    }

    @Override
    public void onStart(String threadName) {
        LogUtil.e(TAG,"LogCallback"+"------onStart");
        LogUtil.e(TAG, String.format("[任务线程%s]/[回调线程%s]执行开始：", threadName, Thread.currentThread()));
    }
}
