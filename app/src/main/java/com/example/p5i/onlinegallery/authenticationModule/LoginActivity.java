package com.example.p5i.onlinegallery.authenticationModule;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.view.View;

import com.example.p5i.onlinegallery.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    //private FloatingActionButton materialButton;
    private ExtendedFloatingActionButton materialButton;
    private AnimatedVectorDrawable animatedVectorDrawable;
    private TextInputLayout emailTextInputLayout,passwordTextInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        materialButton=findViewById(R.id.materialButton);
        animatedVectorDrawable=(AnimatedVectorDrawable) materialButton.getIcon();
        emailTextInputLayout =findViewById(R.id.emailTextInputLayout);
        passwordTextInputLayout=findViewById(R.id.passwordTextInputLayout);
        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animatedVectorDrawable.start();
                materialButton.setText("next");
                materialButton.shrink();
                emailTextInputLayout.setError("someting went wrrong");
                passwordTextInputLayout.setError("someting went wrrong");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}