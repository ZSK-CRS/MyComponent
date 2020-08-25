package cn.zsk.common_core.base.app;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;

import cn.zsk.common_core.utils.LogUtil;

/**
 * @Author : ZSK
 * @Date : 2019/10/31
 * @Description : 子线程初始化工作,初始化第三方sdk等等
 */
public class InitializeService extends IntentService {

    private static final String ACTION_INIT = "initApplication";

    public static void start(Context context) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT);
        context.startService(intent);
    }

    public InitializeService() {
        super("InitializeService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT.equals(action)) {
                // TODO: 2019/10/31 初始化
                initApplication();
            }
        }
    }

    private void initApplication() {
        initUtils();
    }

    private void initUtils() {
        //设置打印日志总开关，线上时关闭
        LogUtil.setLogSwitch(true);
    }


}
