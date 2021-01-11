package com.example.p5i.onlinegallery.authenticationModule;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.example.p5i.onlinegallery.MainActivity;
import com.example.p5i.onlinegallery.Util.ActivtyTransitionModel;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class LoginViewModel extends BaseObservable
{
    private static final String TAG = "LoginViewModel";
    private Context context;
    private boolean extend=true;
    private LoginModel mLoginModel;
    private static FabAnimationModel mFabAnimationModel;
    private ActivtyTransitionModel mActivtyTransitionModel;
    private boolean enabled;

    public LoginViewModel(Context context)
    {
        this.context=context;
        mLoginModel=new LoginModel();
        mFabAnimationModel=new FabAnimationModel(context);
        mActivtyTransitionModel=new ActivtyTransitionModel(context);

        mFabAnimationModel.enbaleFab(false);
        mFabAnimationModel.extendedFab(true);
        observeLogingIn();
        observeFabEnabledState();
        observeFabExtendedState();

    }
    public void loginOnClick(View view)
    {
        if(extend)
        {
            mActivtyTransitionModel.startringBrowserToGetAutheticate(mLoginModel.getUrl());
        }
         else
        {
             mActivtyTransitionModel.startActivity(new MainActivity());
        }
    }
    private void observeLogingIn()
    {
        mLoginModel.getIsLogedIn().observe((LifecycleOwner) context, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean)
                {
                    mFabAnimationModel.extendedFab(!aBoolean);
                }
            }
        });
    }
    private void observeFabEnabledState()
    {
        mFabAnimationModel.getFabEnabledStateLiveData().observe((LifecycleOwner) context, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                setEnabled(aBoolean);
            }
        });
    }

    public void observeFabExtendedState()
    {
        mFabAnimationModel.getFabExtendStateLiveData().observe((LifecycleOwner) context, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                setExtend(aBoolean);
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

    @Bindable
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        notifyChange();
    }


}
