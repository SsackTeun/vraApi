package com.example.vraapi.identity.Controller;

import com.example.vraapi.identity.Schemas.*;
import com.example.vraapi.identity.Service.PrincipalUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

@Slf4j
@RequestMapping("/loggedin/user")
@RestController
public class PrincipalUserController {

    private PrincipalUserService principalUserService;

    @Autowired
    PrincipalUserController(PrincipalUserService principalUserService){
        this.principalUserService = principalUserService;
    }
    /*
       URI : /csp/gateway/am/api/loggedin/user/orgsId
       required : header -> HttpHeader.Authorization , AccessToken
       */
    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> profile(@CookieValue("token") String accessToken){
        ResponseEntity<UserProfileResponse> profile = principalUserService.userProfile(accessToken);
        log.info(profile.getBody().toString());
        return profile;
    }


    /*
    URI : /csp/gateway/am/api/loggedin/user/orgsId
    required : header -> HttpHeader.Authorization , AccessToken
    */
    @GetMapping("/orgs")
    public ResponseEntity<Organizations> organizationRefLink(@CookieValue("token") String accessToken){
        ResponseEntity<Organizations> organizationRefLink = principalUserService.organizationsRefLink(accessToken);
        log.info(organizationRefLink.getBody().toString());
        return organizationRefLink;
    }

    /*
    URI : /csp/gateway/am/api/loggedin/user/orgs/{orgId}
    required : header -> HttpHeader.Authorization , AccessToken
    */
    @GetMapping("/orgs/orgId")
    public ResponseEntity<OrganizationResponse> organizationOrgId(@CookieValue("token") String accessToken){
        ResponseEntity<OrganizationResponse> organizationsOrgId = principalUserService.organizationsDetail(accessToken);
        log.info(organizationsOrgId.getBody().toString());
        return organizationsOrgId;
    }

    /*
    URI : /csp/gateway/am/api/loggedin/user/orgs/{orgId}/roles
    required : header -> HttpHeader.Authorization , AccessToken
    */
    @GetMapping("/orgs/orgId/roles")
    public ResponseEntity<RoleResponse[]> organizationRoles(@CookieValue("token") String accessToken){
        //
        ResponseEntity<RoleResponse[]> organizationRoles = principalUserService.userRole(accessToken);

        /*RoleResponse[] 출력 */
        RoleResponse[] roles = organizationRoles.getBody();
        Arrays.stream(roles).forEach(e -> {
            log.info(e.toString());
        });

        return organizationRoles;
    }

    /*
    URI : /csp/gateway/am/api/loggedin/user/orgs/{orgId}/services-roles
    required : header -> HttpHeader.Authorization , AccessToken
    */
    @GetMapping("/orgs/orgId/service-roles")
    public ResponseEntity<UserServiceRoles> organizationServiceRole(@CookieValue("token") String accessToken){
        ResponseEntity<UserServiceRoles> organizationServiceRole = principalUserService.serviceRoles(accessToken);
        log.info(organizationServiceRole.getBody().toString());
        return organizationServiceRole;
    }

    /*
    URI : /csp/gateway/am/api/loggedin/user/default-org
    required : header -> HttpHeader.Authorization , AccessToken
    */
    @GetMapping("/default-org")
    public ResponseEntity<RefLink> organizationDefault(@CookieValue("token") String accessToken){
        ResponseEntity<RefLink> organizationDefault = principalUserService.default_org(accessToken);
        log.info(organizationDefault.getBody().toString());
        return organizationDefault;
    }

    /*
    URI : /csp/gateway/am/api/loggedin/user/details
    required : header -> HttpHeader.Authorization , AccessToken
    */
    @GetMapping("/details")
    public ResponseEntity<UserDetailsResponse> userDetails(@CookieValue("token") String accessToken){
        ResponseEntity<UserDetailsResponse> userDetails = principalUserService.userDetails(accessToken);
        log.info(userDetails.getBody().toString());
        return userDetails;
    }

    /*
    URI : /csp/gateway/am/api/loggedin/user/orgs/{orgId}/info
    required : header -> HttpHeader.Authorization , AccessToken
    */
    @GetMapping("/orgs/orgId/info")
    public ResponseEntity<UserInfo> orgInfo(@CookieValue("token") String accessToken){
        ResponseEntity<UserInfo> orgInfo = principalUserService.orgInfo(accessToken);
        log.info(orgInfo.getBody().toString());
        return orgInfo;
    }
}
