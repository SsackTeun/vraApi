package com.example.vraapi.identity.schemas;

import lombok.Data;

@Data
public class UpdateMemberRolesRequest {
    private UpdateRolesRequestBaseRoleBinding organizationRoles;
    private UpdateServiceRolesRequest[] serviceRoles;
}
