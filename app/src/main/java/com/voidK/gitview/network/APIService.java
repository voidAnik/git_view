package com.voidK.gitview.network;

import com.voidK.gitview.models.gitqueryrepo.GitQueryRepo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface APIService {

    @GET("/search/repositories")
    Call<GitQueryRepo> searchGitRepo(@Query("q") String query);
}
