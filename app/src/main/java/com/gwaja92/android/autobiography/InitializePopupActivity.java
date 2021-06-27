package com.gwaja92.android.autobiography;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import javax.xml.transform.sax.SAXSource;

import static com.gwaja92.android.autobiography.R.*;

public class InitializePopupActivity extends AppCompatActivity {
    Activity activity;
    TextView txt_Message;
    Dialog dialog;
    Button confirmBtn;

    public InitializePopupActivity(Activity activity) {
        this.activity = activity;
    }

    public void showDialog(String message){

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
    }



}
