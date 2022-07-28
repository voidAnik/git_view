package com.voidK.gitview.di.module;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.voidK.gitview.BuildConfig;
import com.voidK.gitview.db.GitQueryDao;
import com.voidK.gitview.db.GitQueryDatabase;
import com.voidK.gitview.network.APIService;
import com.voidK.gitview.utils.mSharedPreferences;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class ApplicationModule {

    @Singleton
    @Provides
    APIService provideService(Retrofit retrofit){
        return retrofit.create(APIService.class);
    }

    @Singleton
    @Provides
    Retrofit provideRetrofitInstance(@ApplicationContext Context context){
        String token = new mSharedPreferences(context).getToken();
        Log.i("ApplicationModule", "TOKEN: "+token);
        // Setting OkHttpClient to set central access token
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @NonNull
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + token)
                        .header("User-Agent", "GitView")
                        .header("Accept", "application/vnd.github+json")
                        .method(original.method(), original.body())
                        .build();

                Response response = chain.proceed(newRequest);
                Log.i("ApplicationModule", "okhttp response code: "+response.code());
                return response;
            }
        }).build();

        return new Retrofit.Builder()
                .baseUrl(BuildConfig.GIT_API_BASE_URL) //To secure base url
                //.client(client) // token needed for now
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    mSharedPreferences providePreferences(@ApplicationContext Context context){
        return new mSharedPreferences(context);
    }

    @Singleton
    @Provides
   GitQueryDatabase provideGitQueryDatabase(@ApplicationContext Context context){
        return GitQueryDatabase.getDatabase(context);
    }

    @Singleton
    @Provides
    GitQueryDao provideGitQueryDao(GitQueryDatabase gitQueryDatabase){
        return gitQueryDatabase.GitQueryDao();
    }


}
