package com.rezolve.gistscanner.di;

import com.rezolve.gistscanner.ui.main.MainActivity;
import com.rezolve.gistscanner.ui.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity mainActivity();
}