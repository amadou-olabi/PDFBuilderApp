package com.example.pdfbuilderapp;

import android.content.Context;
import android.graphics.Bitmap;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

public class ResizeImageView {
    /**
     * Converts a bitmap in a rounded drawable.
     * @param context
     * @param bitmap
     * @return
     * */

    public static Bitmap resizeBitmap(Bitmap bitmap, int width, int height, boolean applyFilter){
        return Bitmap.createScaledBitmap(bitmap,width,height,applyFilter);
    }

    public static RoundedBitmapDrawable getRoundedImageDrawable(
            Context context, Bitmap bitmap) {
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory
                .create(context.getResources(), bitmap);
        roundedBitmapDrawable.setCornerRadius(Math.max(bitmap.getWidth(),
                bitmap.getHeight()) / 2.0f);
        return roundedBitmapDrawable;
    }
}
