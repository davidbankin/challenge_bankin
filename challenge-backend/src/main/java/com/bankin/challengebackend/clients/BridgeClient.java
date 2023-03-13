package com.bankin.challengebackend.clients;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BridgeClient {

    @POST("/v2/authenticate")
    Call<AuthenticateResponse> authenticate(@Header("Bankin-Version") String version,
                                            @Header("Bankin-Device") String deviceId,
                                            @Query(value = "email", encoded = true) String email,
                                            @Query(value = "password", encoded = true) String password);

    @GET("/v2/changeme")
    Call<GetAccountResponse> getAccounts(@Header("Bankin-Version") String version,
                                         @Header("Authorization") String authorization,
                                         @Query("param") String param);
}
