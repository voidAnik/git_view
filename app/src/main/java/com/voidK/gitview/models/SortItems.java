package com.voidK.gitview.models;

import com.voidK.gitview.R;

import java.util.ArrayList;

public class SortItems {
    public static String[] sortText= {"Stars", "Updated"};
    public static Integer[] image = {R.drawable.ic_baseline_star_24, R.drawable.ic_baseline_access_time_filled_24};

    public static String[] getSortText() {
        return sortText;
    }

    public static Integer[] getImage() {
        return image;
    }
}
