package cn.zsk.common_core.base.mvp.presenter;


import java.lang.ref.WeakReference;

import cn.zsk.common_core.base.mvp.view.AbstractView;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Author : ZSK
 * Date : 2019/5/20
 * Description :
 */
public class BasePresenter<T extends AbstractView> implements AbstractPresenter<T>{

    private T mView;

    public WeakReference<T> mViewRef;

    private CompositeDisposable compositeDisposable;

    @Override
    public void attachView(T view) {
        mViewRef = new WeakReference<>(view);
    }

    @Override
    public void detachView() {
        this.mView = null;
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    @Override
    public boolean isAttach() {
        return mViewRef != null && mViewRef.get() != null;
    }

    @Override
    public T getView() {
        if (isAttach()) {
            return mViewRef.get();
        }
        return null;
    }

    @Override
    public void addRxbingSubscribe(Disposable disposable) {
        addSubscribe(disposable);
    }

    protected void addSubscribe(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }
}
