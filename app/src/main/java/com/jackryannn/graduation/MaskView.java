package com.jackryannn.graduation;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;

public class MaskView extends ImageView
{
    private static final String TAG = "Jack";
    int heightScreen;
    private Paint mAreaPaint;
    private Rect mCenterRect = null;
    private Context mContext;
    private Paint mLinePaint;
    int widthScreen;

    public MaskView(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
        initPaint();
        this.mContext = paramContext;
        DisplayMetrics localDisplayMetrics = paramContext.getResources().getDisplayMetrics();
        Point localPoint = new Point(localDisplayMetrics.widthPixels, localDisplayMetrics.heightPixels);
        this.widthScreen = localPoint.x;
        this.heightScreen = localPoint.y;
    }

    private void initPaint()
    {
        this.mLinePaint = new Paint(1);
        this.mLinePaint.setColor(-16776961);
        this.mLinePaint.setStyle(Paint.Style.STROKE);
        this.mLinePaint.setStrokeWidth(5.0F);
        this.mLinePaint.setAlpha(30);
        this.mAreaPaint = new Paint(1);
        this.mAreaPaint.setColor(0);
        this.mAreaPaint.setStyle(Paint.Style.FILL);
        this.mAreaPaint.setAlpha(180);
    }

    public void clearCenterRect(Rect paramRect)
    {
        this.mCenterRect = null;
    }

    protected void onDraw(Canvas paramCanvas)
    {
        Log.i("Jack", "onDraw...");
        if (this.mCenterRect == null)
            return;
        paramCanvas.drawRect(0F, 0F, this.widthScreen, this.mCenterRect.top, this.mAreaPaint);
        paramCanvas.drawRect(0F, 1 + this.mCenterRect.bottom, this.widthScreen, this.heightScreen, this.mAreaPaint);
        paramCanvas.drawRect(0F, this.mCenterRect.top, -1 + this.mCenterRect.left, 1 + this.mCenterRect.bottom, this.mAreaPaint);
        paramCanvas.drawRect(1 + this.mCenterRect.right, this.mCenterRect.top, this.widthScreen, 1 + this.mCenterRect.bottom, this.mAreaPaint);
        paramCanvas.drawRect(this.mCenterRect, this.mLinePaint);
        super.onDraw(paramCanvas);
    }

    public void setCenterRect(Rect paramRect)
    {
        Log.i("Jack", "setCenterRect...");
        this.mCenterRect = paramRect;
        postInvalidate();
    }
}