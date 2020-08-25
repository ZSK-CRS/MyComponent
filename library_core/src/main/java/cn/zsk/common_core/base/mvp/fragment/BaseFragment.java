package cn.zsk.common_core.base.mvp.fragment;

import cn.zsk.common_core.base.mvp.presenter.AbstractPresenter;
import cn.zsk.common_core.base.mvp.view.AbstractView;
import cn.zsk.common_core.utils.LogUtil;

/**
 * Author : ZSK
 * Date : 2020/2/19
 * Description :
 */
public abstract class BaseFragment<T extends AbstractPresenter> extends AbstractSimpleFragment
        implements AbstractView {

    protected T mPresenter;   //业务逻辑


    @Override
    protected void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroyView();
    }

    @Override
    protected void requestPermissons() {
        if (null != setPermission()) {
            String[] data = setPermission();
            StringBuffer buffer = new StringBuffer();
            for (String str :data){
                buffer.append(str);
            }
        }
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

}
