package com.rezolve.gistscanner.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;
import com.rezolve.gistscanner.R;
import com.rezolve.gistscanner.ui.scanner.ScannerFragment;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import timber.log.Timber;

import com.rezolve.gistscanner.ui.util.UIUtils;

public class MainActivity extends DaggerAppCompatActivity implements
        ScannerFragment.OnFragmentInteractionListener {

    @Inject
    MainFragment mainFragment;

    @Inject
    ScannerFragment scannerFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            ScannerFragment fragment = ScannerFragment.class.cast(
                    getSupportFragmentManager().findFragmentById(R.id.container)
            );

            if (fragment == null) {
                addScannerFragment();
            }
        }
    }

    private void addScannerFragment() {
        UIUtils.addFragmentToActivity(getSupportFragmentManager(),
                scannerFragment, R.id.container, SCANNER_FRAGMENT_TAG);
    }

    private void showCommentListFragment(@NonNull Barcode barcode) {
        runOnUiThread(() -> {
            Timber.d("Found barcode: %s", barcode.displayValue);
            String gistId = barcode.displayValue;
            if (!TextUtils.isEmpty(barcode.displayValue) && mainFragment.getArguments() != null) {
                mainFragment.getArguments().putString(MainFragment.GIST_ID_BUNDLE_ARGUMENT,
                        gistId);

                UIUtils.animateFragment(getSupportFragmentManager(),
                        mainFragment, R.id.container, MAIN_FRAGMENT_TAG);
            }
        });
    }

    @Override
    public void onBarcodeDetected(@NonNull Barcode barcode) {
        showCommentListFragment(barcode);
    }

    @Override
    public void onScanError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        UIUtils.popFragment(getSupportFragmentManager());

        if (UIUtils.isFragmentVisible(getSupportFragmentManager(), SCANNER_FRAGMENT_TAG)) {
            finish();
        }
        return true;
    }


    private static final String MAIN_FRAGMENT_TAG = "main";
    private static final String SCANNER_FRAGMENT_TAG = "ScannerTag";
}
