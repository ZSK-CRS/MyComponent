package cn.zsk.module_home.gift;

import android.view.View;
import android.view.animation.AnimationSet;

import cn.zsk.module_home.gift.inter.IGiftAnimation;


/**
 * Author : ZSK
 * Date : 2020/9/8
 * Description :
 */
public class GiftAnimation implements IGiftAnimation {

    @Override
    public void addAnimation(View view, GiftMessage giftMessage) {

    }

    @Override
    public AnimationSet outAnimation() {
        return null;
    }
}
