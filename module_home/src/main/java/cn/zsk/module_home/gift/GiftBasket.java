package cn.zsk.module_home.gift;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import cn.zsk.module_home.gift.inter.IGiftTaskService;

/**
 * Author : ZSK
 * Date : 2020/9/8
 * Description :  存储礼物的队列
 */
public class GiftBasket {

    private String TAG = "GiftBasket";

    private IGiftTaskService giftService;   //线程池

    public GiftBasket(IGiftTaskService executorService) {
        this.giftService = executorService;
    }

    BlockingQueue<GiftMessage> queue = new LinkedBlockingDeque<>();

    public void put(GiftMessage giftMessage) throws InterruptedException {
        queue.put(giftMessage);
        if (giftService == null){
            throw new NullPointerException("giftService can't be null");
        }
    }


    public GiftMessage take() throws InterruptedException {
        if (queue.isEmpty()){
            return null;
        }
        return queue.take();
    }


}
