package com.hobarb.sountry.models;

import java.util.ArrayList;

import java.util.List;
public class UserModel {


    /**
     * user_details : {"full_name":"Himaaa","user_name":"kasushtabbs","password":"xyzqqq","gender":"Male","phone":"9765389222","email":"dikam@gmail.com","date_joined":"2017-03-14"}
     * user_preferences : {"preferred_genres":["Classical, Pop"],"preferred_gender":["Female"]}
     */
    private UserDetailsDTO user_details;
    private UserPreferencesDTO user_preferences;

    public UserModel(UserDetailsDTO user_detailsDTO, UserPreferencesDTO user_preferencesDTO){
        this.user_details = user_detailsDTO;
        this.user_preferences = user_preferencesDTO;
    }

    public static class UserDetailsDTO {
        private String full_name;
        private String user_name;
        private String password;
        private String gender;
        private String phone;
        private String email;
        private String date_joined;

        public UserDetailsDTO(String full_name, String user_name, String password, String gender, String phone, String email, String date_joined)
        {
            this.full_name = full_name;
            this.user_name = user_name;
            this.password = password;
            this.gender = gender;
            this.phone = phone;
            this.email = email;
            this.date_joined = date_joined;

        }
    }

    public static class UserPreferencesDTO {
        private List<String> preferred_genres;
        private List<String> preferred_gender;

        public UserPreferencesDTO(ArrayList<String> preferred_genres, ArrayList<String> preferred_gender){
            this.preferred_genres = preferred_genres;
            this.preferred_gender = preferred_gender;
        }
    }
}
