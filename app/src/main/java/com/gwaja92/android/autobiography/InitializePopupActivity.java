package com.gwaja92.android.autobiography;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;
import android.widget.TextView;

public class InitializePopupActivity {
    Activity activity;
    TextView txt_Message;
    Dialog dialog;

    public InitializePopupActivity(Activity activity) {
        this.activity = activity;
    }

    public void showDialog(String message){
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.initialize_popup_activity);


        txt_Message = dialog.findViewById(R.id.popuptext);
        txt_Message.setText(message);

        dialog.show();

    }
    public void dimiss(){

        try {
            dialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
