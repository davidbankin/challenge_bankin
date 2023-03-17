package com.bankin.challengebackend.bridge.api;

import com.bankin.challengebackend.bridge.internal.AuthenticationResponse;
import com.bankin.challengebackend.bridge.internal.ListAccountsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BridgeApi {

  @POST("/v2/authenticate")
  Call<AuthenticationResponse> authenticate(
      @Header("Bankin-Version") String version,
      @Header("Bankin-Device") String deviceId,
      @Query(value = "email", encoded = true) String email,
      @Query(value = "password", encoded = true) String password);

  @GET("/v2/accounts")
  Call<ListAccountsResponse> getAccounts(
      @Header("Bankin-Version") String version,
      @Header("Authorization") String authorization,
      @Query("client_id") String clientId,
      @Query("client_secret") String clientSecret,
      @Query("before") String before,
      @Query("after") String after,
      @Query("limit") String limit);
}
