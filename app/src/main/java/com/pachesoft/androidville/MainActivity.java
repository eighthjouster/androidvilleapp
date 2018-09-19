package com.pachesoft.androidville;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private MainApp mainApp;

    public VScroll vScroll;
    public LinearLayout dialogLayout;

    private VilleMap villeMap;

    private AnimatorSet slideUpAnimation;
    private AnimatorSet slideDownAnimation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vScroll = findViewById(R.id.vScroll);
        villeMap = findViewById(R.id.mainVilleMap);
        dialogLayout = findViewById(R.id.ll_house_dialog);

        mainApp = (MainApp) getApplication();
        mainApp.setMainActivity(this);

        slideUpAnimation = (AnimatorSet) AnimatorInflater.loadAnimator(this,
                R.animator.slide_up);
        slideUpAnimation.setTarget(dialogLayout);

        slideDownAnimation = (AnimatorSet) AnimatorInflater.loadAnimator(this,
                R.animator.slide_down);
        slideDownAnimation.setTarget(dialogLayout);
    }

    public VilleMap getVilleMap() {
        return villeMap;
    }

    public void addHouseBtnClick(View v) {
        slideUpAnimation.start();
    }

    public void otherBtnClick(View v) {
        slideDownAnimation.start();
    }
}
