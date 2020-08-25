package cn.zsk.module_home.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

import cn.zsk.common_core.utils.LogUtil;

/**
 * Author : ZSK
 * Date : 2020/8/24
 * Description :
 */
public class TestText extends AppCompatTextView {

    public TestText(Context context) {
        super(context,null);
    }

    public TestText(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public TestText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LogUtil.e("00000000000","onDraw");
    }
}
