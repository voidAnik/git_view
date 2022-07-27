package com.voidK.gitview.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.voidK.gitview.R;
import com.voidK.gitview.databinding.ActivityHomeBinding;
import com.voidK.gitview.models.gitqueryrepo.GitQueryRepo;
import com.voidK.gitview.viewmodels.HomeActivityViewModel;

import java.util.HashMap;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.http.Query;

@AndroidEntryPoint
public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    private final String TAG = getClass().getName() + "ANIK";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        HomeActivityViewModel viewModel = new ViewModelProvider(this).get(HomeActivityViewModel.class);
        viewModel.getLiveData().observe(this, new Observer<GitQueryRepo>() {
            @Override
            public void onChanged(GitQueryRepo gitQueryRepos) {
                Toast.makeText(HomeActivity.this, "Success response", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "on changed first item: " + gitQueryRepos.getItems().get(0).getName() +" -- "+ gitQueryRepos.getItems().get(0).getUrl());
            }
        });

        // Query data
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("q", "Android");
        queryParams.put("sort", "stars"); // Can be one of: stars, forks, help-wanted-issues, updated
        queryParams.put("order", "desc"); //Default: desc -- Can be one of: desc, asc
        queryParams.put("per_page", 20); // Default: 30
        queryParams.put("page", 1); // Default: 1

        viewModel.repoQueryAPICall(HomeActivity.this, queryParams);
    }
}