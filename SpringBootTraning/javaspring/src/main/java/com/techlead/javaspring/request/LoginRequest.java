package com.techlead.javaspring.request;

import lombok.*;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    private String email;
    private String password;
}