package com.example.p5i.onlinegallery.authenticationModule;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.example.p5i.onlinegallery.MainActivity;
import com.example.p5i.onlinegallery.Util.ActivtyTransitionModel;
import com.example.p5i.onlinegallery.authenticationModule.authorizationData.LoginStateModel;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class LoginViewModel extends BaseObservable
{
    private static final String TAG = "LoginViewModel";
    private Context context;
    private boolean extend=true;
    private LoginModel mLoginModel;
    private static FabAnimationModel mFabAnimationModel;
    private ActivtyTransitionModel mActivtyTransitionModel;
    private LoginStateModel mLoginStateModel;
    private boolean enabled;
    private String email,passaword;
    private ValidatorModel mValidatorModel;
    private Boolean checkForErrorEnabled;
    private static boolean emtyEmailField,emptyPasswordField,valideEmail=true,validPassWord=true;
    public LoginViewModel(Context context)
    {
        Log.d(TAG, "LoginViewModel: ");;
        this.context=context;
        mLoginModel=new LoginModel(context);
        mFabAnimationModel=new FabAnimationModel(context);
        mActivtyTransitionModel=new ActivtyTransitionModel(context);
        mValidatorModel=new ValidatorModel();

        initState();
       
        observeLogingIn();
        observeFabEnabledState();
        observeFabExtendedState();

    }
    private void initState()
    {
        Log.d(TAG, "initState: ");
        mLoginStateModel=new LoginStateModel(context);
        mFabAnimationModel.enbaleFab(mLoginStateModel.retriveFabState());

        if(!mLoginStateModel.retriveTockenl().isEmpty())
        {
            mFabAnimationModel.extendedFab(false);
        }
        setCheckForErrorEnabled(false);
        setEmail(mLoginStateModel.retriveEmail());
        setPassaword(mLoginStateModel.retrivePassword());
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
        mValidatorModel.getEverythingIsValidatedLiveData().observe((LifecycleOwner) context, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean validate) {
                if(validate)
                {
                    mFabAnimationModel.enbaleFab(validate);
                    setCheckForErrorEnabled(!validate);
                }
            }
        });
    }

    public void validate()
    {
        mValidatorModel.validate(email,passaword);
        observeEmailField();
        observerPassWordField();
        observeValidation();
        setCheckForErrorEnabled(true);
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

    @Bindable
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        notifyChange();
        mLoginStateModel.saveFabState(enabled);
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyChange();
        mLoginStateModel.saveEmail(email);
    }

    @Bindable
    public String getPassaword() {
        return passaword;
    }

    public void setPassaword(String passaword) {
        this.passaword = passaword;
        notifyChange();
        mLoginStateModel.savePassword(passaword);
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

    @Bindable
    public Boolean getCheckForErrorEnabled() {
        return checkForErrorEnabled;
    }

    public void setCheckForErrorEnabled(Boolean checkForErrorEnabled) {
        this.checkForErrorEnabled = checkForErrorEnabled;
        notifyChange();
        
    }
}
