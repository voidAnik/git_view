package com.voidK.gitview.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    String[] sortItems = {"stars", "updated"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
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
        initSortSpinner();

        String access_token = "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"; // demo access token
        preferences.setToken(access_token);
        final int[] page = {1};
        // Query data
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("q", "Android");
        queryParams.put("sort", "stars"); // Can be one of: stars, forks, help-wanted-issues, updated
        queryParams.put("order", "asc"); //Default: desc -- Can be one of: desc, asc
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

    private void initSortSpinner() {
        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,sortItems);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        binding.spinner.setAdapter(aa);
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i(TAG, "sort selected: "+ i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.i(TAG, "sort selected: nothing");
            }
        });
    }

    private void initRecyclerView() {
        gitRepoRcAdapter = new GitRepoRcAdapter();
        binding.repoRecycler.setLayoutManager(new LinearLayoutManager(this));
        binding.repoRecycler.setAdapter(gitRepoRcAdapter);
    }
}