package com.example.githubexperience;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

//BASE URL :  https://api.github.com/users/JLOPEZR

public interface UserService {

    //Followers y Followings
    @GET("{user}")
    Call<Users> getInfo(@Path("user") String name);


    //Language
    @GET("{user}/repos")
    Call<List<Repositorios>> getLanguage(@Path("user") String name);


}