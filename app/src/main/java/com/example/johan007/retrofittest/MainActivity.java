package com.example.johan007.retrofittest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn= (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

    }
    public void getData(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.github.com/")
                .build();

        GitHubApi repo = retrofit.create(GitHubApi.class);
        Call<List<ResponseBody>> call = repo.contributorsBySimpleGetCall();
        call.enqueue(new Callback<List<ResponseBody>>() {
            @Override
            public void onResponse(Call<List<ResponseBody>> call, Response<List<ResponseBody>> response) {
                Toast.makeText(MainActivity.this,"请求成功",Toast.LENGTH_SHORT).show();
                Log.i("onResponse", "共获取contributors数："+response.body().size());

            }

            @Override
            public void onFailure(Call<List<ResponseBody>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"onFailure",Toast.LENGTH_SHORT).show();
                Log.i("Failure", t.toString());

            }
        });
    }
}
