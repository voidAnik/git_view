package com.voidK.gitview.network;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.voidK.gitview.R;
import com.voidK.gitview.models.gitqueryrepo.GitQueryRepo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitClient {
    private final APIService apiService;
    private final String TAG = getClass().getName() +"ANIK";
    private final Context context;

    public RetrofitClient(Context context, APIService apiService) {
        this.apiService = apiService;
        this.context = context;
    }

    public void callAPI(HashMap<String, Object> queryParams, MutableLiveData<GitQueryRepo> liveData){

        Call<GitQueryRepo> call = apiService.searchGitRepo(context.getString(R.string.API_QUERY_REPO), queryParams);
        call.enqueue(new Callback<GitQueryRepo>() {
            @Override
            public void onResponse(@NonNull Call<GitQueryRepo> call, @NonNull Response<GitQueryRepo> response) {
                if(response.isSuccessful()){
                    Log.i(TAG, "response: "+response.body().getItems().size());
                    liveData.postValue(response.body());
                } else {
                    liveData.postValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<GitQueryRepo> call, @NonNull Throwable t) {
                liveData.postValue(null);
            }
        });
    }
}
