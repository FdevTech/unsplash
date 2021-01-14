package com.example.p5i.onlinegallery.authenticationModule;

import android.net.Uri;

import androidx.lifecycle.MutableLiveData;

public interface AuthenticationRepositioryContractor
{
    void creatingUser(Uri mUri, String Email, String Password);
    void signInInToTheApp(String Email, String Password);
    MutableLiveData<Integer> getUserLoggedIncheckobserver();
    String getEmail();
    String getPassword();
    void setEmail(String email);
    void setPassword(String password);
}
