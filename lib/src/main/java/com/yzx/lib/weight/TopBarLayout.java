package com.yzx.lib.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.yzx.lib.R;

/**
 * Created by Administrator on 2019/7/5.
 */

public class TopBarLayout extends RelativeLayout {

    private TextView titleTextView;
    private String titleText;
    private int titleTextColor;
    private float titleTextSize;

    private TextView leftTextView;
    private String leftText;
    private int leftTextColor;
    private float leftTextSize;

    private TextView rightTextView;
    private String rightText;
    private int rightTextColor;
    private float rightTextSize;
    private int rightTextVisiable;

    private LayoutParams titleParams, leftParams, rightParams;

    public TopBarLayoutLeftOnClickListener mTopBarLayoutLeftOnClickListener;
    public TopBarLayoutRightOnClickListener mTopBarLayoutRightOnClickListener;


    public TopBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TopBarLayout);

        titleText = typedArray.getString(R.styleable.TopBarLayout_title);
        titleTextColor = typedArray.getColor(R.styleable.TopBarLayout_titleTextColor, 0);
        titleTextSize = typedArray.getDimensionPixelSize(R.styleable.TopBarLayout_titleTextSize, getResources().getDimensionPixelSize(R.dimen.topBarLayoutTitleTextSize));

        leftText = typedArray.getString(R.styleable.TopBarLayout_leftText);
        leftTextColor = typedArray.getColor(R.styleable.TopBarLayout_leftTextColor, 0);
        leftTextSize = typedArray.getDimensionPixelSize(R.styleable.TopBarLayout_leftTextSize, getResources().getDimensionPixelSize(R.dimen.topBarLayoutLeftTextSize));

        rightText = typedArray.getString(R.styleable.TopBarLayout_rightText);
        rightTextColor = typedArray.getColor(R.styleable.TopBarLayout_rightTextColor, 0);
        rightTextSize = typedArray.getDimensionPixelSize(R.styleable.TopBarLayout_rightTextSize, getResources().getDimensionPixelSize(R.dimen.topBarLayoutRightTextSize));
        rightTextVisiable = typedArray.getInt(R.styleable.TopBarLayout_rightTextVisiable, 0);

        typedArray.recycle();

        titleTextView = new TextView(context);
        titleTextView.setText(titleText);
        titleTextView.setTextColor(titleTextColor);
        titleTextView.setTextSize(SizeUtils.px2sp(titleTextSize));
        titleTextView.setGravity(Gravity.CENTER);

        leftTextView = new TextView(context);
        leftTextView.setText(leftText);
        leftTextView.setTextColor(leftTextColor);
        leftTextView.setTextSize(SizeUtils.px2sp(leftTextSize));
        Drawable leftTextDrawable = context.getResources().getDrawable(R.drawable.back);
        leftTextDrawable.setBounds(0, 0, leftTextDrawable.getMinimumWidth(), leftTextDrawable.getMinimumHeight());
        leftTextView.setCompoundDrawables(leftTextDrawable, null, null, null);
        leftTextView.setPadding(SizeUtils.dp2px(15), 0, 0, 0);

        rightTextView = new TextView(context);
        rightTextView.setText(rightText);
        rightTextView.setTextColor(rightTextColor);
        rightTextView.setTextSize(SizeUtils.px2sp(rightTextSize));
        rightTextView.setVisibility(rightTextVisiable);

        titleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(titleTextView, titleParams);

        leftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        leftParams.addRule(RelativeLayout.CENTER_VERTICAL);
        addView(leftTextView, leftParams);

        rightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        rightParams.addRule(RelativeLayout.CENTER_VERTICAL);
        addView(rightTextView, rightParams);

        leftTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTopBarLayoutLeftOnClickListener != null) {
                    mTopBarLayoutLeftOnClickListener.leftOnClick();
                }
            }
        });
        rightTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTopBarLayoutRightOnClickListener != null) {
                    mTopBarLayoutRightOnClickListener.rightOnClick();
                }
            }
        });
    }

    public void setTopBarLayoutLeftOnClickListener(TopBarLayoutLeftOnClickListener mTopBarLayoutLeftOnClickListener) {
        this.mTopBarLayoutLeftOnClickListener = mTopBarLayoutLeftOnClickListener;
    }

    public interface TopBarLayoutLeftOnClickListener {
        void leftOnClick();
    }

    public void setTopBarLayoutRightOnClickListener(TopBarLayoutRightOnClickListener mTopBarLayoutRightOnClickListener) {
        this.mTopBarLayoutRightOnClickListener = mTopBarLayoutRightOnClickListener;
    }

    public interface TopBarLayoutRightOnClickListener {
        void rightOnClick();
    }

    public void setTitleText(String title) {
        if (!TextUtils.isEmpty(title)) {
            titleTextView.setText(title);
        }
    }

    public void setRightText(String rightText) {
        if (!TextUtils.isEmpty(rightText)) {
            rightTextView.setText(rightText);
        }
    }
}
