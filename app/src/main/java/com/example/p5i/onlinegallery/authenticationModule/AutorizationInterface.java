package com.example.p5i.onlinegallery.authenticationModule;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AutorizationInterface
{
   @POST("oauth/token")
    Call<AutorizationResponsePJO> getAccessTocken(@Query("client_id")String client_id,@Query("client_secret")String client_secret,
                                                  @Query("redirect_uri")String redirect_uri,@Query("code")String code,
                                                  @Query("grant_type")String grant_type);

}
