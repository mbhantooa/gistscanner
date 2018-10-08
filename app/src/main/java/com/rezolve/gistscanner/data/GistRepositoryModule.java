package com.rezolve.gistscanner.data;

import com.rezolve.gistscanner.data.retrofit.RetrofitGistDataSource;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class GistRepositoryModule {

    @Singleton
    @Binds
    @Remote
    abstract IGistDataSource provideRemoteGistDataSource(RetrofitGistDataSource gistDataSource);
}
