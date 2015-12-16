package com.jackryannn.graduation;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class ImageUtil
{
    public static Bitmap getRotateBitmap(Bitmap paramBitmap, float paramFloat)
    {
        Matrix localMatrix = new Matrix();
        localMatrix.setRotate(paramFloat);
        return Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), localMatrix, true);
    }
}