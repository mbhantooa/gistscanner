package com.rezolve.gistscanner.ui;

import com.rezolve.gistscanner.di.FragmentScoped;
import com.rezolve.gistscanner.ui.main.MainFragment;
import com.rezolve.gistscanner.ui.scanner.ScannerFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@SuppressWarnings("unused")
@Module
public abstract class MainModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract MainFragment mainFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract ScannerFragment scannerFragment();
}
