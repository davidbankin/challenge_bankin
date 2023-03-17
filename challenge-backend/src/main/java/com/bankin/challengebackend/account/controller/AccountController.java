package com.bankin.challengebackend.account.controller;

import com.bankin.challengebackend.account.model.AccountAggregation;
import com.bankin.challengebackend.account.service.AccountService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MyController.myEndpoint is called when requesting GET /mycontroller/myroute
 *
 * <p>You can try it by running curl -X GET localhost:8080/mycontroller/myroute
 *
 * <p>The BridgeClient has been injected and ready for use. Maybe the controller, method and route
 * need some renaming?
 */
@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

  private final AccountService accountService;

  /**
   * Returns an aggregation of all accounts.
   *
   * @return a {@link AccountAggregationApiResponse}
   * @throws IOException still this exception, still no time
   */
  @GetMapping
  public AccountAggregationApiResponse list() throws IOException {
    AccountAggregation aggregation = accountService.aggregateAccounts();

    return AccountAggregationApiResponse.builder()
        .accounts(aggregation.getAccounts())
        .roundedValue(aggregation.getRoundedValue())
        .build();
  }
}
