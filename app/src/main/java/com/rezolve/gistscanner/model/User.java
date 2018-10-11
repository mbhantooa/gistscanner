package com.rezolve.gistscanner.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User extends BaseObservable {
    @SerializedName("login")
    @Expose
    @Bindable
    private String login;

    public String getLogin() {
        return login;
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                '}';
    }
}
