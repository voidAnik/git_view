package com.voidK.gitview.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.voidK.gitview.models.gitqueryrepo.GitQueryRepo;
import com.voidK.gitview.network.APIService;
import com.voidK.gitview.network.RetrofitClient;

import java.util.List;


import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeActivityViewModel extends ViewModel {

    MutableLiveData<List<GitQueryRepo>> liveData;
    APIService apiService;

    @Inject
    HomeActivityViewModel(APIService apiService) {
        this.apiService = apiService;
        this.liveData = new MutableLiveData<>();
    }

    /*public HomeActivityViewModel() {
        this.liveData = new MutableLiveData<>();
    }*/

    public MutableLiveData<List<GitQueryRepo>> getLiveData(){
        return liveData;
    }

    public void repoQueryAPICall(String query){
        RetrofitClient retrofitClient = new RetrofitClient(apiService);
        retrofitClient.callAPI(query, liveData);
    }
}
