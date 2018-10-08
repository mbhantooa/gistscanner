package com.rezolve.gistscanner.di;

import android.app.Application;

import com.rezolve.gistscanner.MyApplication;
import com.rezolve.gistscanner.ui.main.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class,
        ViewModelModule.class
})
public interface AppComponent extends AndroidInjector<MyApplication> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
