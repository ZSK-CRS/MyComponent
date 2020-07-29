package cn.zsk.common_core.utils;

/**
 * Author : ZSK
 * Date : 2020/3/7
 * Description :
 */
public class StringUtil {

    /**
     * 检测目标字符串是否为空
     *
     * @param target
     * @return
     */
    public static boolean isEmpty(String... target) {
        if (target == null || "".equals(target)) {
            return true;
        }

        for (String str : target) {
            if (str == null || "".equals(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 处理字符串
     * @param target
     * @return
     */
    public static String handleStringFormat(String target) {
        if (target == null) {
            return "";
        }
        return target;
    }

    /**
     * 处理字符串
     * @param target
     * @param defaultData  为null时间默认值
    * @return
     */
    public static String handleStringFormat(String target, String defaultData) {
        if (target == null) {
            return defaultData;
        }
        return target;
    }


}
