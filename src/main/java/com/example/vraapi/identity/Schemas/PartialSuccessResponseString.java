package com.example.vraapi.identity.Schemas;

import lombok.Data;

@Data
public class PartialSuccessResponseString {
    private String[] succeeded;
    private String[] failed;
}
