package com.hobarb.sountry.models;

import java.util.List;

public class ForumPostModel {


    private Integer status;
    private List<DataDTO> data;

    public Integer getStatus() {
        return status;
    }

    public List<DataDTO> getData() {
        return data;
    }

    public static class DataDTO {
        private String post_id;
        private String user_id;
        private String user_name;
        private String post_requirement;
        private String post_description;
        private String user_phone;

        public String getPostId() {
            return post_id;
        }

        public String getUserId() {
            return user_id;
        }

        public String getUserName() {
            return user_name;
        }

        public String getPostRequirement() {
            return post_requirement;
        }

        public String getPostDescription() {
            return post_description;
        }

        public String getUserPhone() {
            return user_phone;
        }
    }
}
