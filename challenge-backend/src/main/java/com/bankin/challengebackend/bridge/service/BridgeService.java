package com.bankin.challengebackend.bridge.service;

import com.bankin.challengebackend.bridge.entity.ListAccountsResponse;
import com.bankin.challengebackend.bridge.repository.BridgeRepository;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Responsible for all bridge api calls */
@Service
@RequiredArgsConstructor
public class BridgeService {

  private final BridgeRepository repository;

  /**
   * I usually do a service that transforms dao from the repo into dto but I didnt get the time
   * since I was stuck looking for those client id and secret
   *
   * @return a {@link ListAccountsResponse}
   * @throws IOException should receive a dedicated exception, and do something better about it, but
   *     no time
   */
  public ListAccountsResponse fetchAccounts() throws IOException {
    return repository.fetchAccounts();
  }
}
