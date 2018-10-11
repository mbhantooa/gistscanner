package com.rezolve.gistscanner.ui.landing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.rezolve.gistscanner.R;
import com.rezolve.gistscanner.ui.main.MainActivity;

import util.UIUtils;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        UIUtils.transparentToolbar(this);
    }

    public void launchMainActivity(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
