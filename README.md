# Retrofit入门

## Retrofit简介
retrofit是由square公司开发的。square在github上发布了很多优秀的Android开源项目。例如:otto(事件总线),leakcanary(排查内存泄露),android-times-square(日历控件),dagger(依赖注入),picasso(异步加载图片),okhttp(网络请求),retrofit(网络请求)等等。更多square上的开源项目我们可以去square的GitHub进行查看。这次就来介绍一下retrofit的一些基本用法。retrofit是REST安卓客户端请求库。使用retrofit可以进行GET，POST，PUT，DELETE等请求方式。下面就来看一下retrofit的基本用法。
[官网](http://square.github.io/retrofit/)

## Retrofit简单使用
### 添加依赖
app/build.gradle

```
compile 'com.squareup.retrofit2:retrofit:2.3.0' //添加retrofit
compile 'com.squareup.retrofit2:converter-gson:2.1.0'//添加JSON解析
```
当前版本为2.3.0，如果想使用最新版本请[查看最新版本](http://square.github.io/retrofit/)
### 创建API接口
在retrofit中通过一个Java接口作为http请求的api接口

```java
public interface GitHubApi {
    @GET("repos/square/retrofit/contributors")
    Call<List<ResponseBody>> contributorsBySimpleGetCall();
}
```
### 创建retrofit实例
在这里baseUrl是在创建retrofit实力的时候定义的，我们也可以在API接口中定义完整的url。在这里建议在创建baseUrl中以”/”结尾，在API中不以”/”开头和结尾。

```java
Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.github.com/")
                .build();
```
### 接口调用
在调用API接口请求后，获得一个ResponseBody的List，可直接通过response.body()获得List

```java
GitHubApi repo = retrofit.create(GitHubApi.class);
        Call<List<ResponseBody>> call = repo.contributorsBySimpleGetCall();
        call.enqueue(new Callback<List<ResponseBody>>() {
            @Override
            public void onResponse(Call<List<ResponseBody>> call, Response<List<ResponseBody>> response) {
                Toast.makeText(MainActivity.this,"请求成功",Toast.LENGTH_SHORT).show();
                Log.i("onResponse", response.body().toString());

            }

            @Override
            public void onFailure(Call<List<ResponseBody>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"onFailure",Toast.LENGTH_SHORT).show();
                Log.i("Failure", t.toString());

            }
        });
```
### 效果图
![效果图](http://upload-images.jianshu.io/upload_images/267127-c1e74591e299bd96.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
### 取消请求
我们可以终止一个请求。终止操作是对底层的httpclient执行cancel操作。即使是正在执行的请求，也能够立即终止。
```java
call.cancel();
```
[源码:https://github.com/weihansheng/RetrofitTest](https://github.com/weihansheng/RetrofitTest)
[参考](http://blog.csdn.net/ljd2038/article/details/51046512)