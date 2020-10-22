package cn.zsk.mycomponent.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import cn.zsk.common_core.base.mvp.activity.BaseActivity;
import cn.zsk.common_core.utils.LogUtil;
import cn.zsk.mycomponent.R;

public class MainActivity extends BaseActivity {

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initParams() {

    }

    @Override
    protected void initData() {

        findViewById(R.id.tv_1).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this,SecondActivity.class));
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtil.e("======MainActivity::::onNewIntent");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.e("======MainActivity::::onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.e("======MainActivity::::onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.e("======MainActivity::::onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.e("======MainActivity::::onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.e("======MainActivity::::onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.e("======MainActivity::::onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.e("======MainActivity::::onDestroy");
    }


}
