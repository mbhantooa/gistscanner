package com.rezolve.gistscanner.ui;

import android.support.annotation.NonNull;

import com.rezolve.gistscanner.di.FragmentScoped;
import com.rezolve.gistscanner.ui.main.MainFragment;
import com.rezolve.gistscanner.ui.scanner.ScannerFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@SuppressWarnings("unused")
@Module
public abstract class MainModule {

    @NonNull
    @FragmentScoped
    @ContributesAndroidInjector
    abstract MainFragment mainFragment();

    @NonNull
    @FragmentScoped
    @ContributesAndroidInjector
    abstract ScannerFragment scannerFragment();
}
