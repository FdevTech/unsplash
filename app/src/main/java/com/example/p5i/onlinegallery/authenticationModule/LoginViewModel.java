package com.example.p5i.onlinegallery.authenticationModule;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.ViewModel;

import com.example.p5i.onlinegallery.authenticationModule.authorizationData.AutorizationInterface;
import com.example.p5i.onlinegallery.authenticationModule.authorizationData.AutorizationResponsePJO;

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
    String url="https://unsplash.com/oauth/authorize?client_id=CH5YIV_t-PtFB52Db4bAXGQxiQEVy79ZTy9wa4z90iQ&redirect_uri=curta://callback&response_type=code" +
            "&scope=public read_user write_user read_photos write_photos write_likes write_followers read_collections write_collections";
    private Retrofit retrofit;
    private AutorizationInterface mAutorizationInterface;
    private boolean extend=true;

    public LoginViewModel(Context context)
    {
        this.context=context;
        retrofit=new Retrofit.Builder()
                .baseUrl("https://unsplash.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mAutorizationInterface=retrofit.create(AutorizationInterface.class);
        intent=new Intent(Intent.ACTION_VIEW, Uri.parse(url));

    }
    public void loginOnClick(View view)
    {
        Log.d(TAG, "loginOnClick: ");
        context.startActivity(intent);
    }
    public void getTheTocken(Uri mUri)
    {
        mAutorizationInterface.getAccessTocken("CH5YIV_t-PtFB52Db4bAXGQxiQEVy79ZTy9wa4z90iQ","yUWMA9JU_1ZuLmwRbnkwDSx1cI3TKktQTK8x2eAC-dk",
                "curta://callback",mUri.getQueryParameter("code"),"authorization_code").enqueue(new Callback<AutorizationResponsePJO>() {
            @Override
            public void onResponse(Call<AutorizationResponsePJO> call, Response<AutorizationResponsePJO> response) {
                Log.d(TAG, "onResponse: "+response.body().getAccess_token());
            }

            @Override
            public void onFailure(Call<AutorizationResponsePJO> call, Throwable t) {

            }
        });
    }
}
