package com.example.p5i.onlinegallery.authenticationModule.authorizationData;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseAuthentication
{
    private static final String TAG = "FirebaseAuth";
    private FirebaseAuth mFirebaseAuth;
    private MutableLiveData<Boolean> creatingUserCheck,checkUserSignedIn;
    private MutableLiveData<String> errorMessageMutableLifeData;
    private FirebaseUser mFirebaseUser;

    public FirebaseAuthentication()
    {
        mFirebaseAuth=FirebaseAuth.getInstance();
        creatingUserCheck =new MutableLiveData<>();
        checkUserSignedIn=new MutableLiveData<>();
        errorMessageMutableLifeData=new MutableLiveData<>();
        creatingUserCheck.setValue(false);
        checkUserSignedIn.setValue(false);

    }
    public void createAccount(String Email,String Password)
    {
        Log.d(TAG, "createAccount: ");
        mFirebaseAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    creatingUserCheck.setValue(true);
                    mFirebaseUser=mFirebaseAuth.getCurrentUser();

                }
                else
                {
                    creatingUserCheck.setValue(false);
                }

            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                errorMessageMutableLifeData.setValue(e.getMessage());
            }
        });
    }
    public void signInTheApp(String Email,String Password)
    {
         mFirebaseAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful())
                   {

                       mFirebaseUser=mFirebaseAuth.getCurrentUser();
                       Log.d(TAG, "onComplete: "+mFirebaseUser.getEmail());
                       checkUserSignedIn.setValue(true);
                   }
                   else
                   {
                       checkUserSignedIn.setValue(false);
                   }
             }
         }).addOnFailureListener(new OnFailureListener() {
             @Override
             public void onFailure(@NonNull Exception e) {
                 errorMessageMutableLifeData.setValue(e.getMessage());
             }
         });
    }

    public boolean isCurrentUserIsSignedIn(String Email)
    {
        return mFirebaseAuth.isSignInWithEmailLink(Email);
    }
    public String isUserExixt()
    {
        if(mFirebaseAuth.getCurrentUser()!=null)
        {
             return mFirebaseAuth.getCurrentUser().getEmail();
        }else
        {
            return "no user exist";
        }
    }

    public void signOutFromTheApp()
    {
        mFirebaseAuth.signOut();
    }



    public MutableLiveData<String> getErrorMessageMutableLifeData() {
        return errorMessageMutableLifeData;
    }

    public MutableLiveData<Boolean> getCheckUserSignedIn() {
        return checkUserSignedIn;
    }

    public MutableLiveData<Boolean> getCreatingUserCheck() {
        return creatingUserCheck;
    }
}
