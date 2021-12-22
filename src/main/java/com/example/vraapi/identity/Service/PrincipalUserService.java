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
        APIUtil<Organizations> organizationsRefLink = new APIUtil<>(webClient, accessToken);
        return organizationsRefLink.get(null, URI.create("/csp/gateway/am/api/loggedin/user/orgs"), Organizations.class);
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
        APIUtil<OrganizationResponse> organizationsDetail = new APIUtil<>(webClient, accessToken);
        return organizationsDetail.get(null, URI.create(orgRefLink), OrganizationResponse.class);
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
        APIUtil<RoleResponse[]> userRole = new APIUtil<>(webClient, accessToken);
        return userRole.get(null, URI.create("/csp/gateway/am/api/loggedin/user/orgs/" + orgId + "/roles"), RoleResponse[].class);
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
        APIUtil<UserServiceRoles> serviceRoles = new APIUtil<>(webClient, accessToken);
        return serviceRoles.get(null, URI.create("/csp/gateway/am/api/loggedin/user/orgs/" + orgId + "/service-roles"), UserServiceRoles.class);
    }

    /*
     * URI : /csp/gateway/am/api/loggedin/user/default-org
     * DO : RefLink 리턴
     * RefLink(
     *  {"refLink":"/csp/gateway/am/api/orgs/294b6b99-140c-405b-ba23-570936ba8a2d"}
     * )
     */
    public ResponseEntity<RefLink> default_org(String accessToken){
        APIUtil<RefLink> default_org = new APIUtil<>(webClient, accessToken);
        return default_org.get(null, URI.create("/csp/gateway/am/api/loggedin/user/default-org"), RefLink.class);
    }

    /*
     * URI : /csp/gateway/am/api/loggedin/user/details
     * DO : RefLink 리턴
     * RefLink(
     *  {"refLink":"/csp/gateway/am/api/orgs/294b6b99-140c-405b-ba23-570936ba8a2d"}
     * )
     */
    public ResponseEntity<UserDetailsResponse> userDetails(String accessToken){
        APIUtil<UserDetailsResponse> userProfile = new APIUtil<>(webClient, accessToken);
        return userProfile.get(null, URI.create("/csp/gateway/am/api/loggedin/user/details"), UserDetailsResponse.class);
    }

    /*
     * URI : /csp/gateway/am/api/loggedin/user/orgs/{orgId}/info
     * DO :  UserInfo 리턴
     * UserInfo(
     *  {
          "user": {
            "id": "5e7d8164-389a-47b4-b626-abe6d1f1b440",
            "firstName": "jaeyoung",
            "lastName": "bae",
            "username": "jy",
            "acct": "jy",
            "password": null,
            "email": "jy@onware.co.kr",
            "refLink": "/csp/gateway/am/api/users/jy",
            "groups": [
              "4b837d9a-a09a-4589-b8d2-fa500af22e0c",
              "105f449f-bf6b-466f-9eca-54f053e3eb84"
            ],
            "userProfile": null,
            "managerId": null
          },
          "userOrgInfo": [
            {
              "displayName": "VIDM",
              "name": "vidm",
              "orgRoles": [
                {
                  "id": "fbef8b1b-5038-4fbf-bb05-edc8177f7e1a",
                  "createdMillis": 1614243155490,
                  "updatedMillis": 0,
                  "name": "org_owner",
                  "displayName": "Organization Owner",
                  "orgId": "294b6b99-140c-405b-ba23-570936ba8a2d",
                  "visible": true,
                  "organizationLink": "/csp/gateway/am/api/orgs/294b6b99-140c-405b-ba23-570936ba8a2d",
                  "refLink": "/csp/gateway/am/api/orgs/294b6b99-140c-405b-ba23-570936ba8a2d/roles/fbef8b1b-5038-4fbf-bb05-edc8177f7e1a",
                  "userIds": [
                    "e5731edf-31aa-47ae-bdaa-34b417cb3814",
                    "78c139d8-c29a-4e9e-85d2-f036c1581844",
                    "98b4eff1-3962-43d5-a688-396337615254",
                    "210aed8c-43a5-4532-acff-a01f99d18680",
                    "5e7d8164-389a-47b4-b626-abe6d1f1b440",
                    "ba1d551d-0f1c-44b0-b477-31de458fe7e5"
                  ],
                  "groupIds": []
                }
              ],
              "serviceDef": null
            }
          ]
        }
     * )
     */
    public ResponseEntity<UserInfo> orgInfo(String accessToken){
        String orgId = organizationsDetail(accessToken).getBody().getId();
        APIUtil<UserInfo> orgInfo = new APIUtil<>(webClient, accessToken);
        return orgInfo.get(null, URI.create("/csp/gateway/am/api/loggedin/user/orgs/"+ orgId + "/info"), UserInfo.class);
    }
}
