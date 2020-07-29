package cn.zsk.common_core.utils;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

/**
 * @Author : ZSK
 * @Date : 2019/10/31
 * @Description : 定制的Log工具,
 */
public class LogUtil {

    public static boolean LOG = true;

    private static final String TAG = "Log==";
    private static final String LEVEL_I = "i";
    private static final String LEVEL_D = "d";
    private static final String LEVEL_W = "w";
    private static final String LEVEL_V = "v";
    private static final String LEVEL_E = "e";

    public static void setLogSwitch(boolean isOpen) {
        LOG = isOpen;
    }

    private LogUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void i(String tag, String msg) {
        if (LOG) {
            printLog(tag, formatLog(msg), LogUtil.LEVEL_I);
        }
    }

    public static void i(String msg) {
        if (LOG) {
            printLog(TAG, formatLog(msg), LogUtil.LEVEL_I);
        }
    }

    public static void d(String tag, String msg) {
        if (LOG) {
            printLog(tag, formatLog(msg), LogUtil.LEVEL_D);
        }
    }

    public static void d(String msg) {
        if (LOG) {
            printLog(TAG, formatLog(msg), LogUtil.LEVEL_D);
        }
    }

    public static void w(String tag, String msg) {
        if (LOG) {
            printLog(tag, formatLog(msg), LogUtil.LEVEL_W);
        }
    }

    public static void w(String msg) {
        if (LOG) {
            printLog(TAG, formatLog(msg), LogUtil.LEVEL_W);
        }
    }


    public static void v(String tag, String msg) {
        if (LOG) {
            printLog(tag, formatLog(msg), LogUtil.LEVEL_V);
        }
    }

    public static void v(String msg) {
        if (LOG) {
            printLog(TAG, formatLog(msg), LogUtil.LEVEL_V);
        }
    }

    public static void e(String tag, String msg) {
        if (LOG) {
            printLog(tag, formatLog(msg), LogUtil.LEVEL_E);
        }
    }

    public static void e(String msg) {
        if (LOG) {
            printLog(TAG, formatLog(msg), LogUtil.LEVEL_E);
        }
    }

    private static String formatLog(String msg) {
        StackTraceElement traceElements[] = Thread.currentThread().getStackTrace();
        StackTraceElement element = traceElements[4];
        String className = element.getClassName();
        String methodName = element.getMethodName();
        String fileName = element.getFileName();
        int lineNumber = element.getLineNumber();

        if (className != null && className.contains(".")) {
            className = className.substring(className.lastIndexOf(".") + 1, className.length());
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append(className + ".");
        buffer.append(methodName + "(");
        buffer.append(fileName + ":");
        buffer.append(lineNumber + "):");
        buffer.append(msg);

        return buffer.toString();
    }

    private static void printLog(String tag, String msg, String type) {
        printByLogType(tag, msg, type);
    }

    private static void printByLogType(String tag, String msg, String type) {
        switch (type) {
            case LogUtil.LEVEL_I:
                Log.i(tag, msg);
                break;
            case LogUtil.LEVEL_D:
                Log.d(tag, msg);
                break;
            case LogUtil.LEVEL_W:
                Log.w(tag, msg);
                break;
            case LogUtil.LEVEL_V:
                Log.v(tag, msg);
                break;
            case LogUtil.LEVEL_E:
                Log.e(tag, msg);
                break;
            default:
                break;
        }
    }

    public static void printCurrentActivity(Context context){
        String currentActivity = context.getClass().getSimpleName();
        StringBuffer buffer = new StringBuffer();
        buffer.append("("+currentActivity + ".java:0)");
        Log.d("当前界面", buffer.toString());
    }
}
