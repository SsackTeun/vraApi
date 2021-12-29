package com.example.vraapi.identity.service;

import com.example.vraapi.identity.schemas.AccessToken;
import com.example.vraapi.identity.schemas.LoginRequest;
import com.example.vraapi.util.APIUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;

@RequiredArgsConstructor
@Service
public class LoginService {
    //new 로 생성하는 객체 인스턴스는
    private final WebClient webClient;

    //LoginController
    public ResponseEntity<AccessToken> basicLogin(LoginRequest loginRequest){
        APIUtil<AccessToken> api = new APIUtil<>(webClient);
        return api.post(loginRequest, URI.create("/csp/gateway/am/api/login?access_token"), AccessToken.class);
    }

    /*public Mono<Token> enhanceLogin(LoginRequest loginRequest, HashMap<String, String> additionalParameters){
        APIUtil<AccessToken> api = new APIUtil<>(webClient);
        return api.post(loginRequest, URI.create("/csp/gateway/am/idp/auth/login"), Token.class, additionalParameters);
    }*/

    public Mono<AccessToken> oAuthLogin(LoginRequest loginRequest){
        return null;
    }
}
