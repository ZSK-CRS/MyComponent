package cn.zsk.common_core.network.custom;

/**
 * Author : ZSK
 * Date : 2020/3/31
 * Description : 服务器返回值的实体类
 */
public class BaseResponse<T> {

    private int statusCode=0;

    private String msg="";

    private T data;

    public BaseResponse() {
        this.statusCode = statusCode;
        this.msg = msg;
    }

    public BaseResponse(T data) {
        this.data = data;
    }

    public BaseResponse(int statusCode, String msg, T data) {
        this.statusCode = statusCode;
        this.msg = msg;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isTokenInvalidation(){
        return 4003 == statusCode;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code='" + statusCode + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
