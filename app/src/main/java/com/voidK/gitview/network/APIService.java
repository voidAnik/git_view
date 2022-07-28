package com.voidK.gitview.network;

import com.voidK.gitview.models.GitQueryRepoModel.GitQueryRepo;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;


public interface APIService {

    @GET
    Call<GitQueryRepo> searchGitRepo(/*@Header ("Authorization") String token,*/ @Url String url, @QueryMap HashMap<String, Object> params);
}
