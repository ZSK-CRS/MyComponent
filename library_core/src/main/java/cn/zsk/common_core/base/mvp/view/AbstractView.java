package cn.zsk.common_core.base.mvp.view;

/**
 * Author : ZSK
 * Date : 2020/1/30
 * Description : 抽象接口
 */
public interface AbstractView {

     void showLoading();

     void cancelLoading();

     void showFailureMessage(String error);


}
