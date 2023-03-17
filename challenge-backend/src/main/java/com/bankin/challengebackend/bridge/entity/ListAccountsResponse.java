package com.bankin.challengebackend.bridge.entity;

import com.bankin.challengebackend.account.model.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class ListAccountsResponse {

  @JsonProperty("resources")
  List<Account> accounts;
}
