package com.gwaja92.android.autobiography;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static com.gwaja92.android.autobiography.R.id;
import static com.gwaja92.android.autobiography.R.layout;

public class InitializePopupActivity extends AppCompatActivity {
    Activity activity;
    TextView txt_Message;
    Dialog dialog;
    Button confirmBtn;
    View birthDaySelect;
    Dialog dateDialog;
    View dateConfirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(layout.initialize_popup_activity);

        confirmBtn = findViewById(id.popupConfirm);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

 /*   public InitializePopupActivity(Activity activity) {
        this.activity = activity;
    }*/

    public void showDialog(String message) {

        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(layout.initialize_popup_activity);

        txt_Message = dialog.findViewById(id.popuptext);
        //txt_Message.setText(message);

        dialog.show();

        confirmBtn = dialog.findViewById(id.popupConfirm);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        View birthDaySelect = dialog.findViewById(id.birthDaySelect);
        birthDaySelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  DatePickerActivity datePickerActivity = new DatePickerActivity(activity);
               // datePickerActivity.showCalenderDialog();

                Intent intent = new Intent(activity, DatePickerActivity.class);
                startActivityForResult(intent, 1);

            }
        });


    }


}
