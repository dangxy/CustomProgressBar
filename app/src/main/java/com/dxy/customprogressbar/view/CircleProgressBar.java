package com.dxy.customprogressbar.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.dxy.customprogressbar.R;

/**
 * Created by dangxueyi on 16/6/5.
 */
public class CircleProgressBar extends HorizontalProgressBar{

    private int mRadius = dp2px(30);

    private int mMaxPaintWidth;
    public CircleProgressBar(Context context) {
        this(context,null);
    }

    public CircleProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressBar);

        mRadius = (int)typedArray.getDimension(R.styleable.CircleProgressBar_radius,mRadius);


        typedArray.recycle();

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);



    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mMaxPaintWidth= Math.max(mReachHeight,mUnReachHeight);

        int expect = mRadius*2+mMaxPaintWidth+getPaddingLeft()+getPaddingRight();


        int width = resolveSize(expect,widthMeasureSpec);
        int height = resolveSize(expect,heightMeasureSpec);

        int realwidth = Math.min(width,height);

        mRadius=(realwidth-getPaddingLeft()-getPaddingRight()-mMaxPaintWidth)/2;

        setMeasuredDimension(realwidth,realwidth);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        String text = getProgress()+"%";

        float textWidth = mPaint.measureText(text);
        float textHeight = (mPaint.descent()+mPaint.ascent())/2;

        canvas.save();

        canvas.translate(getPaddingLeft()+mMaxPaintWidth/2,getPaddingTop()+mMaxPaintWidth/2);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mUnReachColor);
        mPaint.setStrokeWidth(mUnReachHeight);
        canvas.drawCircle(mRadius,mRadius,mRadius,mPaint);


        mPaint.setColor(mReachColor);
        mPaint.setStrokeWidth(mReachHeight);


        float sweepAngle = getProgress()*1.0f/getMax()*360;

        canvas.drawArc(new RectF(0,0,mRadius*2,mRadius*2),0,sweepAngle,false,mPaint);



        mPaint.setStyle(Paint.Style.FILL);

        mPaint.setColor(mTextColor);
        canvas.drawText(text,mRadius-textWidth /2,mRadius-textHeight,mPaint);

        canvas.restore();

    }
}
