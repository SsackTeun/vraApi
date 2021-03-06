package com.example.vraapi.identity.controller;

import com.example.vraapi.identity.schemas.AccessToken;
import com.example.vraapi.identity.schemas.LoginRequest;
import com.example.vraapi.identity.service.LoginService;
import com.example.vraapi.util.CookieUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    //schema Object 를 Map으로 변환
    private LoginService loginService;
    private HashMap<String, String> additionalParameters;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/")
    public ModelAndView loginPage(ModelAndView mav){
        mav.setViewName("login");
        return mav;
    }

    @GetMapping("/consumer")
    public ModelAndView consumerPage(ModelAndView mav){
        mav.setViewName("consumer");
        return mav;
    }

    @PostMapping(value = "/login")
    public Map<String, String> basicLogin(@RequestBody LoginRequest loginRequest,
                                          HttpServletResponse response) throws UnknownHostException {
        //Token variable
        AccessToken token = null;

        //Map
        Map<String, String> data = new HashMap<>();

        //로그인 결과
        ResponseEntity<AccessToken> result = loginService.basicLogin(loginRequest);

        //성공
        if(result.getStatusCode().equals(HttpStatus.OK)){
            logger.info("token : " + result.getBody());
            token = result.getBody();

            //data 에 값 담기
            data.put("status", "success");
            data.put("link", "http://localhost" +":8001" + "/consumer");

            //쿠키에 토큰 저장
            CookieUtil cookieUtil = new CookieUtil();
            response.addCookie(cookieUtil.addCookie("token", token.getAccess_token()).setHttpOnly(true).setExpire(60*60*8).build());
        }
        else{
            token = null;
        }
        //logger
        logger.info("data : " + data);
        return data;
     }
}
