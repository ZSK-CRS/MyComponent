package cn.zsk.common_core.network.exception;

import java.io.IOException;

/**
 * Author : ZSK
 * Date : 2020/3/31
 * Description : 连接服务器异常
 */
public class ServiceErrorException extends IOException {

    private int code;

    public ServiceErrorException() {
        super("登录失效");
    }

    public ServiceErrorException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
