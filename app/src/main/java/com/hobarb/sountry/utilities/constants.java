package com.hobarb.sountry.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class constants {




   // public static String BASE_URL = "http://sountry.herokuapp.com/";
    public static String BASE_URL = "http://192.168.29.207:3000/";
    public static String SHARED_PREFS_KEY = "SOUNTRY";
    public static String USER_ID_KEY = "USER_ID";
    public static String TOKEN_KEY = "TOKEN";


    public static ArrayList<String> genres = new ArrayList<>( Arrays.asList(
            "Classical",
            "Country",
            "EDM",
            "Hip-hop",
            "Indie rock",
            "Jazz",
            "K-pop",
            "Metal",
            "Oldies",
            "Pop",
            "Rap",
            "R&B",
            "Rock",
            "Techno"
    ));

    public static ArrayList<String> USER_PREFERRED_GENRES = new ArrayList<>();
    public static ArrayList<String> VIDEO_GENRES = new ArrayList<>();
    public static ArrayList<String> USER_PREFERRED_GENDER = new ArrayList<>();

    public static String VIDEO_URL_KEY = "video_url";
    public static String VIDEO_GENRES_KEY = "video_genres";


    public static String GENRES_KEY = "preferred_genres";
    public static String GENDERS_KEY = "preferred_gender";

    public static String USER_DETAILS_KEY = "user_details";
    public static String USER_PREFERENCES_KEY = "user_preferences";

    public static class UserDetails{

        public static String FULL_NAME_KEY = "full_name";
        public static String USER_NAME_KEY = "user_name";
        public static String GENDER_KEY = "gender";
        public static String PASSWORD_KEY = "password";
        public static String PHONE_KEY = "phone";
        public static String EMAIL_KEY = "email";
        public static String DATE_JOINED_KEY = "date_joined";

        public static String FULL_NAME = "";
        public static String USER_NAME = "";
        public static String PASSWORD = "";
        public static String PHONE = "";
        public static String EMAIL = "";
        public static String DATE_JOINED = "";
        public static String GENDER = "";
    }


}
