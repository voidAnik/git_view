package com.voidK.gitview.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.voidK.gitview.R;
import com.voidK.gitview.models.GitQueryRepoModel.GitQueryRepoItem;
import com.voidK.gitview.view.RepoDetailsActivity;

import java.util.List;
import java.util.StringTokenizer;

public class GitRepoRcAdapter extends RecyclerView.Adapter<GitRepoRcAdapter.mViewModel> {
    List<GitQueryRepoItem> repos;
    Context context;
    ItemListener itemListener;

    @SuppressLint("NotifyDataSetChanged")
    public void setItems(ItemListener itemListener, List<GitQueryRepoItem> repos) {
        this.itemListener = itemListener;
        this.repos = repos;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GitRepoRcAdapter.mViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.single_repo_view_layout, parent, false);

        return new mViewModel(context, view);
    }

    @Override
    public void onBindViewHolder(@NonNull GitRepoRcAdapter.mViewModel holder, int position) {
        holder.bind(repos.get(holder.getAbsoluteAdapterPosition()));
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.getAbsoluteAdapterPosition()>=0) {
                    itemListener.onClick(holder.getAbsoluteAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(repos==null) return 0;
        return repos.size();
    }

    public class mViewModel extends RecyclerView.ViewHolder{
        TextView repo_name_text, language_text, privacy_text, created_text, star_text;
        ImageView git_image;
        CardView card;
        public mViewModel(Context context, @NonNull View itemView) {
            super(itemView);
            repo_name_text = itemView.findViewById(R.id.repo_name_text);
            language_text = itemView.findViewById(R.id.language_text);
            privacy_text = itemView.findViewById(R.id.privacy_text);
            git_image = itemView.findViewById(R.id.git_image);
            created_text = itemView.findViewById(R.id.created_text);
            card = itemView.findViewById(R.id.card);
            star_text = itemView.findViewById(R.id.star_text);
        }
        @SuppressLint("SetTextI18n")
        void bind(GitQueryRepoItem repoItem){
            repo_name_text.setText(repoItem.getName());
            if(repoItem.getLanguage() != null) {
                language_text.setVisibility(View.VISIBLE);
                language_text.setText(repoItem.getLanguage());
            } else {
                language_text.setVisibility(View.GONE);
            }

            star_text.setText(repoItem.getStargazers_count().toString());
            privacy_text.setText(repoItem.getVisibility());

            String[] splitStr = repoItem.getUpdated_at().split("T");
            created_text.setText("Updated: " + splitStr[0]);

            Glide.with(git_image)
                    .load(repoItem.getOwner().getAvatar_url())
                    .placeholder(R.drawable.git_256)
                    .into(git_image);
        }
    }
    public interface ItemListener{
        public void onClick(int position);
    }
}

