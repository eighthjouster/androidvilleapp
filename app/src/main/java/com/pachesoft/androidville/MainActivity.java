package com.pachesoft.androidville;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private MainApp mainApp;
    private EditText houseDialogTextField;

    public VScroll vScroll;
    public ConstraintLayout dialogLayout;

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
        houseDialogTextField = findViewById(R.id.txt_input_house_name);

        mainApp = (MainApp) getApplication();
        mainApp.setMainActivity(this);

        slideUpAnimation = (AnimatorSet) AnimatorInflater.loadAnimator(this,
                R.animator.slide_up);
        slideUpAnimation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }
            @Override
            public void onAnimationEnd(Animator animator) {
                houseDialogTextField.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);            }
            @Override
            public void onAnimationCancel(Animator animator) {

            }
            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        slideUpAnimation.setTarget(dialogLayout);

        slideDownAnimation = (AnimatorSet) AnimatorInflater.loadAnimator(this,
                R.animator.slide_down);
        slideDownAnimation.setTarget(dialogLayout);

        houseDialogTextField.setFocusableInTouchMode(true);
    }

    public VilleMap getVilleMap() {
        return villeMap;
    }

    public void addHouseBtnClick(View v) {
        slideUpAnimation.start();
    }

    public void onDismissHouseDialogBtnClick(View v) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        slideDownAnimation.start();
    }

    public void onAddHouseBtnClick(View v) {
        AVHouse newHouse = new AVHouse(5, houseDialogTextField.getText().toString(), new AVAddress(7, 7));

        mainApp.serverComm.addHouse(newHouse, new Callback<AVHouse>() {
            @Override
            public void onResponse(Call<AVHouse> call, Response<AVHouse> response) {
                slideDownAnimation.start();
                houseDialogTextField.setText("");
                mainApp.getAllHouses();
            }

            @Override
            public void onFailure(Call<AVHouse> call, Throwable t) {

            }
        });

    }
}
