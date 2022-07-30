package com.voidK.gitview.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.voidK.gitview.R;

import java.util.ArrayList;

public class SortSpinnerAdapter extends BaseAdapter {
    Context context;
    private final String[] sortText;
    private final Integer[] image;

    public SortSpinnerAdapter(Context context, String[] sortText, Integer[] image) {
        this.context = context;
        this.sortText = sortText;
        this.image = image;
    }

    @Override
    public int getCount() {
        return sortText.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        @SuppressLint("ViewHolder") View view = LayoutInflater.from(context).inflate(R.layout.sort_spinner_layout, viewGroup, false);

        ImageView imageView = view.findViewById(R.id.ivSort);
        TextView textView = view.findViewById(R.id.tvSort);

        imageView.setImageResource(image[i]);
        textView.setText(sortText[i]);

        return view;
    }
}
