package com.example.p5i.onlinegallery.authenticationModule;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.p5i.onlinegallery.R;
import com.example.p5i.onlinegallery.authenticationModule.authorizationData.AutorizationInterface;
import com.example.p5i.onlinegallery.authenticationModule.authorizationData.AutorizationResponsePJO;
import com.example.p5i.onlinegallery.authenticationModule.authorizationData.FabAnimationModel;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginViewModel extends BaseObservable
{
    private static final String TAG = "LoginViewModel";
    private Context context;
    private Intent intent;
    private boolean extend=true;
    private LoginModel mLoginModel;
    private static FabAnimationModel mFabAnimationModel;
    public LoginViewModel(Context context)
    {
        this.context=context;
        mLoginModel=new LoginModel();
        intent=new Intent(Intent.ACTION_VIEW, Uri.parse(mLoginModel.getUrl()));
        mFabAnimationModel=new FabAnimationModel(context);
        obserLogingIn();
    }
    public void loginOnClick(View view)
    {
        if(extend)
        {
            context.startActivity(intent);
        }
         else
        {

        }
    }
    private void obserLogingIn()
    {
        mLoginModel.getIsLogedIn().observe((LifecycleOwner) context, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean)
                {
                    setExtend(false);
                }
            }
        });
    }
    public void getTheTocken(Uri mUri)
    {
        mLoginModel.getTheTocken(mUri);

    }
   @BindingAdapter("android:extend")
   public static void setExtend(ExtendedFloatingActionButton fab,boolean extend)
   {
       if(extend)
       {
           fab.extend();
       }
       else
       {
           fab.shrink();
           mFabAnimationModel.animateIconInFab();
       }
   }
    @Bindable
    public boolean isExtend() {
        return extend;

    }

    private void setExtend(boolean extend) {
        this.extend = extend;
        notifyChange();
    }

    @Bindable
    public Drawable getIcon() {
        return mFabAnimationModel.getIcon();
    }
}
