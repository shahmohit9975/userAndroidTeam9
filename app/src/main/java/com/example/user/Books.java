package com.example.user;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Books implements Serializable {
    @SerializedName("name")
    private String name;
    @SerializedName("url")
    private String url;



    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return url;
    }
    @Override
    public String toString(){
        return
                "Books{" +
                        ",name = '" + name + '\'' +
                        ",url = '" + url + '\'' +
                        "}";
    }
}