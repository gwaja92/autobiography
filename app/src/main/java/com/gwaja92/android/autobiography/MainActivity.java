package com.gwaja92.android.autobiography;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    AutobiographyFragment autobiographyFragment;
    ExportFragment exportFragment;
    SettingFragment settingFragment;
    WriteFragment writeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exportFragment = new ExportFragment();
        autobiographyFragment = new AutobiographyFragment();
        settingFragment = new SettingFragment();
        writeFragment = new WriteFragment();

        ActionBar bar = getSupportActionBar();
        if (bar != null)
            bar.hide();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, writeFragment).commit();

        BottomNavigationView bottom_menu = findViewById(R.id.bottom_menu);
        bottom_menu.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.write_tab:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, writeFragment).commit();
                    return true;
                case R.id.autobiography_tab:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, autobiographyFragment).commit();
                    return true;
                case R.id.export_tab:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, exportFragment).commit();
                    return true;
                case R.id.setting_tab:
                    Bundle bundle = new Bundle();
                    String ddd = "dddddd";
                    bundle.putString("send", ddd);
                    settingFragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, settingFragment).commit();
                    return true;
            }
            return false;
        });

        Intent intent = new Intent(this, InitializePopupActivity.class);
        startActivityForResult(intent, 1234);



    }

}