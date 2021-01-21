package com.example.p5i.onlinegallery.authenticationModule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Fade;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.p5i.onlinegallery.R;
import com.example.p5i.onlinegallery.authenticationModule.authorizationData.AutorizationInterface;
import com.example.p5i.onlinegallery.authenticationModule.authorizationData.AutorizationResponsePJO;
import com.example.p5i.onlinegallery.databinding.ActivityLoginBinding;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private LoginViewModel mLoginViewModel;
    private ActivityLoginBinding mActivityLoginBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        getWindow().setEnterTransition(new ChangeBounds());
        getWindow().setExitTransition(new ChangeBounds());
        super.onCreate(savedInstanceState);
        mActivityLoginBinding=ActivityLoginBinding.inflate(getLayoutInflater());
        mLoginViewModel=new LoginViewModel(this);
        mActivityLoginBinding.setLoginBinding(mLoginViewModel);
        setContentView(mActivityLoginBinding.getRoot());



    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Uri mUri=getIntent().getData();
        if(mUri!=null)
        {
            mLoginViewModel.getTheTocken(mUri);

        }

    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
       // Log.d(TAG, "onKeyUp: "+keyCode);
        switch (keyCode)
        {
            case KeyEvent.KEYCODE_ENTER:
                mLoginViewModel.validate();
                return true;

        }
        return super.onKeyUp(keyCode, event);
        
    }
}