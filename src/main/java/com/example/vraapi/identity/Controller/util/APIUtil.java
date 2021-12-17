package com.example.vraapi.identity.Controller.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
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

    public Mono post(Object obj, String URL, Class toValueType){
        objectMapper = new ObjectMapper();
        parameters = new HashMap<String, String>();
        parameters = objectMapper.convertValue(obj, Map.class);

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
