package com.gwaja92.android.autobiography;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static com.gwaja92.android.autobiography.R.id;
import static com.gwaja92.android.autobiography.R.layout;

public class InitializePopupActivity extends AppCompatActivity {

    Button confirmBtn;
    View birthDaySelect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(layout.initialize_popup_activity);

        getWindow().getAttributes().width = 850;
        getWindow().getAttributes().height = 820;

        confirmBtn = findViewById(id.popupConfirm);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Intent intentBirtday = new Intent(this, DatePickerActivity.class);

        birthDaySelect = findViewById(id.birthDaySelect);
        birthDaySelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(intentBirtday, 1234);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1234 && resultCode == RESULT_OK) {
            int year = data.getIntExtra("mYear", 0);
            int month = data.getIntExtra("mMonth", 0);
            int day = data.getIntExtra("mDay", 0);

            String dateStr = Integer.toString(month) + "/" + Integer.toString(day) +
                    "/" + Integer.toString(year);
            ((TextView) birthDaySelect).setText(dateStr);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Don't close when click the outside of activity
        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        // Don't operate Android's back button
        return;
    }

}
