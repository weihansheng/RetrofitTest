package com.example.johan007.retrofittest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.github.com/")
                .build();
        GitHubApi repo = retrofit.create(GitHubApi.class);

        Call<List<ResponseBody>> call = repo.contributorsBySimpleGetCall();
        call.enqueue(new Callback<List<ResponseBody>>() {
            @Override
            public void onResponse(Call<List<ResponseBody>> call, Response<List<ResponseBody>> response) {
                Toast.makeText(MainActivity.this,"onResponse",Toast.LENGTH_SHORT).show();
                Log.i("onResponse", response.toString());

                    /*Gson gson = new Gson();
                    ArrayList<ResponseBody> contributorsList = gson.fromJson(response.toString(), new TypeToken<List<ResponseBody>>(){}.getType());
                    for (ResponseBody contributor : contributorsList){
                        Log.d("login",contributor.getLogin());
                        Log.d("contributions",contributor.getContributions()+"");
                    }*/

            }

            @Override
            public void onFailure(Call<List<ResponseBody>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"onFailure",Toast.LENGTH_SHORT).show();
                Log.i("Failure", t.toString());

            }
        });
    }
}
