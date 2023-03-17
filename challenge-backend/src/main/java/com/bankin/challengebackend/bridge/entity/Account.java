package com.bankin.challengebackend.bridge.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Account {

  @JsonProperty("id")
  Long id;

  @JsonProperty("name")
  String name;

  @JsonProperty("balance")
  Double balance;

  @JsonProperty("type")
  String type;

  @JsonProperty("currency_code")
  String currencyCode;
}
