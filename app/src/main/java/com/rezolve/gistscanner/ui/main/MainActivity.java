package com.rezolve.gistscanner.ui.main;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;
import com.rezolve.gistscanner.R;
import com.rezolve.gistscanner.ui.scanner.ScannerFragment;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import timber.log.Timber;
import util.UIUtils;

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

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);
        } else {
            if (savedInstanceState == null) {
                ScannerFragment fragment = ScannerFragment.class.cast(
                        getSupportFragmentManager().findFragmentById(R.id.container)
                );

                if (fragment == null) {
                    addFragment();
                }
            }
        }
    }

    private void addFragment() {
        UIUtils.addFragmentToActivity(getSupportFragmentManager(),
                scannerFragment, R.id.container);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (PERMISSION_REQUEST_CODE == requestCode) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                addFragment();
            } else {
                Toast.makeText(this, R.string.camera_permission_required, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBarcodeDetected(Barcode barcode) {
        Timber.d("Found barcode: " + barcode.displayValue);
    }
}
