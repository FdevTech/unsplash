package com.example.p5i.onlinegallery.authenticationModule.authorizationData;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

public class AuthenticationRepository
{
    private static final String TAG = "AuthenticationRepositor";
    private static final int noAccountValue  = 0,signedOutValue=1,signedInValue=2;


    private FirebaseAuthentication mFirebaseAuthentication;
    private UnsplashWebServiceLoginModel mUnsplashWebServiceUnsplashWebServiceLoginModel;
    private LoginStateModel mLoginStateModel;
    private Context context;
    private String email,password,tocken;
    private MutableLiveData<Integer> userLoggedIncheckobserver;



    public AuthenticationRepository(Context context)
    {
        this.context=context;
        mFirebaseAuthentication=new FirebaseAuthentication();
        mUnsplashWebServiceUnsplashWebServiceLoginModel =new UnsplashWebServiceLoginModel(context);
        mLoginStateModel=new LoginStateModel(context);
        email=mLoginStateModel.retriveEmail();
        password=mLoginStateModel.retrivePassword();
        userLoggedIncheckobserver=new MutableLiveData<>();
        Log.d(TAG, "AuthenticationRepository: "+mFirebaseAuthentication.isCurrentUserIsSignedIn(email));
        obserIsUserSinedInInUnsplashService();
        checkIfUserIsSignedIn();


    }
    public void obserIsUserSinedInInUnsplashService()
    {
        mUnsplashWebServiceUnsplashWebServiceLoginModel.getIsLogedIn().observe((LifecycleOwner) context, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean)
                {
                    userLoggedIncheckobserver.setValue(signedOutValue);

                }
            }
        });
    }

    private void checkIfUserIsSignedIn()
    {

        tocken=mLoginStateModel.retriveTockenl();
        Log.d(TAG, "checkIfUserIsSignedIn: "+tocken);
        if(tocken.isEmpty())
        {
            userLoggedIncheckobserver.setValue(noAccountValue);
        }
        else
        {
            Log.d(TAG, "checkIfUserIsSignedIn: "+mFirebaseAuthentication.isCurrentUserIsSignedIn(email));
            if(!mFirebaseAuthentication.isCurrentUserIsSignedIn(email))
            {

               userLoggedIncheckobserver.setValue(signedOutValue);
            }
            else {
                userLoggedIncheckobserver.setValue(signedInValue);
            }

        }
    }

    private void creatingUser(String Email,String Password)
    {
        Log.d(TAG, "creatingUser: ");

        mFirebaseAuthentication.createAccount(Email, Password);
    }
    private void requestingTockenFromUnsplashService(Uri mUri)
    {
        Log.d(TAG, "requestingTockenFromUnsplashService: ");
        mUnsplashWebServiceUnsplashWebServiceLoginModel.getTheTocken(mUri);
    }

    private void observingUserCreatingIntoFirebase(final Uri mUri)
    {
        mFirebaseAuthentication.getCreatingUserCheck().observe((LifecycleOwner) context, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Log.d(TAG, "onChanged: getCreatingUserCheck()"+aBoolean);
                requestingTockenFromUnsplashService(mUri);
            }
        });
    }
    private void observingSignedInUserIntoFirebase()
    {
        mFirebaseAuthentication.getCheckUserSignedIn().observe((LifecycleOwner) context, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Log.d(TAG, "onChanged: "+aBoolean);
                userLoggedIncheckobserver.setValue(signedInValue);
            }
        });
    }

    public void creatingUser(Uri mUri,String Email,String Password)
    {
        creatingUser(Email, Password);
        observingUserCreatingIntoFirebase(mUri);
    }

    public void signInInToTheApp(String Email, String Password)
    {
        mFirebaseAuthentication.signInTheApp(Email, Password);
        observingSignedInUserIntoFirebase();
    }

    public MutableLiveData<Integer> getUserLoggedIncheckobserver() {
        return userLoggedIncheckobserver;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getTocken() {
        return tocken;
    }

    public void setEmail(String email) {
        this.email = email;
        mLoginStateModel.saveEmail(email);

    }

    public void setPassword(String password) {
        this.password = password;
        mLoginStateModel.savePassword(password);
    }

    //TODO I should remove This from Here
    public String getUrl() {
        return mUnsplashWebServiceUnsplashWebServiceLoginModel.getUrl();
    }
}
