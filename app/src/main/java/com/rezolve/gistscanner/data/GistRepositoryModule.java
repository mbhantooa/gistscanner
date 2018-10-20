package com.rezolve.gistscanner.data;

import android.support.annotation.NonNull;

import com.rezolve.gistscanner.Remote;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class GistRepositoryModule {

    @NonNull
    @Singleton
    @Binds
    @Remote
    abstract IGistDataSource provideRemoteGistDataSource(RetrofitGistDataSource gistDataSource);
}
