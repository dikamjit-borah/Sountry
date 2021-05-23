package com.hobarb.sountry.utilities;

import java.util.ArrayList;
import java.util.Arrays;

public class constants {


    public static String BASE_URL = "";

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
    public static ArrayList<String> USER_PREFERRED_GENDERS = new ArrayList<>();

    public static String VIDEO_URL_KEY = "video_url";
    public static String VIDEO_GENRES_KEY = "video_genres";


    public static String GENRES_KEY = "user_preferred_genres";
    public static String GENDERS_KEY = "user_preferred_genders";

    public static String USER_DETAILS_KEY = "user_details";
    public static String USER_PREFERENCES_KEY = "user_preferences";

    public static class UserDetails{

        public static String FULL_NAME_KEY = "full_name";
        public static String USER_NAME_KEY = "user_name";
        public static String PASSWORD_KEY = "password";
        public static String PHONE_KEY = "phone";
        public static String EMAIL_KEY = "email";

        public static String FULL_NAME = "";
        public static String USER_NAME = "";
        public static String PASSWORD = "";
        public static String PHONE = "";
        public static String EMAIL = "";

    }


}
