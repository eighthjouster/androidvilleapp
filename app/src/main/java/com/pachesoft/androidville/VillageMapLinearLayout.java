package com.pachesoft.androidville;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class VillageMapLinearLayout extends LinearLayout {
    public VillageMapLinearLayout(Context context) {
        super(context);
        initializeViews(context);
    }

    public VillageMapLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeViews(context);
    }

    public VillageMapLinearLayout(Context context,
                       AttributeSet attrs,
                       int defStyle) {
        super(context, attrs, defStyle);
        initializeViews(context);
    }

    /**
     * Inflates the views in the layout.
     *
     * @param context
     *           the current context for the view.
     */
    private void initializeViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.vm_linear_layout, this);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        System.out.println("EVENT!!");//__RP
        System.out.println(event);//__RP
        return false;
    }
}
