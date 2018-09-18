package com.pachesoft.androidville;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

public class VilleMap extends View {
    private String mVilleName;

    private Paint mTextPaint;
    private Paint mGridPaint;

    private Bitmap houseBitmap;
    private RectF houseBitmapSize;

    private List<AVHouse> houses = null;

    public VilleMap(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray myAttrs = context.getTheme().obtainStyledAttributes(attrs, R.styleable.VilleMap, 0, 0);
        init();

        try {
            mVilleName = myAttrs.getString(R.styleable.VilleMap_villeName);
        } finally {
            myAttrs.recycle();
        }
    }

    private void init() {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(100.0f);
        mTextPaint.setColor(getResources().getColor(R.color.white));

        mGridPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mGridPaint.setColor(getResources().getColor(R.color.yellow));
        mGridPaint.setStrokeWidth(2);

        houseBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.house_icon);
        houseBitmapSize = new RectF(0, 0, 100, 100);
    }

    public String getVilleName() {
        return mVilleName;
    }

    public void setVilleName(String name) {
        mVilleName = name;
        invalidate();
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(2400,3600);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(getResources().getColor(R.color.darkGreen));
        canvas.drawText(mVilleName, 100, 700, mTextPaint);

        for (int i=0; i<3600;i+=100) {
            canvas.drawLine(0, i, 2400, i, mGridPaint);
        }

        for (int i=0; i<2400;i+=100) {
            canvas.drawLine(i, 0, i, 3600, mGridPaint);
        }

        if (houses != null) {
            for (int i=0; i<houses.size(); i++) {
                AVHouse house = houses.get(i);

                canvas.save();
                canvas.translate(house.address.x * 100,house.address.y * 100);
                canvas.drawBitmap(houseBitmap, null, houseBitmapSize, null);
                canvas.restore();
            }
        }
    }

    public void setHouses(List<AVHouse> houses) {
        this.houses = houses;
    }
}
