package com.ddfinv.backend.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterAuthRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
