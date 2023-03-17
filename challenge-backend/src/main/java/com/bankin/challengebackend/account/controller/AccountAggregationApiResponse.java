package com.bankin.challengebackend.account.controller;

import com.bankin.challengebackend.bridge.entity.Account;
import java.util.List;
import lombok.Builder;
import lombok.Value;
import lombok.With;

/**
 * I usually do a dedicated dto for api response so that whatever happens down the road, the api
 * contract does not change
 */
@With
@Value
@Builder(toBuilder = true)
public class AccountAggregationApiResponse {
  long roundedValue;
  List<Account> accounts;
}
