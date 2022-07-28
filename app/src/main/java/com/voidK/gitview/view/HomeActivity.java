package com.voidK.gitview.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.voidK.gitview.R;
import com.voidK.gitview.adapter.GitRepoRcAdapter;
import com.voidK.gitview.databinding.ActivityHomeBinding;
import com.voidK.gitview.models.GitQueryRepoModel.GitQueryRepoItem;
import com.voidK.gitview.utils.mSharedPreferences;
import com.voidK.gitview.viewmodels.HomeActivityViewModel;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;

    @Inject
    mSharedPreferences preferences;
    private final String TAG = getClass().getName() + "ANIK";
    HomeActivityViewModel viewModel;
    GitRepoRcAdapter gitRepoRcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        viewModel = new ViewModelProvider(this).get(HomeActivityViewModel.class);
       /* viewModel.getLiveData().observe(this, new Observer<List<GitQueryRepoItem>>() {
            @Override
            public void onChanged(List<GitQueryRepoItem> gitQueryRepos) {
                if(gitQueryRepos != null) {
                    Toast.makeText(HomeActivity.this, "Success response", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "on changed first item: " + gitQueryRepos.get(0).getName() + " -- " + gitQueryRepos.get(0).getUrl());
                } else {
                    Toast.makeText(HomeActivity.this, "ERROR response", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        initRecyclerView();

        String access_token = "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"; // demo access token
        preferences.setToken(access_token);
        final int[] page = {1};
        // Query data
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("q", "Android");
        queryParams.put("sort", "stars"); // Can be one of: stars, forks, help-wanted-issues, updated
        queryParams.put("order", "desc"); //Default: desc -- Can be one of: desc, asc
        queryParams.put("per_page", 10); // Default: 30
        queryParams.put("page", page[0]); // Default: 1

        viewModel.getAllQueryRepoList().observe(this, new Observer<List<GitQueryRepoItem>>() {
            @Override
            public void onChanged(List<GitQueryRepoItem> gitQueryRepoItems) {
                if (gitQueryRepoItems != null) {
                    Toast.makeText(HomeActivity.this, "Success response", Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "count view: " + gitQueryRepoItems.size());
                    if(gitQueryRepoItems.size()>0) {
                        gitRepoRcAdapter.setItems(gitQueryRepoItems);
                        Log.d(TAG, "on changed first item: " + gitQueryRepoItems.get(0).getName() + " -- " + gitQueryRepoItems.get(0).getOwner().getAvatar_url());
                    }
                } else {
                    Toast.makeText(HomeActivity.this, "ERROR response", Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewModel.repoQueryAPICall(HomeActivity.this, queryParams);

    }

    private void initRecyclerView() {
        gitRepoRcAdapter = new GitRepoRcAdapter();
        binding.repoRecycler.setLayoutManager(new LinearLayoutManager(this));
        binding.repoRecycler.setAdapter(gitRepoRcAdapter);
    }
}