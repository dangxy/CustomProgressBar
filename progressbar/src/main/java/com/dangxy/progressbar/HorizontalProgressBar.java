package com.dangxy.progressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;

/**
 * Created by dangxueyi on 16/6/5.
 */
public class HorizontalProgressBar extends ProgressBar {

    private static final int DEFAULT_TEXT_SIZE = 10;
    private static final int DEFAULT_TEXT_COLOR = 0Xffc00d1;
    private static final int DEFAULT_COLOR_UNREACH = 0xffd3d6da;
    private static final int DEFAULT_COLOR_REACH = DEFAULT_TEXT_COLOR;
    private static final int DEFAULT_HEIGHT_UNREACH = 2;
    private static final int DEFAULT_HEIGHT_RAECH = 2;
    private static final int DEFAULT_TEXT_OFFSET = 10;

    protected int mTextSize = sp2px(DEFAULT_TEXT_SIZE);
    protected int mTextColor = DEFAULT_TEXT_COLOR;
    protected int mUnReachColor = DEFAULT_COLOR_UNREACH;
    protected int mReachColor = DEFAULT_COLOR_REACH;
    protected int mReachHeight = dp2px(DEFAULT_HEIGHT_RAECH);
    protected int mUnReachHeight = dp2px(DEFAULT_HEIGHT_UNREACH);
    protected int mTextOffset = dp2px(DEFAULT_TEXT_OFFSET);

    protected Paint mPaint = new Paint();
    private int mRealWidth;

    public HorizontalProgressBar(Context context) {
        this(context, null);
    }

    public HorizontalProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        getStyledAttrs(attrs);
    }

    private void getStyledAttrs(AttributeSet attributeSet) {

        TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.HorizontalProgressBar);

        mTextSize = (int) typedArray.getDimension(R.styleable.HorizontalProgressBar_progress_text_size, mTextSize);

        mTextColor = (int) typedArray.getColor(R.styleable.HorizontalProgressBar_progress_text_color, mTextColor);

        mReachHeight = (int) typedArray.getDimension(R.styleable.HorizontalProgressBar_progress_reach_height, mReachHeight);

        mReachColor = (int) typedArray.getColor(R.styleable.HorizontalProgressBar_progress_reach_color, mReachColor);

        mUnReachColor = (int) typedArray.getColor(R.styleable.HorizontalProgressBar_progress_un_reach_color, mUnReachColor);

        mUnReachHeight = (int) typedArray.getDimension(R.styleable.HorizontalProgressBar_progress_un_reach_height, mUnReachHeight);

        mTextOffset = (int) typedArray.getDimension(R.styleable.HorizontalProgressBar_progress_text_offset, mTextOffset);


        typedArray.recycle();

        mPaint.setTextSize(mTextSize);


    }

    protected int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, getResources().getDisplayMetrics());
    }

    protected int sp2px(int spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, getResources().getDisplayMetrics());
    }


    @Override
    protected synchronized void onDraw(Canvas canvas) {

        canvas.save();
        canvas.translate(getPaddingLeft(), getHeight() / 2);


        boolean noNeedUnRech = false;


        String text = getProgress() + "%";
        float radio = getProgress() * 1.0f / getMax();
        int textWidth = (int) mPaint.measureText(text);
        float progressX = radio * mRealWidth;
        if (progressX + textWidth > mRealWidth) {

            progressX = mRealWidth - textWidth;
            noNeedUnRech = true;
        }


        float endx = progressX - mTextOffset / 2;


        if (endx > 0) {
            mPaint.setColor(mReachColor);
            mPaint.setStrokeWidth(mReachHeight);
            canvas.drawLine(0, 0, endx, 0, mPaint);
        }

        mPaint.setColor(mTextColor);
        int y = (int) -(mPaint.descent() + mPaint.ascent()) / 2;

        canvas.drawText(text, progressX, y, mPaint);


        if (!noNeedUnRech) {
            float start = progressX + mTextOffset / 2 + textWidth;
            mPaint.setColor(mUnReachColor);
            mPaint.setStrokeWidth(mReachHeight);

            canvas.drawLine(start, 0, mRealWidth, 0, mPaint);
        }

        canvas.restore();
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int widthvValue = MeasureSpec.getSize(widthMeasureSpec);


        int height = measureHeight(heightMeasureSpec);

        setMeasuredDimension(widthvValue, height);

        mRealWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();


    }

    private int measureHeight(int heightMeasureSpec) {

        int result = 0;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);

        if (mode == MeasureSpec.EXACTLY) {

            result = size;

        } else {
            int textHeight = (int) (mPaint.descent() - mPaint.ascent());
            result = getPaddingTop() + getPaddingBottom() + Math.max(Math.max(mReachHeight , mUnReachHeight), Math.abs(textHeight));

            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;
    }
}
