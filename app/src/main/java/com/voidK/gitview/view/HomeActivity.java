package com.voidK.gitview.view;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.voidK.gitview.CustomSpinner;
import com.voidK.gitview.R;
import com.voidK.gitview.adapter.GitRepoRcAdapter;
import com.voidK.gitview.adapter.SortSpinnerAdapter;
import com.voidK.gitview.databinding.ActivityHomeBinding;
import com.voidK.gitview.models.GitQueryRepoModel.GitQueryRepo;
import com.voidK.gitview.models.GitQueryRepoModel.GitQueryRepoItem;
import com.voidK.gitview.models.SortItems;
import com.voidK.gitview.utils.mSharedPreferences;
import com.voidK.gitview.viewmodels.HomeActivityViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    List<GitQueryRepoItem> repositories = new ArrayList<>();
    boolean mIsLoading, mIsLastPage;
    int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        viewModel = new ViewModelProvider(this).get(HomeActivityViewModel.class);

        viewModel.getLiveData().observe(this, new Observer<GitQueryRepo>() {
            @Override
            public void onChanged(GitQueryRepo repos) {
                if(repos != null) {
                    //Toast.makeText(HomeActivity.this, "Success response", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "on changed retro: " + repos.getItems().get(0).getName() + " -- " + repos.getTotal_count());
                    binding.progressBar.setVisibility(View.GONE);
                    mIsLastPage = currentPage == repos.getTotal_count();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mIsLoading = false;
                        }
                    },2000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mIsLoading = false;
                        }
                    },2000);
                    currentPage = currentPage - 1; // decreasing currentPage number to load that page again if it fails
                    binding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(HomeActivity.this, "ERROR response", Toast.LENGTH_SHORT).show();
                }
            }
        });

        initSortSpinner();

        viewModel.getAllQueryRepoList().observe(this, new Observer<List<GitQueryRepoItem>>() {
            @Override
            public void onChanged(List<GitQueryRepoItem> gitQueryRepoItems) {
                if (gitQueryRepoItems != null) {
                    //Toast.makeText(HomeActivity.this, "Success response", Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "count view room: " + gitQueryRepoItems.size());
                    if (gitQueryRepoItems.size() > 0) {
                        repositories.clear();
                        repositories.addAll(gitQueryRepoItems);
                        currentPage = repositories.size()/10;
                        setListItems(gitQueryRepoItems);

                        Log.d(TAG, "on changed first item: " + gitQueryRepoItems.get(0).getName() + " -- " + gitQueryRepoItems.get(0).getOwner().getAvatar_url());
                    }
                } else {
                    Toast.makeText(HomeActivity.this, "ERROR response", Toast.LENGTH_SHORT).show();
                }
            }
        });

        initRecyclerView();
        fetchData();

    }


    private void fetchData() {
        String access_token = "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"; // demo access token
        preferences.setToken(access_token);
        currentPage = currentPage + 1;
        // Query data
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("q", "Android");
        queryParams.put("sort", "updated"); // Can be one of: stars, forks, help-wanted-issues, updated
        queryParams.put("order", "asc"); //Default: desc -- Can be one of: desc, asc
        queryParams.put("per_page", 10); // Default: 30
        queryParams.put("page", currentPage); // Default: 1

        Log.i(TAG, "current page calling: "+ currentPage);
        viewModel.repoQueryAPICall(HomeActivity.this, queryParams);
    }

    private void setListItems(List<GitQueryRepoItem> items) {
        gitRepoRcAdapter.setItems(new GitRepoRcAdapter.ItemListener() {
            @Override
            public void onClick(int position) {
                Log.e(TAG, "selected position: " + position);
                Intent intent = new Intent(HomeActivity.this, RepoDetailsActivity.class);
                intent.putExtra("item", items.get(position));
                startActivity(intent);
            }
        }, items);
    }

    private void initSortSpinner() {
        //Creating the ArrayAdapter instance having the country list
        SortSpinnerAdapter spinnerAdapter = new SortSpinnerAdapter(this, SortItems.getSortText(), SortItems.getImage());

        //Setting the ArrayAdapter data on the Spinner
        binding.spinner.setAdapter(spinnerAdapter);
        binding.spinner.setSpinnerEventsListener(new CustomSpinner.OnSpinnerEventsListener() {
            @Override
            public void onPopupWindowOpened(Spinner spinner) {
                binding.spinner.setBackground(ContextCompat.getDrawable(HomeActivity.this, R.drawable.sort_spinner_outline_up));
            }

            @Override
            public void onPopupWindowClosed(Spinner spinner) {
                binding.spinner.setBackground(ContextCompat.getDrawable(HomeActivity.this, R.drawable.sort_spinner_outline));
            }
        });
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(HomeActivity.this, "selected " + i, Toast.LENGTH_SHORT).show();
                if (i == 0) {
                    repositories = viewModel.sortByStars(repositories);
                    setListItems(repositories);
                } else {
                    repositories = viewModel.sortByUpdated(repositories);
                    setListItems(repositories);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        gitRepoRcAdapter = new GitRepoRcAdapter();
        binding.repoRecycler.setLayoutManager(layoutManager);
        binding.repoRecycler.setAdapter(gitRepoRcAdapter);

        // initialise loading state
        mIsLoading = false;
        mIsLastPage = false;

        // amount of items you want to load per page
        final int pageSize = 10;

        // set up scroll listener
        binding.repoRecycler.addOnScrollListener(new  RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // number of visible items
                int visibleItemCount = layoutManager.getChildCount();
                // number of items in layout
                int totalItemCount = layoutManager.getItemCount();
                // the position of first visible item
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                //Log.i(TAG, "visibleItemCount: "+visibleItemCount+" totalItem: "+totalItemCount+" firstVisible: "+firstVisibleItemPosition);

                boolean isNotLoadingAndNotLastPage = !mIsLoading && !mIsLastPage;
                // flag if number of visible items is at the last
                boolean isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount;
                // validate non negative values
                boolean isValidFirstItem = firstVisibleItemPosition >= 0;
                // validate total items are more than possible visible items
                boolean totalIsMoreThanVisible = totalItemCount >= pageSize;
                // flag to know whether to load more
                boolean shouldLoadMore = isValidFirstItem && isAtLastItem && totalIsMoreThanVisible && isNotLoadingAndNotLastPage;

                //Log.e(TAG, "should load more "+shouldLoadMore);

                if (shouldLoadMore){
                    Log.e(TAG, "Load more");
                    Log.e(TAG, "is Loading "+mIsLoading);
                    mIsLoading = true;
                    binding.progressBar.setVisibility(View.VISIBLE);
                    fetchData();
                }
            }
        });
    }
}