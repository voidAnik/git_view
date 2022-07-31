package com.voidK.gitview.network;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.DataSource;

import com.voidK.gitview.R;
import com.voidK.gitview.db.GitQueryDao;
import com.voidK.gitview.models.GitQueryRepoModel.GitQueryRepo;
import com.voidK.gitview.models.GitQueryRepoModel.GitQueryRepoItem;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetroRoomRepository {
    private final APIService apiService;
    private final String TAG = getClass().getName() + "ANIK";
    private final Context context;
    private final GitQueryDao gitQueryDao;
    private MediatorLiveData<List<GitQueryRepoItem>> mItemLive = new MediatorLiveData<>();


    @Inject
    RetroRoomRepository(@ApplicationContext Context context, APIService apiService, GitQueryDao gitQueryDao) {
        this.apiService = apiService;
        this.gitQueryDao = gitQueryDao;
        this.context = context;
    }

    public LiveData<List<GitQueryRepoItem>> getAllQueryRepo() {
        return gitQueryDao.getAllRepo();
    }

    public DataSource.Factory<Integer,GitQueryRepoItem> getAllQueryRepoPaged() {
        return gitQueryDao.getPagedRepo();
    }

    void insertRecords(List<GitQueryRepoItem> repoItems){
        gitQueryDao.insertRecords(repoItems);
    }
    public void deleteRecords(){
        gitQueryDao.deleteAllRecords();
    }

    public void callAPI(HashMap<String, Object> queryParams, MutableLiveData<GitQueryRepo> liveData) {
        String end_point = context.getString(R.string.API_QUERY_REPO);
        Log.i(TAG, "end_point: "+ end_point);
        Call<GitQueryRepo> call = apiService.searchGitRepo(end_point, queryParams);

        call.enqueue(new RetryCallback(call) {
            @Override
            public void onResponse(Call<GitQueryRepo> call, Response<GitQueryRepo> response) {
                super.onResponse(call, response);
                Log.i(TAG, "not checked response: " + response.body());
                if (response.isSuccessful()) {
                    Log.i(TAG, "response: " + response.body().getItems().size());
                    Log.i(TAG, "avatar url: " + response.body().getItems().get(0).getOwner().getAvatar_url());
                    //gitQueryDao.deleteAllRecords();
                    insertRecords(response.body().getItems());
                    liveData.postValue(response.body());
                } else {
                    liveData.postValue(null);
                    Log.e(TAG, "response: " + "ERROR");
                }
            }

            @Override
            public void onFailure(Call<GitQueryRepo> call, Throwable t) {
                super.onFailure(call, t);
                Log.e(TAG, "response: " + "onFailure");
                liveData.postValue(null);
            }
        });
    }
}
