package com.bankin.challengebackend.account.model;

import com.bankin.challengebackend.account.entity.AccountDao;import lombok.Builder;
import lombok.Value;
import lombok.With;import java.util.List;

@With
@Value
@Builder(toBuilder = true)
public class AccountAggregationDto {
  long roundedValue;
  List<AccountDao> accounts;
}
