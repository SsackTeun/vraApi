package com.example.vraapi.identity.Service;

import com.example.vraapi.identity.Schemas.AccessToken;
import com.example.vraapi.identity.Schemas.LoginRequest;
import com.example.vraapi.identity.Schemas.Token;
import com.example.vraapi.util.APIUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@RequiredArgsConstructor
@Service
public class LoginService {
    //new 로 생성하는 객체 인스턴스는
    private final WebClient webClient;

    public Mono<ResponseEntity<Token>> basicLogin(LoginRequest loginRequest){
        Mono<ResponseEntity<Token>> token = new APIUtil(webClient).post(loginRequest, "/csp/gateway/am/api/login", Token.class);
        return token;
    }

    public Mono<Token> enhanceLogin(LoginRequest loginRequest, HashMap<String, String> additionalParameters){
        Mono<Token> accessToken = new APIUtil<AccessToken>(webClient).post(loginRequest, "/csp/gateway/am/idp/auth/login", Token.class, additionalParameters);
        return accessToken;
    }

    public Mono<AccessToken> oAuthLogin(LoginRequest loginRequest){
        return null;
    }
}
