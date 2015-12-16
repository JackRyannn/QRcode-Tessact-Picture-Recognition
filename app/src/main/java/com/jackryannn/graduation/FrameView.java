package com.jackryannn.graduation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class FrameView extends View
{
    public static int height;
    public static int width;

    public FrameView(Context paramContext)
    {
        super(paramContext);
        WindowManager localWindowManager = (WindowManager)getContext().getSystemService("window");
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        localWindowManager.getDefaultDisplay().getMetrics(localDisplayMetrics);
        width = localDisplayMetrics.widthPixels;
        height = localDisplayMetrics.heightPixels;
    }

    public FrameView(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
    }

    protected void onDraw(Canvas paramCanvas)
    {
        paramCanvas.drawColor(0);
        Paint localPaint = new Paint(1);
        localPaint.setStyle(Paint.Style.STROKE);
        localPaint.setStrokeWidth(10.0F);
        localPaint.setColor(-16716050);
        Path localPath = new Path();
        localPath.moveTo(-305 + width / 2, -300 + height / 2);
        localPath.lineTo(-275 + width / 2, -300 + height / 2);
        localPath.moveTo(275 + width / 2, -300 + height / 2);
        localPath.lineTo(300 + width / 2, -300 + height / 2);
        localPath.lineTo(300 + width / 2, -275 + height / 2);
        localPath.moveTo(300 + width / 2, -125 + height / 2);
        localPath.lineTo(300 + width / 2, -100 + height / 2);
        localPath.lineTo(275 + width / 2, -100 + height / 2);
        localPath.moveTo(-275 + width / 2, -100 + height / 2);
        localPath.lineTo(-300 + width / 2, -100 + height / 2);
        localPath.lineTo(-300 + width / 2, -125 + height / 2);
        localPath.moveTo(-300 + width / 2, -275 + height / 2);
        localPath.lineTo(-300 + width / 2, -300 + height / 2);
        paramCanvas.drawPath(localPath, localPaint);
        localPaint.setStyle(Paint.Style.FILL);
        localPaint.setColor(-7829368);
        localPaint.setAlpha(180);
        paramCanvas.drawRect(0F, 0F, width, -305 + height / 2, localPaint);
        paramCanvas.drawRect(0F, -305 + height / 2, -305 + width / 2, -95 + height / 2, localPaint);
        paramCanvas.drawRect(305 + width / 2, -305 + height / 2, width, -95 + height / 2, localPaint);
        paramCanvas.drawRect(0F, -95 + height / 2, width, height, localPaint);
    }
}