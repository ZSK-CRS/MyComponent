package cn.zsk.common_core.network.custom;

import com.google.gson.JsonParseException;
import com.pedaily.yc.ycdialoglib.toast.ToastUtils;

import org.json.JSONException;
import java.io.EOFException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLException;

import cn.zsk.common_core.network.exception.ServiceErrorException;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Author : ZSK
 * Date : 2020/3/30
 * Description :  封装观察者
 */
public abstract class BaseObserver<T> implements Observer<BaseResponse<T>> {

    protected Disposable disposable;

    @Override
    public void onSubscribe(Disposable d) {
        //此为回调方法，触发的时机在订阅后，发射数据之前，用于取消订阅
        disposable = d;
    }

    @Override
    public void onNext(BaseResponse<T> t) {
        if (t == null) {
            onError(new NullPointerException());
        } else {
            onSuccess(t);
        }
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ServiceErrorException) {
            ServiceErrorException serviceError = (ServiceErrorException) e;
            if (serviceError.getCode() == 4003) {
                onComplete();
                ToastUtils.showToast(serviceError.getMessage());
                //activity栈提供跳转的context
                //LoginActivity.actionTo(ActivityManager.getActivityManager().currentActivity());
                return;
            }
            onError(serviceError.getCode(), serviceError.getMessage());
        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            onError(httpException.code(), "服务器异常");
        } else if (e instanceof JsonParseException || e instanceof JSONException) {
            onError(500, "解析错误");
        } else if (e instanceof ConnectException) {
            onError(400, "连接错误");
        } else if (e instanceof NullPointerException) {
            onError(500, "数据为空");
        } else if (e instanceof UnknownHostException) {
            onError(400, "无网络连接");
        } else if (e instanceof SocketTimeoutException) {
            onError(400, "链接超时");
        } else if (e instanceof SocketException) {
            onError(500, "链接关闭");
        } else if (e instanceof EOFException) {
            onError(500, "链接关闭");
        } else if (e instanceof IllegalArgumentException) {
            onError(400, "参数错误");
        } else if (e instanceof SSLException) {
            onError(500, "证书错误");
        } else {
            onError(500, "未知错误");
        }
        onComplete();
        disposedRequest();
    }

    @Override
    public void onComplete() {
        disposedRequest();
    }

    private void disposedRequest() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    /**
     * 错误回调
     * @param errorCode    code
     * @param errorMessage msg
     */
    protected void onError(int errorCode, String errorMessage) {

    }

    /**
     * 成功回调
     *
     * @param response data
     */
    protected abstract void onSuccess(BaseResponse<T> response);
}
