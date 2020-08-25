package cn.zsk.module_home.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import cn.zsk.common_core.utils.LogUtil;
import cn.zsk.module_home.R;

/**
 * Author : ZSK
 * Date : 2020/8/19
 * Description :  底部导航栏
 */
public class GapBottomNavigationView extends BottomNavigationView {

    private float centerRadius = 0; //中间凹陷的半径
    private float cornerRadius = 12f; //拐角处的圆滑大小（越大越平滑）
    private float shadowLength = 6f; //阴影大小
    private View mCenterView;    //中心凸出的View

    private Paint mPaint;
    private Path mPath;
    private Path mPath2;

    public GapBottomNavigationView(@NonNull Context context) {
        super(context, null);
    }

    public GapBottomNavigationView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        init(context, attrs);
    }

    public GapBottomNavigationView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    {

    }

    /**************************************************************************************************/

    public void setCenterView(View centerView) {
        this.mCenterView = centerView;
        invalidate();
    }

    /**************************************************************************************************/


    private void init(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.GapBottomNavigationView);
        shadowLength = typedArray.getFloat(R.styleable.GapBottomNavigationView_shadow_length, 6);
        cornerRadius = typedArray.getFloat(R.styleable.GapBottomNavigationView_corner_radius, 12);
        typedArray.recycle();

        //关闭硬件加速才能有阴影效果
        //setLayerType(LAYER_TYPE_SOFTWARE, null);
        setBackgroundColor(Color.TRANSPARENT);

        mPaint = new Paint();
        mPath = new Path();
        mPath2 = new Path();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mCenterView != null) {
            addView(mCenterView);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (mCenterView != null) {
            //设置center view位置
            int leftPosition = (int) (getWidth() / 2 - centerRadius + cornerRadius);
            int topPosition = (int) (getHeight() + centerRadius);
            int rightPosition = (int) (getWidth() / 2 + centerRadius - cornerRadius);
            int bottomPosition = (int) (getHeight() - centerRadius + cornerRadius);
            mCenterView.layout(leftPosition, topPosition, rightPosition, bottomPosition);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //测量时拿到凹陷的尺寸
        if (mCenterView != null) {
            centerRadius = mCenterView.getMeasuredWidth() / 2 + cornerRadius;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LogUtil.e("ooooooooooooooooo", "onDraw");
        //左边的半圆
        RectF rectL = new RectF(shadowLength, shadowLength, getHeight() + shadowLength, getHeight() - shadowLength);
        mPath.arcTo(rectL, 90, 180, false);
        mPath2.arcTo(rectL, 90, 180, false);
        mPath.lineTo(getWidth() / 2 - centerRadius - cornerRadius, shadowLength);
        mPath2.lineTo(getWidth() / 2 - centerRadius - cornerRadius, shadowLength);

        //左边转角处
        mPath.quadTo(
                getWidth() / 2 - centerRadius,
                shadowLength,
                getWidth() / 2 - centerRadius,
                cornerRadius + shadowLength
        );
        mPath2.quadTo(
                getWidth() / 2 - centerRadius,
                shadowLength,
                getWidth() / 2 - centerRadius,
                cornerRadius + shadowLength
        );

        //中间凹陷的半圆
        RectF rectCenter = new RectF(
                getWidth() / 2 - centerRadius,
                cornerRadius + shadowLength - centerRadius,
                getWidth() / 2 + centerRadius,
                cornerRadius + centerRadius + shadowLength
        );

        mPath.arcTo(rectCenter, 180, -180, false);
        mPath2.arcTo(rectCenter, 180, -180, false);

        //右边转角处
        mPath.quadTo(
                getWidth() / 2 + centerRadius,
                shadowLength,
                getWidth() / 2 + centerRadius + cornerRadius,
                shadowLength
        );
        mPath2.quadTo(
                getWidth() / 2 + centerRadius,
                shadowLength,
                getWidth() / 2 + centerRadius + cornerRadius,
                shadowLength
        );
        mPath.lineTo((getWidth() - shadowLength - getHeight() / 2), shadowLength);
        mPath2.lineTo((getWidth() - shadowLength - getHeight() / 2), shadowLength);

        //右边半圆
        RectF rectR = new RectF(
                getWidth() - shadowLength - getHeight(),
                shadowLength,
                getWidth() - shadowLength,
                getHeight() - shadowLength
        );
        mPath.arcTo(rectR, 270, 180, false);
        mPath2.arcTo(rectR, 270, 180, false);

        //最后的直线
        mPath.moveTo((getWidth() - shadowLength - getHeight() / 2), getHeight() - shadowLength);
        mPath.lineTo(getHeight() / 2 + shadowLength, getHeight() - shadowLength);
        mPath.close();
        mPath2.moveTo((getWidth() - shadowLength - getHeight() / 2), getHeight() - shadowLength);
        mPath2.lineTo(getHeight() / 2 + shadowLength, getHeight() - shadowLength);
        mPath2.close();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mPaint.setColor(getBackgroundTintList().getDefaultColor());
        }

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setMaskFilter(null);
        mPaint.setAntiAlias(true);
       // mPaint.setShadowLayer(shadowLength, 0, 0, Color.LTGRAY);
        canvas.drawPath(mPath, mPaint);

    }
}
