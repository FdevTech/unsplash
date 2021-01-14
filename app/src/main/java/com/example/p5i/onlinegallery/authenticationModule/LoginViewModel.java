package com.example.p5i.onlinegallery.authenticationModule;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.example.p5i.onlinegallery.MainActivity;
import com.example.p5i.onlinegallery.Util.ActivtyTransitionModel;
import com.example.p5i.onlinegallery.authenticationModule.authorizationData.AuthenticationRepository;
import com.example.p5i.onlinegallery.authenticationModule.authorizationData.UnsplashWebServiceLoginModel;
import com.example.p5i.onlinegallery.authenticationModule.authorizationData.LoginStateModel;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class LoginViewModel extends BaseObservable
{
    private static final String TAG = "LoginViewModel";
    private Context context;

    private static final int noAccountValue  = 0,signedOutValue=1,signedInValue=2;
    private int userLoggedIncheckobserver=noAccountValue;



    private static FabAnimationModel mFabAnimationModel;
    private ActivtyTransitionModel mActivtyTransitionModel;
    private AuthenticationRepository mAuthenticationRepository;
    private ValidatorModel mValidatorModel;

    private String email,passaword;
    private boolean extend,enabled;
    private Boolean checkForErrorEnabled;

    private static boolean emtyEmailField,emptyPasswordField,valideEmail=true,validPassWord=true;

    private Drawable icon;


    private Pair<Boolean,Boolean> fabState;

    public LoginViewModel(Context context)
    {
        Log.d(TAG, "LoginViewModel: ");;
        this.context=context;
        mFabAnimationModel=new FabAnimationModel(context);
        mActivtyTransitionModel=new ActivtyTransitionModel(context);
        mValidatorModel=new ValidatorModel();

        mAuthenticationRepository=new AuthenticationRepository(context);

        initState();
        



    }
    private void initState()
    {

        observeFab();
        obsrveAutheticationState();
        setEmail(mAuthenticationRepository.getEmail());
        setPassaword(mAuthenticationRepository.getPassword());
    }


    private void observeEmailField()
    {

        mValidatorModel.getEmailFieldEmptyMutableLiveData().observe((LifecycleOwner) context, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                setEmtyEmailField(aBoolean);
            }
        });

        mValidatorModel.getEmailValidMutableLiveData().observe((LifecycleOwner) context, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                setValideEmail(aBoolean);
            }
        });
    }
    private void observerPassWordField()
    {
        mValidatorModel.getPasswordFieldEmpty().observe((LifecycleOwner) context, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                setEmptyPasswordField(aBoolean);
            }
        });

        mValidatorModel.getPasswordFieldTooWeek().observe((LifecycleOwner) context, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                setValidPassWord(!aBoolean);
            }
        });
    }

    private void observeValidation()
    {
        Log.d(TAG, "observeValidation: ");
        mValidatorModel.getEverythingIsValidatedLiveData().observe((LifecycleOwner) context, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean validate) {
                if(validate)
                {
                    mFabAnimationModel.enbaleFab(validate);

                }
            }
        });
    }


    private void observeFab()
    {
        observeFabEnbaled();
        observeFabExtend();

    }

    private void observeFabEnbaled()
    {
        mFabAnimationModel.getFabEnabledStateLiveData().observe((LifecycleOwner) context, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Log.d(TAG, "onChanged: getFabEnabledStateLiveData() "+aBoolean);
                setEnabled(aBoolean);
            }
        });
    }
    private void observeFabExtend()
    {
        mFabAnimationModel.getFabExtendStateLiveData().observe((LifecycleOwner) context, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Log.d(TAG, "onChanged:getFabExtendStateLiveData() "+aBoolean);
                setExtend(aBoolean);
            }
        });
    }



  private void obsrveAutheticationState()
  {
      mAuthenticationRepository.getUserLoggedIncheckobserver().observe((LifecycleOwner) context, new Observer<Integer>() {
          @Override
          public void onChanged(Integer stattus) {
              userLoggedIncheckobserver=stattus;
              Log.d(TAG, "onChanged:getUserLoggedIncheckobserver() "+stattus);
              if(userLoggedIncheckobserver==signedOutValue)
              {
                  mFabAnimationModel.enbaleFab(true);
                  mFabAnimationModel.extendedFab(true);
              }
              if(userLoggedIncheckobserver==signedInValue)
              {
                  mFabAnimationModel.enbaleFab(true);
                  mFabAnimationModel.extendedFab(false);              }
          }
      });
  }
    public void validate()
    {
        mValidatorModel.validate(email,passaword);
        observeEmailField();
        observerPassWordField();
        observeValidation();

    }





    public void getTheTocken(Uri mUri)
    {


        mAuthenticationRepository.creatingUser(mUri,email,passaword);

    }
   @BindingAdapter("android:extend")
   public static void setExtendFab(ExtendedFloatingActionButton fab,boolean extend)
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
    public void loginOnClick(View view)
    {
        Log.d(TAG, "loginOnClick: "+userLoggedIncheckobserver);

        if(userLoggedIncheckobserver==noAccountValue)
        {
            mActivtyTransitionModel.startringBrowserToGetAutheticate(mAuthenticationRepository.getUrl());

        }
        else if(userLoggedIncheckobserver==signedOutValue)
        {
            mAuthenticationRepository.signInInToTheApp(email,passaword);
        }
        else if(userLoggedIncheckobserver==signedInValue)
        {
            mActivtyTransitionModel.startActivity(new MainActivity());
        }
    }
    @Bindable
    public boolean isExtend() {
        return extend;

    }


    @BindingAdapter("android:errorEmptyMessageEmail")
    public static void setErrorMessageEmptyForEmail(TextInputLayout mTextInputLayout,boolean empty)
    {
       

        if(empty)
        {
            mTextInputLayout.setError("email is empty");
        }
    }
    @BindingAdapter("android:errorNoValidMessageEmail")
    public static void setErrorMessageNoValidForEmail(TextInputLayout mTextInputLayout,boolean valid)
    {

        if(emtyEmailField)
        {
            mTextInputLayout.setError("email is empty");
        }
        else
        {
            if(!valid)
            {
                mTextInputLayout.setError("email is no valid");
            }

        }
    }

    @BindingAdapter("android:errorEmptyMessagePassword")
    public static void setErrorMessageEmptyForPassword(TextInputLayout mTextInputLayout,boolean empty)
    {

        if(empty)
        {
            mTextInputLayout.setError("password is empty");
        }
    }
    @BindingAdapter("android:errorNoValidMessagePassword")
    public static void setErrorMessageNoValidForPassword(TextInputLayout mTextInputLayout,boolean valid)
    {

        if(emtyEmailField)
        {
            mTextInputLayout.setError("password is empty");
        }
        else
        {
            if(!valid)
            {

                mTextInputLayout.setError("password is no valid");
            }

        }
    }
    private void setExtend(boolean extend) {
        this.extend = extend;
        notifyChange();
    }

    @Bindable
    public Drawable getIcon() {
        return mFabAnimationModel.getIcon();
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
        notifyChange();
    }

    @Bindable
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        Log.d(TAG, "setEnabled: "+enabled);
        this.enabled = enabled;
        notifyChange();

    }


    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {

        this.email = email;
        notifyChange();
        mAuthenticationRepository.setEmail(email);

    }

    @Bindable
    public String getPassaword() {
        return passaword;
    }

    public void setPassaword(String passaword) {
        this.passaword = passaword;
        notifyChange();
        mAuthenticationRepository.setPassword(passaword);

    }

    @Bindable
    public boolean isEmtyEmailField() {
        return emtyEmailField;
    }

    public void setEmtyEmailField(boolean emtyEmailField) {
        this.emtyEmailField = emtyEmailField;
        notifyChange();
    }

    @Bindable
    public boolean isEmptyPasswordField() {
        return emptyPasswordField;
    }

    public void setEmptyPasswordField(boolean emptyPasswordField) {
        this.emptyPasswordField = emptyPasswordField;
        notifyChange();
    }

    @Bindable
    public boolean isValideEmail() {
        return valideEmail;
    }

    public void setValideEmail(boolean valideEmail) {
        this.valideEmail = valideEmail;
        notifyChange();
    }

    @Bindable
    public boolean isValidPassWord() {
        return validPassWord;
    }

    public void setValidPassWord(boolean validPassWord) {
        this.validPassWord = validPassWord;
        notifyChange();
    }

  
}
