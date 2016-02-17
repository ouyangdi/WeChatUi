package com.demo.candy.wechatui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.demo.candy.wechatui.R;

/**
 * Created by ouyangdi on 2016/2/2.
 */
public class GradientTextView extends FrameLayout{
    private TextView mBottomTextView;
    private TextView mTopTextView;

    private float mAlpha = 0f;

    public GradientTextView(Context context) {
        this(context, null, 0);
    }

    public GradientTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GradientTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initViews(context);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.GradientTextView);

        int color;

        int n = array.getIndexCount();

        for(int i=0; i<n; i++) {
            int attr = array.getIndex(i);
            if(attr == R.styleable.GradientTextView_bottom_text_color) {
                color = array.getColor(attr, Color.BLACK);
                setBottomTextColor(color);
            } else if(attr == R.styleable.GradientTextView_top_text_color) {
                color = array.getColor(attr, Color.TRANSPARENT);
                setTopTextColor(color);
            } else if(attr == R.styleable.GradientTextView_text) {
                String text = array.getString(attr);
                setGradientText(text);
            } else if(attr == R.styleable.GradientTextView_text_size) {
                float textSize = array.getDimension(attr, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                        10, getResources().getDisplayMetrics()));
                setGradientTextSize(textSize);
            }
        }

        array.recycle();
        setTextViewAlpha(mAlpha);
    }

    private void initViews(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_gradient_text, this, true);
        mBottomTextView = (TextView)findViewById(R.id.tv_bottom_text);
        mTopTextView = (TextView)findViewById(R.id.tv_top_text);
    }

    public void setBottomTextColor(int color) {
        mBottomTextView.setTextColor(color);
    }

    public void setTopTextColor(int color) {
        mTopTextView.setTextColor(color);
    }

    public void setGradientText(String text) {
        mTopTextView.setText(text);
        mBottomTextView.setText(text);
    }

    public void setGradientTextSize(float textSize) {
        mTopTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        mBottomTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }

    public void setTextViewAlpha(float alpha) {
        mTopTextView.setAlpha(alpha);
        mBottomTextView.setAlpha(1-alpha);
        this.mAlpha = alpha;
    }
}
