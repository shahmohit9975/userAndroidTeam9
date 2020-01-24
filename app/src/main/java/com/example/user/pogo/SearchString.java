package com.example.user.pogo;

import androidx.annotation.NonNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SearchString {
    private String searchString;
    public SearchString(String searchString){
        this.searchString=searchString;
    }
    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
