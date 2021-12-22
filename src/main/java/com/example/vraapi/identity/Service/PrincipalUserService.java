package com.example.vraapi.identity.Service;

import com.example.vraapi.identity.Schemas.*;
import com.example.vraapi.util.APIUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

@Service
@Slf4j
public class PrincipalUserService {
    private final WebClient webClient;
    private String accessToken;

    public PrincipalUserService(WebClient webClient){
        this.webClient = webClient;
    }
    /*
     * URI : /csp/gateway/am/api/loggedin/user/profile
     * DO :
     * UserProfileResponse(
     * name=vidm,
     * displayName=VIDM,
     * metadata={isMsp=true},
     * id=294b6b99-140c-405b-ba23-570936ba8a2d,
     * refLink=/csp/gateway/am/api/orgs/294b6b99-140c-405b-ba23-570936ba8a2d,
     * parentRefLink=null)
     * */
    public ResponseEntity<UserProfileResponse> userProfile(String accessToken){
        APIUtil<UserProfileResponse> userProfile = new APIUtil<>(webClient, accessToken);
        return userProfile.get(null, URI.create("/csp/gateway/am/api/loggedin/user/profile"), UserProfileResponse.class);
    }

    /*
    * URI : /csp/gateway/am/api/loggedin/user/orgs
    * DO : accessToken 정보에 맞는 사용자의 orgId가 포함된 refLink를 반환받음
    * Organizations 을 리턴
    * {
         "refLinks": [
          "/csp/gateway/am/api/orgs/294b6b99-140c-405b-ba23-570936ba8a2d"
          ],
          "items": null
       }
    * */
    public ResponseEntity<Organizations> organizationsRefLink(String accessToken){
        APIUtil<Organizations> api = new APIUtil<>(webClient, accessToken);
        return api.get(null, URI.create("/csp/gateway/am/api/loggedin/user/orgs"), Organizations.class);
    }

    /*
     * URI : /csp/gateway/am/api/loggedin/user/orgs/{orgId}
     * DO : orgId로 orgId에 해당하는 유저의 정보를 얻음
     * OrganizationResponse 리턴
     * {
        "name": "vidm",
        "displayName": "VIDM",
        "metadata": {
            "isMsp": "true"
        },
        "id": "294b6b99-140c-405b-ba23-570936ba8a2d",
        "refLink": "/csp/gateway/am/api/orgs/294b6b99-140c-405b-ba23-570936ba8a2d",
        "parentRefLink": null
       }
     * */
    public ResponseEntity<OrganizationResponse> organizationsDetail(String accessToken){
        ///csp/gateway/am/api/loggedin/user/orgs -> orgId 추출
        String orgRefLink = organizationsRefLink(accessToken).getBody().getRefLinks()[0];

        ///csp/gateway/am/api/loggedin/user/orgs/{orgId} <- orgId 파라미터로 주입
        APIUtil<OrganizationResponse> OrganizationResponse = new APIUtil<>(webClient, accessToken);
        return OrganizationResponse.get(null, URI.create(orgRefLink), OrganizationResponse.class);
    }

    /*
     * URI : /csp/gateway/am/api/loggedin/user/orgs/{orgId}/roles
     * DO :
     * RoleResponse 리턴
     * [
         * {"refLink":"/csp/gateway/am/api/orgs/294b6b99-140c-405b-ba23-570936ba8a2d/roles/fbef8b1b-5038-4fbf-bb05-edc8177f7e1a",
         * "name":"org_owner",
         * "displayName":"Organization Owner",
         * "organizationLink":"/csp/gateway/am/api/orgs/294b6b99-140c-405b-ba23-570936ba8a2d","visible":true}
     * ]
     * */
    public ResponseEntity<RoleResponse[]> userRole(String accessToken){
        String orgId = organizationsDetail(accessToken).getBody().getId();
        APIUtil<RoleResponse[]> userProfile = new APIUtil<>(webClient, accessToken);
        return userProfile.get(null, URI.create("/csp/gateway/am/api/loggedin/user/orgs/" + orgId + "/roles"), RoleResponse[].class);
    }

     /*
     * URI : /csp/gateway/am/api/loggedin/user/orgs/{orgId}/service-roles
     * DO : service-role 리턴
     * UserServiceRoles(
     * {"serviceRoles":[
     *  {"serviceDefinitionLink":"/csp/gateway/slc/api/definitions/external/632e462a-73d7-4163-a9fa-f69eaecc088f",
     *      "serviceRoleNames":["migration:admin"],
     *       "serviceRoles":[
     *              {"name":"migration:admin",
     *               "membershipType":"DIRECT",
     *                "resource":null}
     *        ]
     *  },
     * ...
     * }
     * )
     */
    public ResponseEntity<UserServiceRoles> serviceRoles(String accessToken){
        String orgId = organizationsDetail(accessToken).getBody().getId();
        APIUtil<UserServiceRoles> userProfile = new APIUtil<>(webClient, accessToken);
        return userProfile.get(null, URI.create("/csp/gateway/am/api/loggedin/user/orgs/" + orgId + "/service-roles"), UserServiceRoles.class);
    }

    /*
     * URI : /csp/gateway/am/api/loggedin/user/default-org
     * DO : RefLink 리턴
     * RefLink(
     *  {"refLink":"/csp/gateway/am/api/orgs/294b6b99-140c-405b-ba23-570936ba8a2d"}
     * )
     */
    public ResponseEntity<RefLink> default_org(String accessToken){
        String orgId = organizationsDetail(accessToken).getBody().getId();
        APIUtil<RefLink> userProfile = new APIUtil<>(webClient, accessToken);
        return userProfile.get(null, URI.create("/csp/gateway/am/api/loggedin/user/default-org"), RefLink.class);
    }
}
