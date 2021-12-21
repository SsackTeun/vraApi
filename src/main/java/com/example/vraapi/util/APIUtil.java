package com.example.vraapi.util;

import com.example.vraapi.identity.Schemas.AccessToken;
import com.example.vraapi.identity.Schemas.Token;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class APIUtil<T> {

    private ObjectMapper objectMapper;
    private Map parameters;
    private final WebClient webClient;
    private String token;

    public APIUtil(WebClient webClient) {
        this.webClient = webClient;
    }

    public APIUtil(WebClient webClient, String token){
        this.webClient = webClient;
        this.token = token;
    }

    public ResponseEntity<T> post(Object obj, URI uri, Class<T> type){
        objectMapper = new ObjectMapper();
        parameters = new HashMap<String, String>();
        parameters = objectMapper.convertValue(obj, Map.class);

        ResponseEntity<T> result;

        if(token != null){
            result = (ResponseEntity<T>) webClient.post()
                    .uri(uriBuilder -> uriBuilder.path(uri.getPath()).query(uri.getQuery())
                            .build())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, token.getAccess_token())
                    .bodyValue(parameters) //body (MultiValueMap 사용시 500 Error)
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError(),
                            response -> Mono.error(new Exception("error : " + response.statusCode())))
                    .toEntity(type)
                    .block();
        }else{
            result = (ResponseEntity<T>) webClient.post()
                    .uri(uriBuilder -> uriBuilder.path(uri.getPath()).query(uri.getQuery())
                            .build())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .bodyValue(parameters) //body (MultiValueMap 사용시 500 Error)
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError(),
                            response -> Mono.error(new Exception("error : " + response.statusCode())))
                    .toEntity(type)
                    .block();
        }
        return result;
    }

    public ResponseEntity<T> get(Object obj, URI uri, Class<T> type){
        objectMapper = new ObjectMapper();
        parameters = new HashMap<String, String>();
        parameters = objectMapper.convertValue(obj, Map.class);

        ResponseEntity<T> result;

        result = (ResponseEntity<T>) webClient.get()
                .uri(uriBuilder -> uriBuilder.
                        path(uri.getPath()).
                        query(uri.getQuery()).
                        build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(),
                    response -> Mono.error(new Exception("error : " + response.statusCode())))
                .toEntity(type)
                .block();

        return result;
    }

    /*public Mono post(Object obj, String URL, Class<com.example.vraapi.identity.Schemas.Token> toValueType, HashMap<String, String> additionalParameters){
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
    }*/
}
