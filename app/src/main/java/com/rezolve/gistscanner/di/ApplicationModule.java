package com.rezolve.gistscanner.di;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ApplicationModule {

    // Expose Application as an injectable context
    @NonNull
    @Binds
    abstract Context bindContext(Application application);
}
