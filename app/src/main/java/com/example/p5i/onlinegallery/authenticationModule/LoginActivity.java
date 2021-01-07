package com.example.p5i.onlinegallery.authenticationModule;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.p5i.onlinegallery.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private MaterialButton materialButton;
    private AnimatedVectorDrawable animatedVectorDrawable;
    private TextInputLayout textInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        materialButton=findViewById(R.id.materialButton);
        animatedVectorDrawable=(AnimatedVectorDrawable) materialButton.getIcon();
        textInputLayout=findViewById(R.id.textInputLayout);

        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animatedVectorDrawable.start();
                materialButton.setText("next");
                textInputLayout.setError("someting wen wrrong");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}