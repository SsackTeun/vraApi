package com.example.vraapi.identity.Schemas;

import lombok.Data;

@Data
public class DeleteClientsRequest {
    private String[] clientIdsToDelete;
}
