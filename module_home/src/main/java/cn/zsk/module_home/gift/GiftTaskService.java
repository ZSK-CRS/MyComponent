package cn.zsk.module_home.gift;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import cn.zsk.module_home.gift.inter.IGiftTaskService;

/**
 * Author : ZSK
 * Date : 2020/9/8
 * Description :   线程操作 礼物
 */
public class GiftTaskService implements IGiftTaskService {

    private ScheduledExecutorService executorService;

    private int MIN_TAKE_TIME = 1000;    //1s执一次

    private Runnable task;

    public GiftTaskService(Runnable task) {
        this.task = task;
        executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleWithFixedDelay(task, 0, MIN_TAKE_TIME, TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean isShutdown() {
        return executorService.isShutdown();
    }

    @Override
    public void shutDown() {
        executorService.shutdown();
    }

    @Override
    public void shutdownNow() {
        executorService.shutdownNow();
    }

}
