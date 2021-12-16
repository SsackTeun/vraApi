package com.example.vraapi.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class APIUtil<T> {

    private static ObjectMapper objectMapper;
    private static Map parameters;

    public Mono<T> post(Object obj, String URL, Class toValueType , WebClient webClient){
        objectMapper = new ObjectMapper();
        parameters = new HashMap<String, String>();
        parameters = objectMapper.convertValue(obj, Map.class);

        Mono<T> t = webClient.post()
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
