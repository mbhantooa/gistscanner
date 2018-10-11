package com.rezolve.gistscanner.ui.scanner;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.vision.barcode.Barcode;
import com.rezolve.gistscanner.R;
import com.rezolve.gistscanner.di.ActivityScoped;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import info.androidhive.barcode.BarcodeReader;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScannerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
@ActivityScoped
public class ScannerFragment extends DaggerFragment implements BarcodeReader.BarcodeReaderListener {
    private OnFragmentInteractionListener listener;

    @Inject
    public ScannerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scanner, container, false);

        BarcodeReader barcodeReader = (BarcodeReader) getChildFragmentManager()
                .findFragmentById(R.id.barcode_scanner);
        if (barcodeReader != null)
            barcodeReader.setListener(this);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    //region BarcodeReader.BarcodeReaderListener Methods

    @Override
    public void onScanned(Barcode barcode) {
        if (listener != null)
            listener.onBarcodeDetected(barcode);

    }

    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String errorMessage) {
        if (listener != null)
            listener.onScanError(errorMessage);
    }

    @Override
    public void onCameraPermissionDenied() {
        if (listener != null)
            listener.onScanError(getString(R.string.camera_permission_denied));
    }

    //endregion

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onBarcodeDetected(Barcode barcode);

        void onScanError(String error);
    }
}
