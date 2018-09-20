package com.pachesoft.androidville;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class VilleMap extends View {
    private MainApp mainApp;
    private TextView txtHouseName;

    private String mVilleName;

    private Paint mTextPaint;
    private Paint mGridPaint;

    private Bitmap houseBitmap;
    private Bitmap houseSelectedBitmap;
    private Bitmap spotSelectedBitmap;

    private RectF houseBitmapSize;

    public AVHouse selectedHouse = null;

    private List<AVHouse> houses = null;

    private boolean isScrolling = false;

    public int selectedSpotX = -1;
    public int selectedSpotY = -1;

    public VilleMap(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();

        TypedArray myAttrs = context.getTheme().obtainStyledAttributes(attrs, R.styleable.VilleMap, 0, 0);

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
        houseSelectedBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.house_selected_icon);
        spotSelectedBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.spot_selected_icon);

        houseBitmapSize = new RectF(0, 0, 100, 100);
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        this.txtHouseName = mainApp.mainActivity.findViewById(R.id.txt_house_name);
    }

    public String getVilleName() {
        return mVilleName;
    }

    public void setVilleName(String name) {
        mVilleName = name;
        invalidate();
        requestLayout();
    }

    public void highlightHouse(int houseId) {
        boolean doInvalidate = false;
        if (selectedHouse != null) {
            selectedHouse.selected = false;
            selectedHouse = null;
            doInvalidate = true;
        }
        for (int i = 0; i < houses.size(); i++) {
            AVHouse house = houses.get(i);
            if (house.id == houseId) {
                selectedHouse = house;
                selectedHouse.selected = true;
                txtHouseName.setText(house.name);
                doInvalidate = true;
            }
        }
        if (doInvalidate) {
            invalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(2400,3600);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventAction = event.getAction();
        switch (eventAction) {
            case MotionEvent.ACTION_DOWN:
                final int x = (int) (event.getX() * 0.01f);
                final int y = (int) (event.getY() * 0.01f);

                new CountDownTimer(250, 1000) {

                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        if (isScrolling) {
                            return;
                        }
                        boolean houseSelected = false;
                        txtHouseName.setText("");

                        if (houses != null) {
                            if (selectedHouse != null) {
                                selectedHouse.selected = false;
                                selectedHouse = null;
                            }
                            for (int i = 0; i < houses.size(); i++) {
                                AVHouse house = houses.get(i);
                                if ((house.address.x == x) && (house.address.y == y)) {
                                    selectedHouse = house;
                                    selectedHouse.selected = true;

                                    txtHouseName.setText(house.name);

                                    houseSelected = true;
                                }
                            }
                        }

                        if (houseSelected) {
                            if (selectedSpotX != -1) {
                                selectedSpotX = -1;
                                selectedSpotY = -1;
                            }
                        }
                        else {
                            if ((selectedSpotX == x) && (selectedSpotY == y)) {
                                selectedSpotX = -1;
                                selectedSpotY = -1;
                            }
                            else {
                                selectedSpotX = x;
                                selectedSpotY = y;
                            }
                        }

                        mainApp.mainActivity.setHouseEditMode(houseSelected);

                        invalidate();
                    }
                }.start();

                break;
            default:
        }

        return false;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(getResources().getColor(R.color.darkGreen));
        canvas.drawText(mVilleName, 0, 100, mTextPaint);

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

                if (house.selected) {
                    canvas.drawBitmap(houseSelectedBitmap, null, houseBitmapSize, null);
                }

                canvas.restore();
            }
        }

        if (selectedSpotX != -1) {
            canvas.save();
            canvas.translate(selectedSpotX * 100,selectedSpotY * 100);
            canvas.drawBitmap(spotSelectedBitmap, null, houseBitmapSize, null);
            canvas.restore();
        }
    }

    public void setHouses(List<AVHouse> houses) {
        this.houses = houses;
        this.invalidate();
    }

    public void setIsScrolling(boolean isScrolling) {
        this.isScrolling = isScrolling;
    }
}
