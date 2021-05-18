package com.github.hariprasanths.floatingtoast;

import android.graphics.Typeface;
import android.view.View;

public interface FloatingToastStyle {

    FloatingToastStyle setGravity(int gravity);

    FloatingToastStyle setGravity(int gravity, int xOffset, int yOffset);

    FloatingToastStyle setFadeOutDuration(int fadeOutDuration);

    FloatingToastStyle setTextColor(int color);

    FloatingToastStyle setTextTypeface(Typeface typeFace);

    FloatingToastStyle setTextStyle(int style);

    FloatingToastStyle setTextSizeInSp(float sizeInSp);

    FloatingToastStyle setTextSizeInDp(float sizeInDp);

    FloatingToastStyle setTextSizeCustomUnit(int unit, float size);

    FloatingToastStyle setFloatDistance(float floatDistance);

    FloatingToastStyle setShadowLayer(float shadowRadius, float shadowDx, float shadowDy, int shadowColor);

    FloatingToastStyle setBackgroundBlur(boolean bool);

    void showAtTouchPosition(View view);

    void show();
}
