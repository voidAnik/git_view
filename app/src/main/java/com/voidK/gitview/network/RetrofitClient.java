package com.voidK.gitview.network;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.voidK.gitview.models.gitqueryrepo.GitQueryRepo;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitClient {
    private APIService apiService;
    private String TAG = getClass().getName() +"ANIK";

    public RetrofitClient(APIService apiService) {
        this.apiService = apiService;
    }

    public void callAPI(String query, MutableLiveData<List<GitQueryRepo>> liveData){
        Call<GitQueryRepo> call = apiService.searchGitRepo(query);
        call.enqueue(new Callback<GitQueryRepo>() {
            @Override
            public void onResponse(@NonNull Call<GitQueryRepo> call, @NonNull Response<GitQueryRepo> response) {
                if(response.isSuccessful()){
                    Log.i(TAG, "response: "+response.body());
                    liveData.postValue(Collections.singletonList(response.body()));
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
