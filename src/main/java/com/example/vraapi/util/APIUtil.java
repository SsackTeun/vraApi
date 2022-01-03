package com.example.vraapi.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.*;

@Slf4j
public class APIUtil<T> {

    private ObjectMapper objectMapper;
    private Map requestBody;
    private Map requestParam;
    private final WebClient webClient;
    private String token;

    public APIUtil(WebClient webClient) {
        this.webClient = webClient;
    }

    public APIUtil(WebClient webClient, String token){
        this.webClient = webClient;
        this.token = token;
    }

    public ResponseEntity<T> post(Object body, URI uri, Class<T> type){
        objectMapper = new ObjectMapper();
        requestBody = new HashMap<String, String>();
        requestBody = objectMapper.convertValue(body, Map.class);

        ResponseEntity<T> result;

        if(token != null){
            result = (ResponseEntity<T>) webClient.post()
                    .uri(uriBuilder -> uriBuilder
                            .path(uri.getPath())
                            .query(uri.getQuery())
                            .build())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .bodyValue(requestBody) //body (MultiValueMap 사용시 500 Error)
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
                    .bodyValue(requestBody) //body (MultiValueMap 사용시 500 Error)
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError(),
                            response -> Mono.error(new Exception("error : " + response.statusCode())))
                    .toEntity(type)
                    .block();
        }
        return result;
    }

    public ResponseEntity<T> get(Object param, URI uri, Class<T> type){
        objectMapper = new ObjectMapper();
        Map<String, String> map = objectMapper.convertValue(param, new TypeReference<Map<String, String>>(){});
        MultiValueMap<String, String> onlyNotNullParams = new LinkedMultiValueMap<>();
        Map<String, String> reset = new LinkedHashMap<>();

        map.forEach((k, v) -> {
            if(v == null){
                log.info("null : " + k + ":" + v);
            }
            else{
                log.info("notnull: " + k + ":" + v);
                reset.put(k, v);
            }
            onlyNotNullParams.setAll(reset);
        });

        onlyNotNullParams.forEach((k, v) ->{
            log.info("----------");
            log.info(k + ":" + v);
        });

      /*  onlyNotNullParams.forEach((k, v) -> {
            log.info("key : " + k + " value : " + v);
        });*/

        ResponseEntity<T> result;
        if(token != null){
            result = (ResponseEntity<T>) webClient.get()
                    .uri(uriBuilder -> uriBuilder.
                            path(uri.getPath()).
                            queryParams(onlyNotNullParams).
                            build())
                    .accept(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError(),
                            response -> Mono.error(new Exception("error : " + response.statusCode())))
                    .toEntity(type)
                    .block();
        }else{
            result = (ResponseEntity<T>) webClient.get()
                    .uri(uriBuilder -> uriBuilder.
                            path(uri.getPath()).
                            query(uri.getQuery()).
                            build())
                    .accept(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError(),
                            response -> Mono.error(new Exception("error : " + response.statusCode())))
                    .toEntity(type)
                    .block();
        }


        return result;
    }

    public ResponseEntity<T> put(Object body, URI uri, Class<T> type){
        objectMapper = new ObjectMapper();
        requestBody = new HashMap<String, String>();
        requestBody = objectMapper.convertValue(body, Map.class);

        ResponseEntity<T> result;

        result = webClient
                .put()
                .uri(uriBuilder -> uriBuilder
                            .path(uri.getPath())
                            .query(uri.getQuery())
                            .build()
                )
                .header(HttpHeaders.AUTHORIZATION, token)
                .bodyValue(requestBody)
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
