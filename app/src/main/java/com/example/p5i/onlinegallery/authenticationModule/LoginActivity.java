package com.example.p5i.onlinegallery.authenticationModule;

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
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.p5i.onlinegallery.R;
import com.example.p5i.onlinegallery.authenticationModule.authorizationData.AutorizationInterface;
import com.example.p5i.onlinegallery.authenticationModule.authorizationData.AutorizationResponsePJO;
import com.example.p5i.onlinegallery.databinding.ActivityLoginBinding;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private LoginViewModel mLoginViewModel;
    private ActivityLoginBinding mActivityLoginBinding;
    //private FloatingActionButton materialButton;
    private ExtendedFloatingActionButton materialButton;
    private AnimatedVectorDrawable animatedVectorDrawable;
    private TextInputLayout emailTextInputLayout,passwordTextInputLayout;
    Intent intent;
    String url="https://unsplash.com/oauth/authorize?client_id=CH5YIV_t-PtFB52Db4bAXGQxiQEVy79ZTy9wa4z90iQ&redirect_uri=curta://callback&response_type=code" +
            "&scope=public read_user write_user read_photos write_photos write_likes write_followers read_collections write_collections";

    private Retrofit retrofit;
    private AutorizationInterface mAutorizationInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityLoginBinding=ActivityLoginBinding.inflate(getLayoutInflater());
        mLoginViewModel=new LoginViewModel(this);
        mActivityLoginBinding.setLoginBinding(mLoginViewModel);
        setContentView(mActivityLoginBinding.getRoot());
        materialButton=findViewById(R.id.materialButton);
        animatedVectorDrawable=(AnimatedVectorDrawable) materialButton.getIcon();
        emailTextInputLayout =findViewById(R.id.emailTextInputLayout);
        passwordTextInputLayout=findViewById(R.id.passwordTextInputLayout);

                /*materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent);
               // animatedVectorDrawable.start();
              //  materialButton.shrink();
              //  emailTextInputLayout.setError("someting went wrrong");
               // passwordTextInputLayout.setError("someting went wrrong");
            }
        });*/
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
            Toast.makeText(this,"we got it "+ mUri.getQueryParameter("code"),Toast.LENGTH_LONG).show();
            mLoginViewModel.getTheTocken(mUri);

        }

    }
}