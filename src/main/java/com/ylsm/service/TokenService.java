package com.ylsm.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class TokenService  extends AbstractApiRequestService implements CommandLineRunner {

    @Value("${docking.account}")
    private String account;

    @Value("${docking.password}")
    private String password;

    private static String token;

    @Override
    public void run(String... args) throws Exception {

    }
}
