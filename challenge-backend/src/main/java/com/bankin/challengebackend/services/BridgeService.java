package com.bankin.challengebackend.services;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bankin.challengebackend.clients.AuthenticateResponse;
import com.bankin.challengebackend.clients.BridgeClient;
import com.bankin.challengebackend.clients.GetAccountResponse;

/**
 * Bridge is Bankin's SaaS API. This service is where the calls to the API should be implemented.
 * <p>
 * The "doSomething" method doesn't actually do anything yet and needs to be modified to fit the exercice's needs.
 */
@Component
public class BridgeService {

    // these are hardcoded for simplicity's sake
    private static final String USER_EMAIL = "user1@mail.com";
    private static final String USER_PASSWORD = "a!Strongp#assword1";

    @Autowired
    private BridgeClient bridgeClient;

    @Value("${bridge.api.version}")
    private String version;
    @Value("${bridge.api.device-id}")
    private String deviceId;

    /**
     * This method is "complete" and doesn't need editing, except if you feel some things could be improved (there
     * is no trap)
     */
    private Optional<AuthenticateResponse> authenticateUser(String email, String password) throws IOException {
        var response = bridgeClient.authenticate(version, deviceId, email, password).execute();
        if (response.isSuccessful()) {
            return Optional.of(response.body());
        }
        return Optional.empty();
    }

    public GetAccountResponse doSomething() throws IOException {
        var authorization = authenticateUser(USER_EMAIL, USER_PASSWORD);
        return bridgeClient.getAccounts(version, "Bearer ", "myvalue").execute().body();
    }

}
