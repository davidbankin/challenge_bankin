package com.bankin.challengebackend.bridge.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import lombok.Data;

@Data
public class AuthenticationResponse {

  @JsonProperty("access_token")
  String accessToken;

  @JsonProperty("expires_at")
  Instant expiresAt;
}
