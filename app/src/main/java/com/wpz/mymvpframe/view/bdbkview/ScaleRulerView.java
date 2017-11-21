package com.wpz.mymvpframe.view.bdbkview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;

/**
 * Created by zoubo on 16/3/16.
 * 自定义横向滚动刻度尺
 *
 * @version 1.0
 */

public class ScaleRulerView extends View {
    public static final int MOD_TYPE = 10;  //刻度盘精度

    private static final int ITEM_HEIGHT_DIVIDER = 10;

    private static final int ITEM_MAX_HEIGHT = 40;  //最大刻度高度
    private static final int ITEM_MIN_HEIGHT = 30;  //最小刻度高度
    private static final int TEXT_SIZE = 18;

    private float mDensity;
    private float mValue = 50;
    private float mMaxValue = 100;
    private float mDefaultMinValue = 0;
    private int mModType = MOD_TYPE;
    private int mLineDivider = ITEM_HEIGHT_DIVIDER;
    private int mLastX, mMove;
    private int mWidth, mHeight;

    private int mMinVelocity;
    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;

    private OnValueChangeListener mListener;

    private Paint mLinePaint = new Paint();
    private Paint mSelectPaint = new Paint();
    private int mSelectWidth = 2;
    private int mNormalLineWidth = 1/2;
    private String mNormalLineColor = "#cccccc";
    private String setPointer = "#F26E7C";//三角指示器颜色
    private Paint mIndicatorViewPaint;//刻度数字画笔
    private int mScaleTextsize;//大刻度字体大小


    public interface OnValueChangeListener {
        void onValueChange(float value);
    }

    public ScaleRulerView(Context context) {
        this(context, null);
    }

    public ScaleRulerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public ScaleRulerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    protected void init(Context context) {

        mScroller = new Scroller(context);
        mDensity = context.getResources().getDisplayMetrics().density;

        mMinVelocity = ViewConfiguration.get(getContext()).getScaledMinimumFlingVelocity();
    }

    /**
     * @param defaultValue 初始值
     * @param maxValue     最大值
     */
    public void initViewParam(float defaultValue, float maxValue, float defaultMinValue) {
        mValue = defaultValue;
        mMaxValue = maxValue;
        mDefaultMinValue = defaultMinValue;

        invalidate();

        mLastX = 0;
        mMove = 0;
        notifyValueChange();
    }

    /**
     * 设置用于接收结果的监听器
     *
     * @param listener
     */
    public void setValueChangeListener(OnValueChangeListener listener) {
        mListener = listener;
    }


    /**
     * 测量
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    /**
     * 位置
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        mWidth = getWidth();
        mHeight = getHeight();
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //刻画刻度线
        drawScaleLine(canvas);

        //刻画倒三角指示器
        drawPointer(canvas);
    }


    /**
     * 从中间往两边开始画刻度线
     *
     * @param canvas
     */
    private void drawScaleLine(Canvas canvas) {
        canvas.save();
        mLinePaint.setStrokeWidth(mNormalLineWidth);
        mLinePaint.setColor(Color.parseColor(mNormalLineColor));
        TextPaint textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(TEXT_SIZE * mDensity);
        int width = mWidth;
        int drawCount = 0;
        float xPosition;
        for (int i = 0; drawCount <= 4 * width; i++) {
            xPosition = (width / 2 - mMove) + i * mLineDivider * mDensity;
            if (xPosition + getPaddingRight() < mWidth && (mValue + i) <= mMaxValue) {
                if ((mValue + i) % mModType == 0) {
                    canvas.drawLine(xPosition, 0, xPosition, mHeight-20, mLinePaint);
                } else {
                    if ((mValue + i) % 2 == 1) {
                        canvas.drawLine(xPosition, 0, xPosition, mHeight-ITEM_MIN_HEIGHT, mLinePaint);
                    } else if ((mValue + i) % 2 == 0) {
                        canvas.drawLine(xPosition, 0, xPosition, mHeight - ITEM_MAX_HEIGHT, mLinePaint);
                    }
                }
            }
            xPosition = (width / 2 - mMove) - i * mLineDivider * mDensity;
            if (xPosition > getPaddingLeft() && (mValue - i) >= mDefaultMinValue) {
                if ((mValue - i) % mModType == 0) {
                    canvas.drawLine(xPosition, 0, xPosition, mHeight-20, mLinePaint);
                } else {
                    if ((mValue - i) % 2 == 1) {
                        canvas.drawLine(xPosition, 0, xPosition, mHeight-ITEM_MAX_HEIGHT, mLinePaint);
                    } else if ((mValue - i) % 2 == 0) {
                        canvas.drawLine(xPosition, 0, xPosition, mHeight - ITEM_MIN_HEIGHT, mLinePaint);
                    }
                }
            }
            drawCount += 2 * mLineDivider * mDensity;
        }
        canvas.restore();
    }

    /**
     * 画指示器
     * @param canvas
     */
    public void drawPointer(Canvas canvas){
        float center_x = getMeasuredWidth()/2;
        float center_y = getMeasuredHeight()/55;
        Path path = new Path();
        path.moveTo(center_x-22,center_y);
        path.lineTo(center_x,center_y+29);
        path.lineTo(center_x+22,center_y);
        path.close();
        mLinePaint.setColor(Color.parseColor(setPointer));
        mLinePaint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path,mLinePaint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int xPosition = (int) event.getX();

        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mScroller.forceFinished(true);
                mLastX = xPosition;
                mMove = 0;
                break;
            case MotionEvent.ACTION_MOVE:
                mMove += (mLastX - xPosition);
                changeMoveAndValue();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                countMoveEnd();
                countVelocityTracker(event);
                return false;
            // break;
            default:
                break;
        }

        mLastX = xPosition;
        return true;
    }

    private void countVelocityTracker(MotionEvent event) {
        mVelocityTracker.computeCurrentVelocity(1000);
        float xVelocity = mVelocityTracker.getXVelocity();
        if (Math.abs(xVelocity) > mMinVelocity) {
            mScroller.fling(0, 0, (int) xVelocity, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
        }

    }

    private void changeMoveAndValue() {
        int tValue = (int) (mMove / (mLineDivider * mDensity));
        if (Math.abs(tValue) > 0) {
            mValue += tValue;
            mMove -= tValue * mLineDivider * mDensity;
            if (mValue <= mDefaultMinValue || mValue > mMaxValue) {
                mValue = mValue <= mDefaultMinValue ? mDefaultMinValue : mMaxValue;
                mMove = 0;
                mScroller.forceFinished(true);
            }

            notifyValueChange();
        }
        postInvalidate();
    }

    private void countMoveEnd() {
        int roundMove = Math.round(mMove / (mLineDivider * mDensity));
        mValue = mValue + roundMove;
        mValue = mValue <= 0 ? 0 : mValue;
        mValue = mValue > mMaxValue ? mMaxValue : mValue;

        mLastX = 0;
        mMove = 0;

        notifyValueChange();
        postInvalidate();
    }

    private void notifyValueChange() {
        if (null != mListener) {
            if (mModType == MOD_TYPE) {
                mListener.onValueChange(mValue);
            }
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            if (mScroller.getCurrX() == mScroller.getFinalX()) { // over
                countMoveEnd();
            } else {
                int xPosition = mScroller.getCurrX();
                mMove += (mLastX - xPosition);
                changeMoveAndValue();
                mLastX = xPosition;
            }
        }
    }
}
