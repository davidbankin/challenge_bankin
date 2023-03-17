package com.bankin.challengebackend.bridge.repository;

import com.bankin.challengebackend.bridge.api.BridgeApi;
import com.bankin.challengebackend.bridge.entity.AuthenticationResponse;
import com.bankin.challengebackend.bridge.entity.ListAccountsResponse;
import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 * Bridge is Bankin's SaaS API. This service is where the calls to the API should be implemented.
 *
 * <p>The "doSomething" method doesn't actually do anything yet and needs to be modified to fit the
 * exercice's needs.
 */
@Repository
public class BridgeRepository {

  // these are hardcoded for simplicity's sake
  private static final String USER_EMAIL = "user1@mail.com";
  private static final String USER_PASSWORD = "a!Strongp#assword1";

  // I did not find those. They are required per the documentation, but with or without it, I get an
  // empty list of account even when trying the api directly with postman.
  // I surely am missing something here, sorry I got stuck in there trying to find those.
  // Also I dont understand why the authentication route works fine without client_id and secret
  // even when the docs states that they are required
  private static final String MY_CLIENT_ID = "MY_CLIENT_ID";
  private static final String MY_CLIENT_SECRET = "MY_CLIENT_SECRET";

  @Autowired private BridgeApi bridgeClient;

  @Value("${bridge.api.version}")
  private String version;

  @Value("${bridge.api.device-id}")
  private String deviceId;

  /**
   * fetch acocunts from bridge api
   *
   * <p>Should handle better the optional for the authentication, should handle better the retrofit
   * execute().body() but no time.
   *
   * <p>Should also handle next link with a while
   *
   * @return a {@link ListAccountsResponse}
   * @throws IOException should throw dedicated exception, but no time.
   */
  public ListAccountsResponse fetchAccounts() throws IOException {
    Optional<AuthenticationResponse> authenticateResponse = authenticateUser();
    final String auth =
        authenticateResponse.isPresent()
            ? String.format("Bearer %s", authenticateResponse.get().getAccessToken())
            : "";
    return bridgeClient
        .getAccounts(version, auth, MY_CLIENT_ID, MY_CLIENT_SECRET, "", "", "500")
        .execute()
        .body();
  }

  /**
   * This method is "complete" and doesn't need editing, except if you feel some things could be
   * improved (there is no trap)
   */
  private Optional<AuthenticationResponse> authenticateUser() throws IOException {
    var response =
        bridgeClient.authenticate(version, deviceId, USER_EMAIL, USER_PASSWORD).execute();
    if (response.isSuccessful()) {
      return Optional.ofNullable(response.body());
    }
    return Optional.empty();
  }
}
