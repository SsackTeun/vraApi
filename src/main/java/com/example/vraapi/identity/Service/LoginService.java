package com.example.vraapi.identity.Service;

import com.example.vraapi.identity.Schemas.LoginRequest;
import com.example.vraapi.identity.Schemas.Token;
import com.example.vraapi.util.APIUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {

    private WebClient webClient;
    private Map<String, String> parameters;
    private ObjectMapper objectMapper;

    @Autowired
    LoginService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Token> basicLogin(LoginRequest loginRequest){
        objectMapper = new ObjectMapper();
        parameters = new HashMap<>();
        parameters = objectMapper.convertValue(loginRequest, Map.class);

        Mono<Token> token = new APIUtil<Token>().post(loginRequest, "/csp/gateway/am/api/login", Token.class, webClient);

        return token;
    }

}
