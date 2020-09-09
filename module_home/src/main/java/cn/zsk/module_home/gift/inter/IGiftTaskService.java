package cn.zsk.module_home.gift.inter;

/**
 * Author : ZSK
 * Date : 2020/9/8
 * Description :
 */
public interface IGiftTaskService {

    boolean isShutdown();

    void shutDown();

    void shutdownNow();

}
