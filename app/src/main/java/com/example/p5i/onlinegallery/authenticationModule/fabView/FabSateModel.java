package com.example.p5i.onlinegallery.authenticationModule.fabView;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class FabSateModel
{
    private static final String TAG = "FabSateModel";

    private SharedPreferences loginPref;
    private SharedPreferences.Editor editor;


    private static String fabEnabledKey="FabenableddState",fabdExtendedKey="FabEtendState";
    public FabSateModel(Context context)
    {
        loginPref=context.getSharedPreferences(this.getClass().getName(), Context.MODE_PRIVATE);
        editor=loginPref.edit();
    }

    public void setFabExtendState(boolean extendState)
    {
        editor.putBoolean(fabdExtendedKey,extendState);
        editor.apply();
    }
    public void setFabenableddState(boolean enableddState)
    {
        Log.d(TAG, "setFabenableddState: "+enableddState);
        editor.putBoolean(fabEnabledKey,enableddState);
        editor.apply();
    }
    public boolean retriveFabenableddState()
    {
        boolean enabledStateDefautValue=false;
        Log.d(TAG, "retriveFabenableddState: "+loginPref.getBoolean(fabEnabledKey,enabledStateDefautValue) );
        return loginPref.getBoolean(fabEnabledKey,enabledStateDefautValue) ;
    }
    public boolean retriveFabExtendState()
    {
        boolean extendStateFabDefaultValue=true;
        return loginPref.getBoolean(fabdExtendedKey,extendStateFabDefaultValue) ;
    }
}
