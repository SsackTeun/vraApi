package com.example.vraapi.identity.Schemas;

import lombok.Data;

@Data
public class UpdateMemberRolesRequest {
    private UpdateRolesRequestBaseRoleBinding organizationRoles;
    private UpdateServiceRolesRequest[] serviceRoles;
}
