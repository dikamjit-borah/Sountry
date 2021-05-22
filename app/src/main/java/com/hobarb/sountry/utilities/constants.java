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
    public static ArrayList<String> USER_PREFERRED_GENDERS = new ArrayList<>();

    public static String GENRES_KEY = "user_preferred_genres";
    public static String GENDERS_KEY = "user_preferred_genders";


}
