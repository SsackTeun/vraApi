package com.example.vraapi.identity.Service;

import com.example.vraapi.identity.Schemas.AccessToken;
import com.example.vraapi.identity.Schemas.AuthorizationRequest;
import com.example.vraapi.identity.Schemas.LoginRequest;
import com.example.vraapi.identity.Schemas.Token;
import com.example.vraapi.identity.Controller.util.APIUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class LoginService {
    //new 로 생성하는 객체 인스턴스는
    private final WebClient webClient;

    public Mono<Token> basicLogin(LoginRequest loginRequest){
        Mono<Token> token = new APIUtil(webClient).post(loginRequest, "/csp/gateway/am/api/login", Token.class);
        return token;
    }

    public Mono<AccessToken> enhanceLogin(LoginRequest loginRequest, HashMap<String, String> additionalParameters){
        Mono<AccessToken> accessToken = new APIUtil<AccessToken>(webClient).post(loginRequest, "/csp/gateway/am/idp/auth/login", AccessToken.class, additionalParameters);
        return accessToken;
    }

    public Mono<AccessToken> oAuthLogin(LoginRequest loginRequest){
        return null;
    }
}
