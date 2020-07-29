package cn.zsk.common_core.utils;

/**
 * @Author : ZSK
 * @Date : 2020/3/20
 * @Description : 数字处理
 */
public class NumberUtil {

    public static int changeStrToInteger(String str){
        if (str == null || "".equals(str)){
            return 0;
        }
        return Integer.parseInt(str);
    }

    public static int changeStrToInteger(String str,int defaultValue){
        if (str == null || "".equals(str)){
            return defaultValue;
        }
        return Integer.parseInt(str);
    }
}
