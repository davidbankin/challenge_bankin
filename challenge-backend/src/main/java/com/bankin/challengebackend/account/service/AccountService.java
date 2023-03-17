package com.bankin.challengebackend.account.service;

import com.bankin.challengebackend.account.model.AccountAggregation;
import com.bankin.challengebackend.account.type.AccountType;
import com.bankin.challengebackend.bridge.entity.Account;
import com.bankin.challengebackend.bridge.entity.ListAccountsResponse;
import com.bankin.challengebackend.bridge.service.BridgeService;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Responsible for fetching accounts */
@Service
@RequiredArgsConstructor
public class AccountService {

  private final BridgeService bridgeService;

  /**
   * Aggregate accounts
   *
   * @return a {@link AccountAggregation}
   * @throws IOException still no time, its 20:59 already
   */
  public AccountAggregation aggregateAccounts() throws IOException {
    ListAccountsResponse response = bridgeService.fetchAccounts();
    List<Account> accounts = response.getAccounts();
    long accountsSum = computeSum(accounts);
    long roundedValue = ((accountsSum + 99) / 100) * 100;

    return AccountAggregation.builder().accounts(accounts).roundedValue(roundedValue).build();
  }

  private static long computeSum(List<Account> accounts) {
    return (long)
        Math.ceil(
            accounts.stream()
                .filter(AccountService::isCheckingOrSavings)
                .mapToDouble(Account::getBalance)
                .sum());
  }

  private static boolean isCheckingOrSavings(Account account) {
    return account.getType().equals(AccountType.CHECKING.name())
        || account.getType().equals(AccountType.SAVINGS.name());
  }
}
