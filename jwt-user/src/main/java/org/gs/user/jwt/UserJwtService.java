package org.gs.user.jwt;

import io.smallrye.jwt.build.Jwt;

import javax.inject.Singleton;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Singleton
public class UserJwtService {
    public String generateJwt() {

        Set<String> roles = new HashSet<>(
                Arrays.asList("user", "moderator", "admin")
        );
        long duration =  System.currentTimeMillis() + 3600;
        return Jwt.issuer("user-jwt")
                .subject("user-jwt")
                .groups(roles)
                .expiresAt(duration)
                .sign();
    }
}
