package com.example.johan007.retrofittest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Johan007 on 2017/6/2.
 */

public interface GitHubApi {
    @GET("repos/square/retrofit/contributors")
    Call<List<ResponseBody>> contributorsBySimpleGetCall();
}
