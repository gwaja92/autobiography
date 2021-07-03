package com.gwaja92.android.autobiography;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
        confirmBtn.setOnClickListener(v -> {
            if (editTextTextPersonName.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (year == 0 || month == 0 || day == 0) {
                Toast.makeText(getBaseContext(), "생일을 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Intent data send
            Intent intent = new Intent();
            intent.putExtra("mYear", year);
            intent.putExtra("mMonth", month);
            intent.putExtra("mDay", day);
            intent.putExtra("mName", editTextTextPersonName.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        });


        Intent intentBirthday = new Intent(this, DatePickerActivity.class);

        birthDaySelect = findViewById(id.birthDaySelect);
        birthDaySelect.setOnClickListener(v -> datePickerActivityResultLauncher.launch(intentBirthday));

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Don't close when click the outside of activity
        return event.getAction() != MotionEvent.ACTION_OUTSIDE;
    }

    @Override
    public void onBackPressed() {
        // Don't operate Android's back button
    }

    ActivityResultLauncher<Intent> datePickerActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    // There are no request codes
                    Intent data = result.getData();
                    assert data != null;
                    year = data.getIntExtra("mYear", 0);
                    month = data.getIntExtra("mMonth", 0);
                    day = data.getIntExtra("mDay", 0);

                    String dateStr = year + "년 " +
                            month + "월 " + day + "일";

                    birthDaySelect.setText(dateStr);
                }
            });

}
