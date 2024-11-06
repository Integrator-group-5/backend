package com.luxury.wear.service.service.auth;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Log4j2
@Service
public class TokenBlacklistServiceImpl implements ITokenBlacklistService{

    private final Set<String> blacklist = new HashSet<>();


    @Override
    public void addTokenToBlacklist(String token) {
        log.info("Token added to blacklist: {}",token);
        blacklist.add(token);
    }

}