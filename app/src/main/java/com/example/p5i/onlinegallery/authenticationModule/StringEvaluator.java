package com.example.p5i.onlinegallery.authenticationModule;

import android.animation.TypeEvaluator;
import android.util.Log;

public class StringEvaluator implements TypeEvaluator
{
    private static final String TAG = "StringEvaluator";

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        int startValueLength=((String)startValue).length();
        int sendValueLength=((String)endValue).length();
        Log.d(TAG, "evaluate: "+endValue);
       // float startFloat = ((Number) startValue).floatValue();
        return endValue;
    }
}
