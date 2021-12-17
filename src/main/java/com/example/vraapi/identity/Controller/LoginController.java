package com.example.vraapi.identity.Controller;

import com.example.vraapi.identity.Schemas.AccessToken;
import com.example.vraapi.identity.Schemas.LoginRequest;
import com.example.vraapi.identity.Schemas.Token;
import com.example.vraapi.identity.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@Controller
public class LoginController {

    //schema Object 를 Map으로 변환
    private LoginService loginService;
    private HashMap<String, String> additionalParameters;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public ModelAndView loginPage(ModelAndView mav){
        mav.setViewName("login");
        return mav;
    }

    @PostMapping(value = "/login")
    public ModelAndView basicLogin(ModelAndView mav, @RequestBody LoginRequest loginRequest){
        Mono<Token> token = loginService.basicLogin(loginRequest);
        token.subscribe(response -> {
            System.out.println("response : " + response);
        }, e-> {
            System.out.println("error : " + e.getMessage());
        });
        return mav;
    }

    @PostMapping(value = "/enhancedLogin")
    public ModelAndView enhancedLogin(ModelAndView mav, @RequestBody LoginRequest loginRequest){
        Mono<Token> token = loginService.basicLogin(loginRequest);
        additionalParameters = new HashMap<>();
        token.subscribe(response -> {
            System.out.println("response : " + response);
            additionalParameters.put("access_token", response.getCspAuthToken());
            System.out.println("token : " + additionalParameters.get("access_token"));

            Mono<AccessToken> accessToken = loginService.enhanceLogin(loginRequest, additionalParameters);
            accessToken.subscribe(response1 -> {
                System.out.println("response1 : " + response1);
            }, e-> {
                System.out.println("error : " + e.getMessage());
            });

        }, e-> {
            System.out.println("error : " + e.getMessage());
        });



        return mav;
    }
}
