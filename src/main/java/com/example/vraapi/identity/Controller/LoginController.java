package com.example.vraapi.identity.Controller;

import com.example.vraapi.identity.Schemas.LoginRequest;
import com.example.vraapi.identity.Schemas.Token;
import com.example.vraapi.identity.Service.LoginService;
import com.example.vraapi.util.CookieUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Mono;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        //Map
        Map<String, String> data = new HashMap<>();

        //로그인 결과
        Mono<ResponseEntity<Token>> result = loginService.basicLogin(loginRequest);
        ResponseEntity<Token> token = result.block();

        //성공
        if(token.getStatusCode().equals(HttpStatus.OK)){
            logger.info(token.getBody().getCspAuthToken());
            logger.info(String.valueOf(token.getStatusCodeValue()));
        }
        else{

        }

        //data 에 값 담기
        data.put("status", "success");
        data.put("link", "http://" + Inet4Address.getLocalHost().getHostAddress()+":8001" + "/consumer");

        //쿠키에 토큰 저장
        CookieUtil cookieUtil = new CookieUtil();
        //response.addCookie(cookieUtil.addCookie("token", token.getCspAuthToken()).setHttpOnly(true).setExpire(60*60*8).build());

        //logger
        logger.info("data : " + data);

        return data;
     }

    /*@ResponseBody
    @PostMapping(value = "/enhancedLogin")
    public ModelAndView enhancedLogin(ModelAndView mav, @RequestBody LoginRequest loginRequest){
        Mono<Token> token = loginService.basicLogin(loginRequest);
        System.out.println("token : " + token.block().getCspAuthToken());
        additionalParameters = new HashMap<>();
        additionalParameters.put("access_token", token.block().getCspAuthToken()); //block 동기 통신 토큰값 받기전에 다음 API 호출금지

        Mono<Token> accessToken = loginService.enhanceLogin(loginRequest, additionalParameters);
        accessToken.subscribe(response1 -> {
            System.out.println("response1 : " + response1);
        }, e-> {
            System.out.println("error : " + e.getMessage());
        });
        return mav;
    }*/
}
