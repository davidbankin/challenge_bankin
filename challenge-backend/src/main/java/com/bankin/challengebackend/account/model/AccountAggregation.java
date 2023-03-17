package com.bankin.challengebackend.account.model;

import com.bankin.challengebackend.bridge.entity.Account;
import java.util.List;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@With
@Value
@Builder(toBuilder = true)
public class AccountAggregation {
  long roundedValue;
  List<Account> accounts;
}
