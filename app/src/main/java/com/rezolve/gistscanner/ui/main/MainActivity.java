package com.rezolve.gistscanner.ui.main;

import android.os.Bundle;

import com.rezolve.gistscanner.R;
import com.rezolve.gistscanner.ui.scanner.ScannerFragment;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import util.ActivityUtils;

public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    MainFragment mainFragment;

    @Inject
    ScannerFragment scannerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            ScannerFragment fragment = ScannerFragment.class.cast(
                    getSupportFragmentManager().findFragmentById(R.id.container)
            );

            if (fragment == null) {
                fragment = scannerFragment;
                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        fragment, R.id.container);
            }
        }
    }
}
