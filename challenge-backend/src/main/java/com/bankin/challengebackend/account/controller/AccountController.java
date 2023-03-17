package com.bankin.challengebackend.controllers;

import com.bankin.challengebackend.account.entity.AccountDao;
import com.bankin.challengebackend.clients.GetAccountResponse;
import com.bankin.challengebackend.services.BridgeService;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AccountsController {

  @Autowired private BridgeService bridgeService;

  @GetMapping
  public ListAccountResponse list() throws IOException {
    GetAccountResponse response = bridgeService.fetchAccounts();
    List<AccountDao> accounts = response.accounts;
    long accountsSum =
        (long)
            Math.ceil(
                accounts.stream()
                    .filter(AccountsController::isCheckingOrSavings)
                    .mapToDouble(account -> account.balance)
                    .sum());
    long roundedValue = ((accountsSum + 99) / 100) * 100;

    return ListAccountResponse.builder().accounts(accounts).roundedValue(roundedValue).build();
  }

  private static boolean isCheckingOrSavings(AccountDao account) {
    return account.type.equals("CHECKING") || account.type.equals("SAVINGS");
  }
}
