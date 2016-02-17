package com.demo.candy.wechatui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.demo.candy.wechatui.R;

/**
 * Created by ouyangdi on 2016/2/2.
 */
public class GradientIconView extends FrameLayout{
    private ImageView mIconBottom;
    private ImageView mIconTop;

    private float mAlpha = 0f;

    public GradientIconView(Context context) {
        this(context, null, 0);
    }

    public GradientIconView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GradientIconView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);

        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.GradientIconView);

        Drawable drawable;

        int n=array.getIndexCount();
        for(int i=0; i<n; i++) {
            int attr = array.getIndex(i);

            if(attr == R.styleable.GradientIconView_bottom_icon) {
                drawable = array.getDrawable(i);
                setIconBottom(drawable);

            } else if(attr == R.styleable.GradientIconView_top_icon) {
                drawable = array.getDrawable(i);
                setIconTop(drawable);
            }
        }

        //释放实例
        array.recycle();
        setIconAlpha(mAlpha);
    }

    private void initViews(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_gradient_icon, this, true);
        mIconBottom = (ImageView)findViewById(R.id.iv_bottom_icon);
        mIconTop = (ImageView)findViewById(R.id.iv_top_icon);
    }

    public void setIconBottom(Drawable drawable) {
        mIconBottom.setBackground(drawable);
    }

    public void setIconTop(Drawable drawable) {
        mIconTop.setBackground(drawable);
    }

    public void setIconAlpha(float alpha) {
        mIconTop.setAlpha(alpha);
        mIconBottom.setAlpha(1-alpha);
        mAlpha = alpha;
    }
}
