package cn.zsk.common_core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author : ZSK
 * Date : 2020/3/7
 * Description : 时间处理工具
 */
public class DateUtil {

    public static String TYPE_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static String TYPE_MM_SS = "mm:ss";
    public static String TYPE_YYYY_MM_DD = "yyyy-MM-dd";
    public static String TYPE_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static String TYPE__CHINES_YYYY_MM_DD_HH_MM = "yyyy年MM月dd日 HH:mm";

    /**
     * 时间戳转化为标准时间
     * @param dataFormat
     * @param timeStr
     * @return
     */
    public static String changeStampToStandrdTime(String dataFormat, String timeStr) {
        try {
            if (timeStr == null || "".equals(timeStr) || "null".equals(timeStr)) {
                return "";
            }
            long time = Long.parseLong(timeStr);
            time = time * 1000;
            String result = "";
            SimpleDateFormat format = new SimpleDateFormat(dataFormat);
            result = format.format(new Date(time));
            return result;
        } catch (Exception e){
            return "0";
        }
    }

    /**
     * 固定的时间格式获取当前时间
     * @param dataFormat
     * @return
     */
    public static String getCurrentDate(String dataFormat){
        SimpleDateFormat df = new SimpleDateFormat(dataFormat);
        return df.format(new Date());
    }
}
