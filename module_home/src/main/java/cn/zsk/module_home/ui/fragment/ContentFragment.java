package cn.zsk.module_home.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import cn.zsk.common_core.base.mvp.fragment.BaseFragment;
import cn.zsk.module_home.R;
import yalantis.com.sidemenu.interfaces.ScreenShotable;

/**
 * Author : ZSK
 * Date : 2020/8/25
 * Description :
 */
public class ContentFragment extends BaseFragment implements ScreenShotable {


    public static final String CLOSE = "Close";
    public static final String BUILDING = "Building";
    public static final String BOOK = "Book";
    public static final String PAINT = "Paint";
    public static final String CASE = "Case";
    public static final String SHOP = "Shop";
    public static final String PARTY = "Party";
    public static final String MOVIE = "Movie";

    private View containerView;
    protected ImageView mImageView;
    protected int res;
    private Bitmap bitmap;

    public static ContentFragment newInstance(int resId) {
        ContentFragment contentFragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Integer.class.getName(), resId);
        contentFragment.setArguments(bundle);
        return contentFragment;
    }

    @Override
    public int setLayoutId() {
        return R.layout.module_home_fragment;
    }

    @Override
    protected void initParams() {
        super.initParams();
        res = getArguments().getInt(Integer.class.getName());
    }

    @Override
    protected void initData() {
        this.containerView = rootView.findViewById(R.id.container);
        mImageView = rootView.findViewById(R.id.image_content);
        mImageView.setClickable(true);
        mImageView.setFocusable(true);
        mImageView.setImageResource(res);
    }

    @Override
    public void showFailureMessage(String error) {

    }

    @Override
    public void takeScreenShot() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Bitmap bitmap = Bitmap.createBitmap(containerView.getWidth(),
                        containerView.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                containerView.draw(canvas);
                ContentFragment.this.bitmap = bitmap;
            }
        };

        thread.start();
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}
