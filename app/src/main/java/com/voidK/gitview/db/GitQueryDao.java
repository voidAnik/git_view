package com.voidK.gitview.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.voidK.gitview.models.GitQueryRepoModel.GitQueryRepoItem;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface GitQueryDao {

    @Query("SELECT * FROM query_repositories")
    LiveData<List<GitQueryRepoItem>>getAllRepo();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecords(List<GitQueryRepoItem> gitQueryRepoItems);

    @Query("DELETE FROM query_repositories")
    void deleteAllRecords();

    @Query("DELETE FROM query_repositories WHERE id = :id")
    void deleteRepository(int id);
}
