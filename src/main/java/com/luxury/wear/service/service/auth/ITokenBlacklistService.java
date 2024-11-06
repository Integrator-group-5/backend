package com.luxury.wear.service.service.auth;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public interface ITokenBlacklistService {

    void addTokenToBlacklist(String token);

}