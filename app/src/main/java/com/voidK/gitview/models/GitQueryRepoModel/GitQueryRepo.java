
package com.voidK.gitview.models.GitQueryRepoModel;

import java.util.List;

public class GitQueryRepo {

private int total_count;
    private List<GitQueryRepoItem> items;


    public GitQueryRepo(int total_count, List<GitQueryRepoItem> items) {
        this.total_count = total_count;
        this.items = items;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public List<GitQueryRepoItem> getItems() {
        return items;
    }

    public void setItems(List<GitQueryRepoItem> items) {
        this.items = items;
    }
}
