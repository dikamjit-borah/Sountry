package com.hobarb.sountry.models;

import java.util.Date;

public class MessageModel {
    public long userId;
    public String userName, message, datetime;


    public MessageModel(long userId, String message, String datetime) {
        this.userId = userId;
        this.message = message;
        this.datetime = datetime;
    }
}
