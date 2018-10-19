package com.rezolve.gistscanner.di;

import android.support.annotation.NonNull;

import com.rezolve.gistscanner.ui.main.MainActivity;
import com.rezolve.gistscanner.ui.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @NonNull
    @ActivityScoped
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity mainActivity();
}