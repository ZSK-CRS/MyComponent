package cn.zsk.mycomponent.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import cn.zsk.common_core.utils.LogUtil;
import cn.zsk.mycomponent.R;

/**
 * @author: ZSK
 * @date: 2020-9-27 10:33:57
 * @describe:
 */
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_test);
        LogUtil.e("======MainActivity::::onCreate");
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtil.e("======SecondActivity::::onNewIntent");
    }


    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.e("======SecondActivity::::onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.e("======SecondActivity::::onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.e("======SecondActivity::::onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.e("======SecondActivity::::onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.e("======SecondActivity::::onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.e("======SecondActivity::::onDestroy");
    }

}
