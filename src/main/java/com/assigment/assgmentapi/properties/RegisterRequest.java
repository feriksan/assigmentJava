package com.assigment.assgmentapi.properties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String Email;
    private String FirstName;
    private String LastName;
    private String Password;
    private String Role;
}
