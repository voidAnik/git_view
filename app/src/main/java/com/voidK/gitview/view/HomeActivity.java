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

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    private String TAG = getClass().getName() + "ANIK";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        HomeActivityViewModel viewModel = new ViewModelProvider(this).get(HomeActivityViewModel.class);
        viewModel.getLiveData().observe(this, new Observer<List<GitQueryRepo>>() {
            @Override
            public void onChanged(List<GitQueryRepo> gitQueryRepos) {
                Toast.makeText(HomeActivity.this, "Success response", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "on changed total count: " + gitQueryRepos.get(0).getTotalCount());
            }
        });
        viewModel.repoQueryAPICall("Android");
    }
}