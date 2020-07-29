package cn.zsk.common_core.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Author : ZSK
 * Date : 2020/3/15
 * Description :
 */
public class SystemUtil {


    /**
     * 获取系统包名
     * @param context
     * @return
     */
    public static String getPackageName(Context context){
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(),0);
            return packageInfo.packageName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
