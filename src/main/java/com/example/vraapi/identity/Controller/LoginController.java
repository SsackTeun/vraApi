package com.example.vraapi.identity.Controller;

import ch.qos.logback.classic.pattern.MessageConverter;
import com.example.vraapi.identity.Schemas.LoginRequest;
import com.example.vraapi.identity.Schemas.Token;
import com.example.vraapi.util.MapToMultiValueMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Mono;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class LoginController {

    private final WebClient webClient;

    //schema Object 를 Map으로 변환
    private ObjectMapper objectMapper;
    private Map<String, String> parameters;

    public LoginController(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView mav){
        mav.setViewName("login");
        return mav;
    }

    @PostMapping(value = "/login")
    public ModelAndView loginAttempt(ModelAndView mav, @RequestBody LoginRequest loginRequest){
        // LoginRequest Schema (Object to Map)
        objectMapper = new ObjectMapper();
        parameters = new HashMap<String, String>();
        parameters = objectMapper.convertValue(loginRequest, Map.class);

        Mono<Token> token = webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/csp/gateway/am/api/login")
                                .build())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(parameters) //body (MultiValueMap 사용시 500 Error)
                .retrieve()
                .bodyToMono(Token.class);

        token.subscribe(response ->{
            System.out.println("response : " + response);
        }, e->{
            System.out.println("error " + e.getMessage());
        });

        return mav;
    }

}
