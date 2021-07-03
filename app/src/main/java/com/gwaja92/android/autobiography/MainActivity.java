package com.gwaja92.android.autobiography;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    AutobiographyFragment autobiographyFragment;
    ExportFragment exportFragment;
    SettingFragment settingFragment;
    WriteFragment writeFragment;
    int year = 0;
    int month = 0;
    int day = 0;
    String name = "";
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exportFragment = new ExportFragment();
        autobiographyFragment = new AutobiographyFragment();
        settingFragment = new SettingFragment();
        writeFragment = new WriteFragment();
        sharedPref = getPreferences(Context.MODE_PRIVATE);

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
                   /* Bundle bundle = new Bundle();
                    bundle.putString("name", name);
                    bundle.putInt("year", year);
                    bundle.putInt("month", month);
                    bundle.putInt("day", day);
                    settingFragment.setArguments(bundle);*/
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, settingFragment).commit();
                    return true;
            }
            return false;
        });

        name = sharedPref.getString("sharedName", "");
        year = sharedPref.getInt("sharedYear", 0);
        month = sharedPref.getInt("sharedMonth", 0);
        day = sharedPref.getInt("sharedDay", 0);

        if (name.isEmpty() || year == 0 || month == 0 || day == 0) {
            Intent InitialIntent = new Intent(this, InitializePopupActivity.class);
            intentActivityResultLauncher.launch(InitialIntent);
        }
    }

    ActivityResultLauncher<Intent> intentActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    // There are no request codes
                    Intent data = result.getData();
                    assert data != null;
                    year = data.getIntExtra("mYear", 0);
                    month = data.getIntExtra("mMonth", 0);
                    day = data.getIntExtra("mDay", 0);
                    name = data.getStringExtra("mName");

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("sharedName", name);
                    editor.putInt("sharedYear", year);
                    editor.putInt("sharedMonth", month);
                    editor.putInt("sharedDay", day);
                    editor.apply();
                }
            });

}