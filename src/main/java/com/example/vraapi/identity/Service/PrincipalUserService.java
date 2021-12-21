package com.example.vraapi.identity.Service;

import com.example.vraapi.identity.Schemas.AccessToken;
import com.example.vraapi.identity.Schemas.Organizations;
import com.example.vraapi.identity.Schemas.Token;
import com.example.vraapi.util.APIUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
@RequiredArgsConstructor
public class PrincipalUserService {
    private final WebClient webClient;

    //조직 정보
    public ResponseEntity<Organizations> organizations(String accessToken){
        new APIUtil<Organizations>(webClient, accessToken).
    }
}
