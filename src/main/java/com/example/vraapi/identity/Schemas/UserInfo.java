package com.example.vraapi.identity.Schemas;

import lombok.Data;

@Data
public class UserInfo {
    private User user;
    private UserOrgInfo[] userOrgInfo;
}
