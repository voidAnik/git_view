package com.voidK.gitview.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.voidK.gitview.R;
import com.voidK.gitview.databinding.ActivityRepoDetailsBinding;
import com.voidK.gitview.models.GitQueryRepoModel.GitQueryRepoItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RepoDetailsActivity extends AppCompatActivity {

    private GitQueryRepoItem item;
    private ActivityRepoDetailsBinding binding;
    private String TAG = getClass().getName() + "ANIK";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_repo_details);

        item = (GitQueryRepoItem) getIntent().getSerializableExtra("item");
        Log.i(TAG, item.getGit_url());

        binding.navigateBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setViews();
    }

    @SuppressLint("SetTextI18n")
    private void setViews() {
        Glide.with(binding.avatarImage)
                .load(item.getOwner().getAvatar_url())
                .placeholder(R.drawable.git_256)
                .into(binding.avatarImage);
        binding.nameText.setText(item.getOwner().getLogin());
        binding.descText.setText(item.getDescription());

        SpannableString content1 = new SpannableString(item.getFull_name());
        content1.setSpan(new UnderlineSpan(), 0, content1.length(), 0);
        binding.fullNameText.setText(content1);
        binding.fullNameText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent urlIntent = new Intent(Intent.ACTION_VIEW);
                urlIntent.setData(Uri.parse(item.getOwner().getHtml_url()));
                startActivity(urlIntent);
            }
        });

        SpannableString content2 = new SpannableString(item.getHtml_url());
        content2.setSpan(new UnderlineSpan(), 0, content2.length(), 0);
        binding.urlText.setText(content2);

        binding.urlText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent urlIntent = new Intent(Intent.ACTION_VIEW);
                urlIntent.setData(Uri.parse(item.getHtml_url()));
                startActivity(urlIntent);
            }
        });
        try {
            String createdDate = getConvertedDateTime(item.getCreated_at());
            String updatedDate = getConvertedDateTime(item.getUpdated_at());
            String pushedDate = getConvertedDateTime(item.getPushed_at());
            binding.createdAtText.setText("Created: "+ createdDate);
            binding.updatedAtText.setText("Updated: "+ updatedDate);
            binding.pushedAtText.setText("Pushed: "+ pushedDate);
        } catch (Exception e) {
            binding.createdAtText.setText("00-00-00");
            binding.updatedAtText.setText("00-00-00");
            e.printStackTrace();
        }
        binding.starsText.setText(item.getStargazers_count().toString() + " " + "stars");
        binding.watchText.setText(item.getWatchers_count().toString() + " " + "watching");
        binding.forkText.setText(item.getForks_count().toString() + " " + "forks");
    }

    private String getConvertedDateTime(String created_at) throws Exception {
        String[] splitString = created_at.split("T");
        String oldDateString = splitString[0];
        String oldTimeString = splitString[1].replace("Z", "");

        final String OLD_FORMAT = "yyyy-MM-dd";
        final String NEW_FORMAT = "MM-dd-yyyy";

        String newDateString;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(OLD_FORMAT);
        Date d = simpleDateFormat.parse(oldDateString);
        simpleDateFormat.applyPattern(NEW_FORMAT);
        newDateString = simpleDateFormat.format(d);

        return newDateString + " " + oldTimeString;
    }
}