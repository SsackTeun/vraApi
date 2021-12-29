package com.example.vraapi.identity.schemas;

import lombok.Data;

@Data
public class UserInfo {
    private User user;
    private UserOrgInfo[] userOrgInfo;
}
