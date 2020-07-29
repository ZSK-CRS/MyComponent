package cn.zsk.common_core.base.mvp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author : ZSK
 * Date : 2020/2/19
 * Description :
 */
public abstract class AbstractSimpleFragment extends Fragment {

    private Unbinder unbinder;

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(setLayoutId(),container,false);
        unbinder =  ButterKnife.bind(this,rootView);
        requestPermissons();
        attachView();
        initPatams();
        initData();
        initListener();
        return rootView;
    }

    protected abstract void attachView();

    protected abstract void requestPermissons();

    protected void initListener(){}

    public abstract int setLayoutId();

    protected abstract void initData();

    protected void initPatams(){}

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
