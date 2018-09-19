package com.pachesoft.androidville;

import android.widget.ScrollView;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class VScroll extends ScrollView {

    public VScroll(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public VScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VScroll(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        System.out.println("VSCROLL TOUCH EVENT!");//__RP
        return false;
    }
}