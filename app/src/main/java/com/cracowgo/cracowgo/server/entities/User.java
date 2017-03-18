package com.cracowgo.cracowgo.server.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohru on 18.03.2017.
 */

public class User {

    int id;
    private String email;
    private String password;
    @SerializedName("password_confirmation")
    private String passwordConfirmation;
    @SerializedName("year_of_birth")
    private String birthDate;
    private String nationality;


    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.passwordConfirmation = password;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
