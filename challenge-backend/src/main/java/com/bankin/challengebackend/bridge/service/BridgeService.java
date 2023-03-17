package com.bankin.challengebackend.bridge.service;

import com.bankin.challengebackend.bridge.api.BridgeApi;
import com.bankin.challengebackend.bridge.entity.AuthenticationDao;
import com.bankin.challengebackend.clients.GetAccountResponse;
import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Bridge is Bankin's SaaS API. This service is where the calls to the API should be implemented.
 *
 * <p>The "doSomething" method doesn't actually do anything yet and needs to be modified to fit the
 * exercice's needs.
 */
@Component
public class BridgeService {

  // these are hardcoded for simplicity's sake
  private static final String USER_EMAIL = "user1@mail.com";
  private static final String USER_PASSWORD = "a!Strongp#assword1";

  @Autowired private BridgeApi bridgeClient;

  @Value("${bridge.api.version}")
  private String version;

  @Value("${bridge.api.device-id}")
  private String deviceId;

  public GetAccountResponse fetchAccounts() throws IOException {
    Optional<AuthenticationDao> authenticateResponse =
        authenticateUser(USER_EMAIL, USER_PASSWORD);
    final String auth =
        authenticateResponse.isPresent()
            ? String.format("Bearer %s", authenticateResponse.get().accessToken)
            : "";
    return bridgeClient
        .getAccounts(version, auth, "MY_CLIENT_ID", "MY_CLIENT_SECRET", "", "", "500")
        .execute()
        .body();
  }

  /**
   * This method is "complete" and doesn't need editing, except if you feel some things could be
   * improved (there is no trap)
   */
  private Optional<AuthenticationDao> authenticateUser(String email, String password)
      throws IOException {
    var response = bridgeClient.authenticate(version, deviceId, email, password).execute();
    if (response.isSuccessful()) {
      return Optional.of(response.body());
    }
    return Optional.empty();
  }
}
