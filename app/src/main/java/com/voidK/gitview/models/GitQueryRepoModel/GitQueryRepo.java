
package com.voidK.gitview.models.GitQueryRepoModel;

import java.util.List;

public class GitQueryRepo {


    private List<GitQueryRepoItem> items;

    public GitQueryRepo() {
    }

    public GitQueryRepo(List<GitQueryRepoItem> items) {
        this.items = items;
    }

    public List<GitQueryRepoItem> getItems() {
        return items;
    }

    public void setItems(List<GitQueryRepoItem> items) {
        this.items = items;
    }
}
