package com.voidK.gitview.viewmodels;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.voidK.gitview.models.GitQueryRepoModel.GitQueryRepo;
import com.voidK.gitview.models.GitQueryRepoModel.GitQueryRepoItem;
import com.voidK.gitview.network.APIService;
import com.voidK.gitview.network.RetroRoomRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeActivityViewModel extends ViewModel {

    MutableLiveData<GitQueryRepo> liveData;
    APIService apiService;
    RetroRoomRepository retroRoomRepository;

    @Inject
    HomeActivityViewModel(APIService apiService, RetroRoomRepository retroRoomRepository) {
        this.apiService = apiService;
        this.liveData = new MutableLiveData<>();
        this.retroRoomRepository = retroRoomRepository;
    }

    public MutableLiveData<GitQueryRepo> getLiveData(){
        return liveData;
    }

    public LiveData<List<GitQueryRepoItem>> getAllQueryRepoList(){
        return retroRoomRepository.getAllQueryRepo();
    }

    public void repoQueryAPICall(Context context, HashMap<String, Object> queryParams){
        //RetroRoomRepository retroRoomRepository = new RetroRoomRepository(context, apiService);
        retroRoomRepository.callAPI(queryParams, liveData);
    }

    public void clearDatabase(){
        retroRoomRepository.deleteRecords();
    }

    public List<GitQueryRepoItem> sortByStars(List<GitQueryRepoItem> items){
        Comparator<GitQueryRepoItem> compareById = new Comparator<GitQueryRepoItem>() {
            @Override
            public int compare(GitQueryRepoItem o1, GitQueryRepoItem o2) {
                return o2.getStargazers_count().compareTo(o1.getStargazers_count());
            }
        };

        Collections.sort(items, compareById);
        return items;
    }
    public List<GitQueryRepoItem> sortByUpdated(List<GitQueryRepoItem> items){
        Comparator<GitQueryRepoItem> compareByUpdate = new Comparator<GitQueryRepoItem>() {
            @Override
            public int compare(GitQueryRepoItem o1, GitQueryRepoItem o2) {
                String date1 = o1.getUpdated_at().split("T")[0];
                String date2 = o2.getUpdated_at().split("T")[0];
                return date2.compareTo(date1);
            }
        };

        Collections.sort(items, compareByUpdate);
        return items;
    }

}
