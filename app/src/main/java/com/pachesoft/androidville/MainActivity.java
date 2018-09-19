package com.pachesoft.androidville;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {
    private MainApp mainApp;

    public VScroll vScroll;
    public LinearLayout dialogLayout;

    private VilleMap villeMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vScroll = findViewById(R.id.vScroll);
        villeMap = findViewById(R.id.mainVilleMap);
        dialogLayout = findViewById(R.id.ll_house_dialog);

        mainApp = (MainApp) getApplication();
        mainApp.setMainActivity(this);
    }

    public VilleMap getVilleMap() {
        return villeMap;
    }

    public void addHouseBtnClick(View v) {
        dialogLayout.setVisibility(View.VISIBLE);
    }
}
