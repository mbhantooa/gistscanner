package com.rezolve.gistscanner.ui.main;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

    private static final int PERMISSION_REQUEST_CODE = 1234;

    @Inject
    MainFragment mainFragment;

    @Inject
    ScannerFragment scannerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);
        } else {
            if (savedInstanceState == null) {
                ScannerFragment fragment = ScannerFragment.class.cast(
                        getSupportFragmentManager().findFragmentById(R.id.container)
                );

                if (fragment == null) {
                    addScannerFragment();
                }
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (PERMISSION_REQUEST_CODE == requestCode) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                addScannerFragment();
            } else {
                Toast.makeText(this, R.string.camera_permission_required,
                        Toast.LENGTH_SHORT).show();
            }
        }
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
