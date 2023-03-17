package com.bankin.challengebackend.account.controller;

import com.bankin.challengebackend.account.model.Account;
import java.util.List;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@With
@Value
@Builder(toBuilder = true)
public class AccountAggregationApiResponse {
  long roundedValue;
  List<Account> accounts;
}
