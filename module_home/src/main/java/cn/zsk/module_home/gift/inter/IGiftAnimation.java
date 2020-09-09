package cn.zsk.module_home.gift.inter;

import android.view.View;
import android.view.animation.AnimationSet;

import cn.zsk.module_home.gift.GiftMessage;


/**
 * Author : ZSK
 * Date : 2020/9/8
 * Description :
 */
public interface IGiftAnimation {

    void addAnimation(View view, GiftMessage giftMessage);     //添加数据的动画


    AnimationSet outAnimation();     //退出动画
}
