package com.voidK.gitview.viewmodels;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.voidK.gitview.models.gitqueryrepo.GitQueryRepo;
import com.voidK.gitview.network.APIService;
import com.voidK.gitview.network.RetrofitClient;

import java.util.HashMap;


import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeActivityViewModel extends ViewModel {

    MutableLiveData<GitQueryRepo> liveData;
    APIService apiService;

    @Inject
    HomeActivityViewModel(APIService apiService) {
        this.apiService = apiService;
        this.liveData = new MutableLiveData<>();
    }

    public MutableLiveData<GitQueryRepo> getLiveData(){
        return liveData;
    }

    public void repoQueryAPICall(Context context, HashMap<String, Object> queryParams){
        RetrofitClient retrofitClient = new RetrofitClient(context, apiService);
        retrofitClient.callAPI(queryParams, liveData);
    }
}
