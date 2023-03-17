package com.bankin.challengebackend.account.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.bankin.challengebackend.account.model.AccountAggregation;
import com.bankin.challengebackend.account.type.AccountType;
import com.bankin.challengebackend.bridge.entity.Account;
import com.bankin.challengebackend.bridge.entity.ListAccountsResponse;
import com.bankin.challengebackend.bridge.service.BridgeService;
import java.util.List;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/** At least a little dummy unit test toi test that the business rule is ok */
@ExtendWith(MockitoExtension.class)
@DisplayName("Account Service Unit Test")
class AccountServiceUnitTest {

  @Mock BridgeService bridgeService;
  @InjectMocks AccountService accountService;

  @Nested
  @DisplayName("given list of accounts")
  class GivenListOfAccounts {

    private final ListAccountsResponse response = buildResponse();

    @SneakyThrows
    @BeforeEach
    void setUp() {
      when(bridgeService.fetchAccounts()).thenReturn(response);
    }

    @Nested
    @DisplayName("when calling aggregate accounts")
    class WhenCallingAggregateAccounts {

      private AccountAggregation result;

      @SneakyThrows
      @BeforeEach
      void setUp() {
        result = accountService.aggregateAccounts();
      }

      @Test
      @DisplayName("then it should return two accounts")
      void thenItShouldReturnTwoAccounts() {
        assertThat(result.getAccounts()).hasSize(2);
      }

      @Test
      @DisplayName("then it should sum and round balances")
      void thenItShouldSumAndRoundBalances() {
        assertThat(result.getRoundedValue()).isEqualTo(300);
      }
    }
  }

  private ListAccountsResponse buildResponse() {
    final List<Account> accounts =
        List.of(
            Account.builder().type(AccountType.CHECKING.name()).balance(230.2).build(),
            Account.builder().type(AccountType.SAVINGS.name()).balance(10.1).build());
    ListAccountsResponse response = new ListAccountsResponse();
    response.setAccounts(accounts);
    return response;
  }
}
