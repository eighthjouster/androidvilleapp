package com.pachesoft.androidville;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class VilleMap extends View {
    private String mVilleName;

    private Paint mTextPaint;
    private float mTextHeight;

    public VilleMap(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray myAttrs = context.getTheme().obtainStyledAttributes(attrs, R.styleable.VilleMap, 0, 0);
        init();

        try {
            mVilleName = myAttrs.getString(R.styleable.VilleMap_villeName);
        } finally {
            myAttrs.recycle();
        }
        System.out.println("==== THE VILLE NAME IS:");//__RP
        System.out.println(mVilleName);//__RP

    }

    private void init() {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(100.0f);
        mTextPaint.setColor(getResources().getColor(R.color.colorPrimary));
    }

    public String getVilleName() {
        return mVilleName;
    }

    public void setVilleName(String name) {
        mVilleName = name;
        invalidate();
        requestLayout();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(getResources().getColor(R.color.colorAccent));

        canvas.drawText(mVilleName, 100, 100, mTextPaint);
    }
}
