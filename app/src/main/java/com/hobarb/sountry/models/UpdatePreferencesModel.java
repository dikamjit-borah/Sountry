package com.hobarb.sountry.models;

import java.util.ArrayList;
import java.util.List;

public class UpdatePreferencesModel {
    private String id;
        private List<String> preferred_genres;
        private List<String> preferred_gender;

        public UpdatePreferencesModel(String id, ArrayList<String> preferred_genres, ArrayList<String> preferred_gender){
            this.id = id;
            this.preferred_genres = preferred_genres;
            this.preferred_gender = preferred_gender;

    }
}
