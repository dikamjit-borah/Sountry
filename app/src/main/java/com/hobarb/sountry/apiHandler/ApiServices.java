package com.hobarb.sountry.apiHandler;

import com.google.gson.JsonObject;
import com.hobarb.sountry.models.ProfileModel;
import com.hobarb.sountry.models.UploadVideosModel;
import com.hobarb.sountry.models.UserModel;
import com.hobarb.sountry.models.VideosModel;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServices {
    @GET("api/user/videos")
    Call<List<VideosModel>> getVideos();

    @GET("api/user/{id}")
    Call<List<ProfileModel>> getUserProfile(@Path("id") long user_id);

    @Headers("Content-Type: application/json")
    @POST("api/signup")
    Call<JsonObject> postNewUser(@Body UserModel user);

    @Headers("Content-Type: application/json")
    @POST("api/signin")
    Call<JsonObject> postSignIn(@Body JsonObject credentials);


    @Headers("Content-Type: application/json")
    @POST("api/user/upload")
    Call<JsonObject> postUploadVideo(@Body UploadVideosModel uploadVideosModel);


}
