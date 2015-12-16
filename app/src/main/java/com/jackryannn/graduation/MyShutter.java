package com.jackryannn.graduation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.widget.Button;

public class MyShutter extends Button
{
    public MyShutter(Context paramContext)
    {
        super(paramContext);
    }

    public MyShutter(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
    }

    protected void onDraw(Canvas paramCanvas)
    {
        super.onDraw(paramCanvas);
        int i = getWidth();
        Paint localPaint = new Paint(1);
        localPaint.setStyle(Paint.Style.FILL);
        localPaint.setColor(Color.BLACK);
        paramCanvas.drawCircle(i / 2, i / 2, i / 2, localPaint);
        paramCanvas.drawOval(0F, 0F, i - 10, i - 10, localPaint);
        localPaint.setColor(-1);
        paramCanvas.drawCircle(i / 2, i / 2, -5 + i / 2, localPaint);
    }
}