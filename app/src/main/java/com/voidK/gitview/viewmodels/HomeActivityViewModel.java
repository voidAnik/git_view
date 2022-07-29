package com.voidK.gitview.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.voidK.gitview.models.GitQueryRepoModel.GitQueryRepoItem;
import com.voidK.gitview.network.APIService;
import com.voidK.gitview.network.RetroRoomRepository;

import java.util.HashMap;
import java.util.List;


import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeActivityViewModel extends ViewModel {

    //MutableLiveData<List<GitQueryRepoItem>> liveData;
    APIService apiService;
    RetroRoomRepository retroRoomRepository;

    @Inject
    HomeActivityViewModel(APIService apiService, RetroRoomRepository retroRoomRepository) {
        this.apiService = apiService;
        /*this.liveData = new MutableLiveData<>();*/
        this.retroRoomRepository = retroRoomRepository;
    }

    /*public MutableLiveData<List<GitQueryRepoItem>> getLiveData(){
        return liveData;
    }*/

    public LiveData<List<GitQueryRepoItem>> getAllQueryRepoList(){
        return retroRoomRepository.getAllQueryRepo();
    }
    public LiveData<PagedList<GitQueryRepoItem>> getAllQueryRepoListPaged(PagedList.Config config){
        DataSource.Factory<Integer,GitQueryRepoItem>factory = retroRoomRepository.getAllQueryRepoPaged();
        return new LivePagedListBuilder<>(factory, config)
                .build();
    }

    public void repoQueryAPICall(Context context, HashMap<String, Object> queryParams){
        //RetroRoomRepository retroRoomRepository = new RetroRoomRepository(context, apiService);
        retroRoomRepository.callAPI(queryParams/*, liveData*/);
    }
}
