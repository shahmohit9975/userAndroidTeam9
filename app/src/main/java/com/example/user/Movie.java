package com.example.user;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {

    @SerializedName("name")
    private String name;

    @SerializedName("url")
    private Url url;

    @SerializedName("timestamp")
    private String timestamp;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setUrl(Url url){
        this.url = url;
    }

    public Url getUrl(){
        return url;
    }

    public void setTimestamp(String timestamp){
        this.timestamp = timestamp;
    }

    public String getTimestamp(){
        return timestamp;
    }

    @Override
    public String toString(){
        return
                "Movie{" +
                        "name = '" + name + '\'' +
                        ",url = '" + url + '\'' +
                        ",timestamp = '" + timestamp + '\'' +
                        "}";
    }
}
