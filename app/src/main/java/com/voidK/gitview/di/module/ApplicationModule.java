package com.voidK.gitview.di.module;

import android.content.Context;

import com.voidK.gitview.BuildConfig;
import com.voidK.gitview.network.APIService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class ApplicationModule {

    @Singleton
    @Provides
    public APIService getService(Retrofit retrofit){
        return retrofit.create(APIService.class);
    }

    @Singleton
    @Provides
    public Retrofit getRetrofitInstance(@ApplicationContext Context context){
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.GIT_API_BASE_URL) //To secure base url
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
