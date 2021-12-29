package com.example.vraapi.identity.schemas;

import lombok.Data;

@Data
public class PartialSuccessResponseString {
    private String[] succeeded;
    private String[] failed;
}
