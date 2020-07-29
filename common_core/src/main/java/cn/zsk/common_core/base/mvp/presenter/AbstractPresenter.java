package cn.zsk.common_core.base.mvp.presenter;


import cn.zsk.common_core.base.mvp.view.AbstractView;
import io.reactivex.disposables.Disposable;

/**
 * Author : ZSK
 * Date : 2020/1/30
 * Description : 抽象业务逻辑
 */
public interface AbstractPresenter<T extends AbstractView>{

    /**
     * 注入View
     */
    void attachView(T view);

    /**
     * 回收View
     */
    void detachView();


    //是否关联
    boolean isAttach();

    /**
     * 获取View
     */
    T getView();

    /**
     * 添加订阅请求到容器中统一管理
     */
    void addRxbingSubscribe(Disposable disposable);

}
