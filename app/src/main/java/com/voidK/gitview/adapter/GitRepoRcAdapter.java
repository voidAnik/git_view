package com.voidK.gitview.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.voidK.gitview.R;
import com.voidK.gitview.models.GitQueryRepoModel.GitQueryRepoItem;

import java.util.List;

public class GitRepoRcAdapter extends RecyclerView.Adapter<GitRepoRcAdapter.mViewModel> {
    List<GitQueryRepoItem> repos;
    Context context;

    @SuppressLint("NotifyDataSetChanged")
    public void setItems(List<GitQueryRepoItem> repos) {
        this.repos = repos;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GitRepoRcAdapter.mViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.single_repo_view_layout, parent, false);

        return new mViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GitRepoRcAdapter.mViewModel holder, int position) {
        holder.bind(repos.get(position));
    }

    @Override
    public int getItemCount() {
        if(repos==null) return 0;
        return repos.size();
    }

    public static class mViewModel extends RecyclerView.ViewHolder{
        TextView repo_name_text, language_text, privacy_text;
        ImageView git_image;
        public mViewModel(@NonNull View itemView) {
            super(itemView);
            repo_name_text = itemView.findViewById(R.id.repo_name_text);
            language_text = itemView.findViewById(R.id.language_text);
            privacy_text = itemView.findViewById(R.id.privacy_text);
            git_image = itemView.findViewById(R.id.git_image);
        }
        void bind(GitQueryRepoItem repoItem){
            repo_name_text.setText(repoItem.getName());
            language_text.setText(repoItem.getLanguage());
            privacy_text.setText(repoItem.getVisibility());

            Glide.with(git_image)
                    .load(repoItem.getOwner().getAvatar_url())
                    .placeholder(R.mipmap.ic_launcher)
                    .into(git_image);
        }
    }
}
