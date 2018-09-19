package com.pachesoft.androidville;

import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class VScroll extends ScrollView {
    private float mx, my;
    public HorizontalScrollView hScroll;
    VilleMap villeMap;

    MainActivity mainActivity;

    public VScroll(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public VScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VScroll(Context context) {
        super(context);
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        hScroll = mainActivity.findViewById(R.id.hScroll);
        villeMap = mainActivity.getVilleMap();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float curX, curY;

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                villeMap.setIsScrolling(false);
                mx = event.getX();
                my = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                villeMap.setIsScrolling(true);
                curX = event.getX();
                curY = event.getY();
                this.scrollBy((int) (mx - curX), (int) (my - curY));
                hScroll.scrollBy((int) (mx - curX), (int) (my - curY));
                mx = curX;
                my = curY;
                break;
            case MotionEvent.ACTION_UP:
                villeMap.setIsScrolling(false);
                curX = event.getX();
                curY = event.getY();
                this.scrollBy((int) (mx - curX), (int) (my - curY));
                hScroll.scrollBy((int) (mx - curX), (int) (my - curY));
                break;
        }

        return true;
    }
}