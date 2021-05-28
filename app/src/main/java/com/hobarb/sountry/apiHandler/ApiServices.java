package com.hobarb.sountry.apiHandler;

import com.google.gson.JsonObject;
import com.hobarb.sountry.models.NotificationsModel;
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
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServices {
    @GET("api/user/videos")
    Call<List<VideosModel>> getVideos();


    @GET("api/user/video")
    Call<List<String>> getVideoGenres(@Query("video_id") String video_id);



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

    @Headers("Content-Type: application/json")
    @POST("api/connect")
    Call<JsonObject> postSendRequest(@Query("connect_with_id") long connect_with_id, @Query("request_by_id") long request_by_id);

    @GET("api/connect/notifications")
    Call<List<NotificationsModel>> getNotifications(@Query("user_id") long user_id);

    @GET("api/connect/connections")
    Call<List<NotificationsModel>> getConnections(@Query("user_id") long user_id);


    @PUT("api/connect/update")
    Call<JsonObject> updateConnection(@Query("connect_with_id") long connect_with_id, @Query("request_by_id") long request_by_id, @Query("is_connected") int is_connected);

}
