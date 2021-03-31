package com.example.p5i.onlinegallery.authenticationModule.authorizationData;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.example.p5i.onlinegallery.authenticationModule.authorizationData.AutorizationInterface;
import com.example.p5i.onlinegallery.authenticationModule.authorizationData.AutorizationResponsePJO;
import com.example.p5i.onlinegallery.authenticationModule.authorizationData.LoginStateModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UnsplashWebServiceLoginModel
{
    private static final String TAG = "LoginModel";
    private Retrofit retrofit;
    private AutorizationInterface mAutorizationInterface;
    private MutableLiveData<Boolean> isLogedIn;
    private LoginStateModel mLoginStateModel;

    private String clientID="U4e3aiPSmYENZTwqMYUMv1P2XftujdL6byd9e_jM_UI";
    private String clientSecret="TVnudFDwuYEJuL0GvB8qcCAR9Vqsr14GcMYVwa8B0OM";
    private String redirect_uri="curta://callback";

    private String url="https://unsplash.com/oauth/authorize?client_id="+clientID+"&redirect_uri="+redirect_uri +
            "&response_type=code&scope=public read_user write_user read_photos write_photos write_likes write_followers read_collections write_collections";
    /*private String url="https://unsplash.com/oauth/authorize?client_id=yrnk1pXnbDXXE4MYt_vk0sB5fMWJkVMvokLv16zkG7w&redirect_uri=curta://callback&response_type=code" +
            "&scope=public read_user write_user read_photos write_photos write_likes write_followers read_collections write_collections";*/
    public UnsplashWebServiceLoginModel(Context context)
    {
        mLoginStateModel=new LoginStateModel(context);
        isLogedIn=new MutableLiveData<>();
        isLogedIn.setValue(false);
        retrofit=new Retrofit.Builder()
                .baseUrl("https://unsplash.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mAutorizationInterface=retrofit.create(AutorizationInterface.class);
    }
    public void  getTheTocken(Uri mUri)
    {
        mAutorizationInterface.getAccessTocken(clientID,clientSecret,
                redirect_uri,mUri.getQueryParameter("code"),"authorization_code").enqueue(new Callback<AutorizationResponsePJO>() {
            @Override
            public void onResponse(Call<AutorizationResponsePJO> call, Response<AutorizationResponsePJO> response) {
                if(response.body()!=null)
                {
                    mLoginStateModel.saveTocken(response.body().getAccess_token());
                     isLogedIn.setValue(true);
                }
                else
                {
                    Log.d(TAG, "onResponse: "+response.body());
                    Log.d(TAG, "onResponse: "+response.code());
                }
            }

            @Override
            public void onFailure(Call<AutorizationResponsePJO> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<Boolean> getIsLogedIn() {
        return isLogedIn;
    }

    public String getUrl() {
        return url;
    }
}
