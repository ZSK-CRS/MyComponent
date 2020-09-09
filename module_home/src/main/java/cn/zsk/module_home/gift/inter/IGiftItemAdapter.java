package cn.zsk.module_home.gift.inter;

import android.view.View;

import cn.zsk.module_home.gift.GiftMessage;

/**
 * Author : ZSK
 * Date : 2020/9/8
 * Description :  礼物适配器
 */
public interface IGiftItemAdapter<T extends GiftMessage> {


    View initView(View view, T giftMessage);

    View updateGift(View view, T giftMessage);

}
