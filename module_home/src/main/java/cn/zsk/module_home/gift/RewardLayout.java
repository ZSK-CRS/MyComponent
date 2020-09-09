package cn.zsk.module_home.gift;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import cn.zsk.module_home.R;
import cn.zsk.module_home.gift.inter.IGiftAnimation;
import cn.zsk.module_home.gift.inter.IGiftItemAdapter;

/**
 * Author : ZSK
 * Date : 2020/9/8
 * Description : 打赏的布局
 */
public class RewardLayout extends LinearLayout {

    public static final int MAX_COUNT_DEFAULT = 3;    //默认最多显示礼物条数
    private int MAX_GIFT_COUNT;   //礼物的数量
    private int GIFT_ITEM_LAYOUT;   //礼物样式

    private int childWidth;
    private int childHeight;
    private GiftTaker taker;   //显示礼物

    private GiftTaskService taskService;  //获取礼物线程
    private GiftBasket basket;  //礼物队列
    private IGiftItemAdapter adapter;     //礼物适配器
    private IGiftAnimation giftAnimation;   //动画
    private AnimationSet outAnim = null;


    public void setGiftAnimation(IGiftAnimation giftAnimation) {
        this.giftAnimation = giftAnimation;
    }

    public void setAdapter(IGiftItemAdapter adapter) {
        this.adapter = adapter;
    }

    public RewardLayout(Context context) {
        super(context);
        init();
    }

    public RewardLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RewardLayout);
        MAX_GIFT_COUNT = (int) a.getInteger(R.styleable.RewardLayout_max_gift, MAX_COUNT_DEFAULT);
        GIFT_ITEM_LAYOUT = a.getResourceId(R.styleable.RewardLayout_gift_item_layout, 0);
        init();
        a.recycle();
    }

    public RewardLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RewardLayout);
        MAX_GIFT_COUNT = (int) a.getDimension(R.styleable.RewardLayout_max_gift, MAX_COUNT_DEFAULT);
        GIFT_ITEM_LAYOUT = a.getResourceId(R.styleable.RewardLayout_gift_item_layout, 0);
        init();
        a.recycle();
    }


    public void init() {
        taker = new GiftTaker();
        taskService = new GiftTaskService(taker);
        basket = new GiftBasket(taskService);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        View child = getGiftView();
        measureChild(child, widthMeasureSpec, heightMeasureSpec);

        MarginLayoutParams lp = (MarginLayoutParams) child
                .getLayoutParams();
        // 当前子空间实际占据的宽度,此处已用FrameLayout包裹，这里的margin≡0，
        childWidth = child.getMeasuredWidth() + lp.leftMargin
                + lp.rightMargin;
        // 当前子空间实际占据的高度
        childHeight = child.getMeasuredHeight() + lp.topMargin
                + lp.bottomMargin;

        int totalWidth = childWidth + getPaddingLeft() + getPaddingRight();
        int totalHeight = childHeight * MAX_GIFT_COUNT + getPaddingTop() + getPaddingBottom();
        setMeasuredDimension(measureWidth(widthMeasureSpec, totalWidth, child.getLayoutParams().width)
                , measureHeight(heightMeasureSpec, totalHeight, child.getLayoutParams().height));

    }


    /**
     * 测量宽度，结合item布局的宽参数
     *
     * @param measureSpec
     * @param viewGroupWidth
     * @param childLp
     * @return
     */
    private int measureWidth(int measureSpec, int viewGroupWidth, int childLp) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = specSize;
                break;
            case MeasureSpec.AT_MOST:
                if (childLp == ViewGroup.LayoutParams.MATCH_PARENT) {
                    result = specSize;
                } else {
                    result = Math.min(viewGroupWidth, specSize);
                }
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
            default:
                result = specSize;
                break;
        }
        return result;
    }

    /**
     * 测量高度，结合item布局的高参数
     *
     * @param measureSpec
     * @param viewGroupHeight
     * @param childLp
     * @return
     */
    private int measureHeight(int measureSpec, int viewGroupHeight, int childLp) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = specSize;
                break;
            case MeasureSpec.AT_MOST:
                if (childLp == ViewGroup.LayoutParams.MATCH_PARENT) {
                    result = specSize;
                } else {
                    result = Math.min(viewGroupHeight, specSize);
                }
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
            default:
                result = specSize;
                break;
        }
        return result;
    }


    /**
     * 向rewardlayout中添加MAX_GIFT_COUNT个子framelayout
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (getChildCount() != 0) {
            removeAllViews();
        }
        for (int i = 0; i < MAX_GIFT_COUNT; i++) {
            FrameLayout linearLayout = new FrameLayout(getContext());
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(),
                    (getMeasuredHeight() - getPaddingTop() - getPaddingBottom()) / MAX_GIFT_COUNT);
            linearLayout.setLayoutParams(params);
            addView(linearLayout);
        }
    }

    public class GiftTaker implements Runnable {

        @Override
        public void run() {
            takeGift();
        }
    }


    private void takeGift() {
        try {
            GiftMessage giftMessage = basket.take();
            if (giftMessage != null) {
                post(() -> updateGift(giftMessage));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void updateGift(GiftMessage giftMessage) {
        if (adapter == null) {
            return;
        }
        //目前只显示一条数据，故此简单处理
        removeGiftViewAnim();
        addGiftViewAnim(giftMessage);
    }

    private void removeGiftViewAnim() {
        View removeView = getRemoveView();
        if (adapter != null && removeView != null) {
            outAnim = giftAnimation.outAnimation();
            outAnim.setFillAfter(true);
            outAnim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    post(() -> removeChildGift(removeView));
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            post(() -> removeView.startAnimation(outAnim));
        }

    }

    private View getRemoveView() {
        for (int i = 0; i < getChildCount(); i++) {
            ViewGroup itemGroup = (ViewGroup) getChildAt(i);
            if (itemGroup.getChildCount() != 0) {
                return itemGroup.getChildAt(0);
            }
        }
        return null;
    }

    private void removeChildGift(View view) {
        for (int i = 0; i < getChildCount(); i++) {
            ViewGroup vg = (ViewGroup) getChildAt(i);
            vg.removeView(view);
            view = null;
        }
    }

    private void addGiftViewAnim(GiftMessage giftMessage) {
        View view = getGiftView();
        addChildGift(view);
        invalidate();
        if (giftAnimation != null) {
            giftAnimation.addAnimation(view, giftMessage);
        }
    }

    private void addChildGift(View view) {
        for (int i = 0; i < getChildCount(); i++) {
            ViewGroup itemGroup = (ViewGroup) getChildAt(i);
            if (itemGroup.getChildCount() == 0) {
                itemGroup.addView(view);
            }
        }

    }


    private View getGiftView() {
        FrameLayout root = new FrameLayout(getContext());
        View view = LayoutInflater.from(getContext()).inflate(getGiftRes(), root, false);
        LayoutParams lp = new LayoutParams(view.getLayoutParams().width, view.getLayoutParams().height);
        root.setLayoutParams(lp);
        root.addView(view);
        return root;
    }


    private int getGiftRes() {
        if (GIFT_ITEM_LAYOUT != 0) {
            return GIFT_ITEM_LAYOUT;
        } else {
            throw new NullPointerException("请在XML中配置 礼物item 的样式");
        }
    }

    public void put(GiftMessage giftMessage) {
        try {
            basket.put(giftMessage);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }





    public void onDestroy() {
        if (taskService != null) {
            taskService.shutdownNow();
        }
        taskService = null;
        taker = null;
        basket = null;
    }
}
