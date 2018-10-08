package com.rezolve.gistscanner.ui.main;

import com.rezolve.gistscanner.data.GistRepository;
import com.rezolve.gistscanner.di.ActivityScoped;
import com.rezolve.gistscanner.di.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract MainFragment mainFragment();

    @Binds
    @ActivityScoped
    abstract GistRepository bindGistRepository(GistRepository gistRepository);
}
