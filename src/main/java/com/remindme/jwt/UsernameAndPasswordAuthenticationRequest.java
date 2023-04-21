package com.remindme.jwt;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class UsernameAndPasswordAuthenticationRequest {
    private String username;
    private String password;
}
