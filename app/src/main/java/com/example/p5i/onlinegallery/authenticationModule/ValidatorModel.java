package com.example.p5i.onlinegallery.authenticationModule;

import android.util.Log;

import java.util.regex.Pattern;

import androidx.lifecycle.MutableLiveData;

public class ValidatorModel
{


    private static final String TAG = "ValidatorModel";
    Pattern emailPattern,passworPattern;
    MutableLiveData<Boolean> emailFieldEmptyMutableLiveData,emailValidMutableLiveData,passwordFieldEmpty, passwordFieldTooWeek,
                        everythingIsValidatedLiveData;
    boolean emailValidate,passwordValidate;
    public ValidatorModel()
    {
        emailFieldEmptyMutableLiveData=new MutableLiveData<>();
        emailFieldEmptyMutableLiveData.setValue(true);
        emailValidMutableLiveData=new MutableLiveData<>();
        emailValidMutableLiveData.setValue(false);
        passwordFieldEmpty=new MutableLiveData<>();
        passwordFieldEmpty.setValue(true);
        passwordFieldTooWeek =new MutableLiveData<>();
        everythingIsValidatedLiveData=new MutableLiveData<>();
        everythingIsValidatedLiveData.setValue(false);
        passwordFieldTooWeek.setValue(false);

        emailValidate=false;
        passwordValidate=false;

        emailPattern=Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
       // passworPattern=Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
        passworPattern= Pattern.compile("^" +
                "(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$");
    }




    public void validate(String emailText,String passwordText)
    {
        validateEmail(emailText);
        validatePassword(passwordText);
        if(emailValidate&&passwordValidate)
        {
            everythingIsValidatedLiveData.setValue(true);
        }
    }
    private void validateEmail(String emailText)
    {

         if(emailText==null)
         {
             emailFieldEmptyMutableLiveData.setValue(true);
             return;
         }
         else
         {

             if(emailText.isEmpty())
             {
                 emailFieldEmptyMutableLiveData.setValue(true);
                 return;
             }
             else
             {
                 emailFieldEmptyMutableLiveData.setValue(false);

                 if(!emailPattern.matcher(emailText.trim()).matches())
                 {

                     emailValidMutableLiveData.setValue(false);


                 }
                 else
                 {
                     emailValidMutableLiveData.setValue(true);
                     emailValidate=true;
                 }
             }
         }
    }
    private void validatePassword(String passwordText)
    {
        
            if(passwordText==null)
            {
                passwordFieldEmpty.setValue(true);
                return;
            }
            else
            {
                if(passwordText==null|passwordText.isEmpty())
                {
                    passwordFieldEmpty.setValue(true);
                    return;
                }
                else
                {
                    passwordFieldEmpty.setValue(false);
                    Log.d(TAG, "validatePassword: "+passworPattern.matcher(passwordText.trim()).matches());
                    if(!passworPattern.matcher(passwordText).matches())
                    {
                        Log.d(TAG, "validatePassword: ");
                        passwordFieldTooWeek.setValue(true);
                    }
                    else
                    {
                        passwordFieldTooWeek.setValue(false);
                        passwordValidate=true;
                    }
                }
            }
    }

    public MutableLiveData<Boolean> getEmailFieldEmptyMutableLiveData() {
        return emailFieldEmptyMutableLiveData;
    }

    public MutableLiveData<Boolean> getEmailValidMutableLiveData() {
        return emailValidMutableLiveData;
    }

    public MutableLiveData<Boolean> getPasswordFieldEmpty() {
        return passwordFieldEmpty;
    }

    public MutableLiveData<Boolean> getPasswordFieldTooWeek() {
        return passwordFieldTooWeek;
    }

    public MutableLiveData<Boolean> getEverythingIsValidatedLiveData() {
        return everythingIsValidatedLiveData;
    }
}
