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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static com.gwaja92.android.autobiography.R.id;
import static com.gwaja92.android.autobiography.R.layout;

public class InitializePopupActivity extends AppCompatActivity {

    Button confirmBtn;
    TextView birthDaySelect;
    EditText editTextTextPersonName;
    int year = 0;
    int month = 0;
    int day = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(layout.initialize_popup_activity);

        getWindow().getAttributes().width = 850;
        getWindow().getAttributes().height = 820;

        editTextTextPersonName = findViewById(id.editTextTextPersonName);

        confirmBtn = findViewById(id.popupConfirm);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextTextPersonName.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "이름을 입력해주세요.", Toast.LENGTH_LONG).show();
                    return;
                }
                if (year == 0 || month == 0 || day == 0) {
                    Toast.makeText(getApplicationContext(), "생일을 입력해주세요.", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("mYear", year);
                intent.putExtra("mMonth", month);
                intent.putExtra("mDay", day);
                intent.putExtra("mName", editTextTextPersonName.getText().toString());
                setResult(RESULT_OK, intent);
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
            year = data.getIntExtra("mYear", 0);
            month = data.getIntExtra("mMonth", 0);
            day = data.getIntExtra("mDay", 0);

            String dateStr = Integer.toString(year) + "년 " +
                    Integer.toString(month) + "월 " + Integer.toString(day) + "일";

            birthDaySelect.setText(dateStr);
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
