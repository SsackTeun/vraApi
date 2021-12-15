package com.example.vraapi.identity.Schemas;

import lombok.Data;

@Data
public class IdpUserDetails {
    private String address;
    private String city;
    private String company;
    private String country;
    private String customerNumber;
    private String workPhone;
    private String zipCode;
    private String state;
}
