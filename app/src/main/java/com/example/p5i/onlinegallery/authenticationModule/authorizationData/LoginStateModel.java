package com.example.p5i.onlinegallery.authenticationModule.authorizationData;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class LoginStateModel
{
    private static final String TAG = "LoginStateModel";
    private Context context;
    private SharedPreferences loginPref;
    private SharedPreferences.Editor editor;
    private String defaultValueString="";
    private boolean defaultValueBoolean=false;
    public LoginStateModel(Context context) {
        this.context = context;
        loginPref=context.getSharedPreferences(this.getClass().getName(),Context.MODE_PRIVATE);
        editor=loginPref.edit();

    }
    public void saveEmail(String Email)
    {
        editor.putString("Email",Email);
        editor.apply();

    }
    public String retriveEmail()
    {
       return loginPref.getString("Email",defaultValueString) ;
    }
    public void savePassword(String Password)
    {
        editor.putString("Password",Password);
        editor.apply();

    }
    public String retrivePassword()
    {
        return loginPref.getString("Password",defaultValueString) ;
    }
    public void saveTocken(String Tocken)
    {
        editor.putString("Tocken",Tocken);
        editor.apply();
    }
    public String retriveTockenl()
    {
        Log.d(TAG, "retriveTockenl: "+loginPref.getString("Tocken",defaultValueString));
        return loginPref.getString("Tocken",defaultValueString) ;
    }
    public void saveFabState(boolean FabState)
    {
        editor.putBoolean("FabState",FabState);
        editor.apply();
    }
    public boolean retriveFabState()
    {
        return loginPref.getBoolean("FabState",defaultValueBoolean) ;
    }

}
