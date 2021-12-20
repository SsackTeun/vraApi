package com.example.vraapi.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import java.util.HashMap;
import java.util.Map;

public class APIUtil<T> {

    private ObjectMapper objectMapper;
    private Map parameters;
    private final WebClient webClient;

    public APIUtil(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<<ResponseEntity<T> post(Object obj, String URL, Class toValueType){
        objectMapper = new ObjectMapper();
        parameters = new HashMap<String, String>();
        parameters = objectMapper.convertValue(obj, Map.class);

        Mono<T> t = webClient.post()
                .uri(uriBuilder -> uriBuilder.path(URL)
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(parameters) //body (MultiValueMap 사용시 500 Error)
                .exchangeToMono(res -> {
                    if(res.statusCode().equals(HttpStatus.OK)){
                        return res.bodyToMono(toValueType);
                    }
                    else{
                        return res.createException().flatMap(Mono::error);
                    }
                });
        return t;
    }

    public Mono post(Object obj, String URL, Class toValueType, HashMap<String, String> additionalParameters){
        objectMapper = new ObjectMapper();
        parameters = new HashMap<String, String>();

        for(Map.Entry entry : additionalParameters.entrySet()){
            parameters.put(entry.getKey(), entry.getValue());
        }
        parameters = objectMapper.convertValue(obj, Map.class);
        parameters.forEach((k,v ) -> System.out.println(k + ":" + v));
        System.out.println(obj.getClass());

        Mono t = webClient.post()
                .uri(uriBuilder -> uriBuilder.path(URL)
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(parameters) //body (MultiValueMap 사용시 500 Error)
                .retrieve()
                .bodyToMono(toValueType);
        return t;
    }
}
