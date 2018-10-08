package com.rezolve.gistscanner.ui.main;

import android.os.Bundle;

import com.rezolve.gistscanner.R;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import util.ActivityUtils;

public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            MainFragment fragment = MainFragment.class.cast(
                    getSupportFragmentManager().findFragmentById(R.id.container)
            );

            if (fragment == null) {
                fragment = mainFragment;
                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        fragment, R.id.container);
            }
        }
    }
}
