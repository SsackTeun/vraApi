package com.example.vraapi.identity.schemas;

import lombok.Data;

@Data
public class DeleteClientsRequest {
    private String[] clientIdsToDelete;
}
