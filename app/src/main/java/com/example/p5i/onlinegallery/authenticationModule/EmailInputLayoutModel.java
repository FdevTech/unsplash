package com.example.p5i.onlinegallery.authenticationModule;

import androidx.lifecycle.MutableLiveData;

public class EmailInputLayoutModel
{
    private static final String TAG = "EmailInputLayoutModel";
    private MutableLiveData<String> emtyErrorMessage,validateErrorMessage;

    public EmailInputLayoutModel()
    {
        emtyErrorMessage=new MutableLiveData<>();
        validateErrorMessage=new MutableLiveData<>();
    }

    public void isEmpty(boolean emty)
    {
        if(emty)
        {

        }
    }
}
