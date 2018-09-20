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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private MainApp mainApp;
    private EditText houseDialogTextField;

    public VScroll vScroll;
    public ConstraintLayout dialogLayout;

    private VilleMap villeMap;
    private Button addEditButton;
    private Button deleteButton;
    private Button addEditDialogButton;
    private Button cancelDialogButton;

    public int nextHouseId = 1;

    private AnimatorSet slideUpAnimation;
    private AnimatorSet slideDownAnimation;

    private boolean houseEditMode = false;
    private TextView selectedHouseName;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vScroll = findViewById(R.id.vScroll);
        villeMap = findViewById(R.id.mainVilleMap);
        dialogLayout = findViewById(R.id.ll_house_dialog);
        houseDialogTextField = findViewById(R.id.txt_input_house_name);
        addEditButton = findViewById(R.id.btn_add_house);
        deleteButton = findViewById(R.id.btn_delete_house);
        addEditDialogButton = findViewById(R.id.action_button);
        cancelDialogButton = findViewById(R.id.cancel_button);
        selectedHouseName = findViewById(R.id.txt_house_name);


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
                addEditButton.setVisibility(View.INVISIBLE);
                deleteButton.setVisibility(View.INVISIBLE);
                cancelDialogButton.setVisibility(View.VISIBLE);
                houseDialogTextField.requestFocus();
                showSoftKeyboard();
            }
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
        slideDownAnimation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }
            @Override
            public void onAnimationEnd(Animator animator) {
                addEditButton.setVisibility(View.VISIBLE);
                deleteButton.setVisibility(View.VISIBLE);
            }
            @Override
            public void onAnimationCancel(Animator animator) {

            }
            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        slideDownAnimation.setTarget(dialogLayout);

        houseDialogTextField.setFocusableInTouchMode(true);

        setHouseEditMode(false);
    }

    public VilleMap getVilleMap() {
        return villeMap;
    }

    public void addEditHouseBtnClick(View v) {
        if (houseEditMode && villeMap.selectedHouse != null) {
            houseDialogTextField.setText(villeMap.selectedHouse.name);
            houseDialogTextField.selectAll();
        }
        slideUpAnimation.start();
    }

    public void deleteHouseBtnClick(View v) {
        if (houseEditMode && villeMap.selectedHouse != null) {
            final int selectedSpotX = villeMap.selectedHouse.address.x;
            final int selectedSpotY = villeMap.selectedHouse.address.y;
            villeMap.selectedSpotY = -1;
            mainApp.serverComm.deleteHouse(villeMap.selectedHouse, new Callback<AVHouse>() {
                @Override
                public void onResponse(Call<AVHouse> call, Response<AVHouse> response) {
                    houseDialogTextField.setText("");
                    villeMap.selectedHouse = null;
                    villeMap.selectedSpotX = selectedSpotX;
                    villeMap.selectedSpotY = selectedSpotY;
                    setHouseEditMode(false);
                    mainApp.getAllHouses();
                }

                @Override
                public void onFailure(Call<AVHouse> call, Throwable t) {

                }
            });
        }
    }

    public void onDismissHouseDialogBtnClick(View v) {
        dismissSoftKeyboard();
        cancelDialogButton.setVisibility(View.INVISIBLE);
        slideDownAnimation.start();
    }

    public void onAddEditHouseBtnClick(View v) {
        String houseName = houseDialogTextField.getText().toString();
        if (!"".equals(houseName)) {
            if (villeMap.selectedHouse == null) {
                final int houseId = nextHouseId++;
                AVHouse newHouse = new AVHouse(houseId, houseName, new AVAddress(villeMap.selectedSpotX, villeMap.selectedSpotY));

                mainApp.serverComm.addHouse(newHouse, new Callback<AVHouse>() {
                    @Override
                    public void onResponse(Call<AVHouse> call, Response<AVHouse> response) {
                        dismissSoftKeyboard();
                        cancelDialogButton.setVisibility(View.INVISIBLE);
                        slideDownAnimation.start();
                        houseDialogTextField.setText("");
                        mainApp.getAllHouses(houseId);
                        villeMap.selectedSpotX = -1;
                        villeMap.selectedSpotY = -1;
                        setHouseEditMode(true);
                    }

                    @Override
                    public void onFailure(Call<AVHouse> call, Throwable t) {

                    }
                });
            }
            else {
                AVHouse editHouse = villeMap.selectedHouse;
                editHouse.name = houseName;

                mainApp.serverComm.updateHouse(editHouse, new Callback<AVHouse>() {
                    @Override
                    public void onResponse(Call<AVHouse> call, Response<AVHouse> response) {
                        System.out.println(response.message());
                        dismissSoftKeyboard();
                        slideDownAnimation.start();
                        houseDialogTextField.setText("");
                    }

                    @Override
                    public void onFailure(Call<AVHouse> call, Throwable t) {
                        selectedHouseName.setText("Fail");
                    }
                });
            }
        }
    }

    private void showSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
    }

    private void dismissSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    public void setHouseEditMode(boolean editMode) {
        houseEditMode = editMode;

        if (houseEditMode) {
            addEditButton.setText("Edit house");
            addEditDialogButton.setText("Edit house");
            deleteButton.setTextColor(getResources().getColor(R.color.red));
        }
        else {
            addEditButton.setText("Add house");
            addEditDialogButton.setText("Add house");
            deleteButton.setTextColor(getResources().getColor(R.color.softRed));
        }
        deleteButton.setEnabled(houseEditMode);
    }
}
