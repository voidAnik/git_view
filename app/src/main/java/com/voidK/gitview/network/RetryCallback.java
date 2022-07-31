package com.voidK.gitview.network;

import android.util.Log;

import com.voidK.gitview.models.GitQueryRepoModel.GitQueryRepo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class RetryCallback implements Callback<GitQueryRepo> {

    Call<GitQueryRepo> call;
    int MAX_RETRIES = 3;
    int retries = 0;
    private String TAG = RetryCallback.class.getName()+"ANIK";

    public RetryCallback(Call<GitQueryRepo> call) {
        this.call = call;
    }

    @Override
    public void onResponse(Call<GitQueryRepo> call, Response<GitQueryRepo> response) {
        if(!response.isSuccessful() && retries++ < MAX_RETRIES){
            retryCall();
        }
    }

    @Override
    public void onFailure(Call<GitQueryRepo> call, Throwable t) {
        if(retries++ < MAX_RETRIES){
            retryCall();
        }
    }

    private void retryCall(){
        Log.e(TAG, "retrying call");
        call.clone().enqueue(this);
    }
}
