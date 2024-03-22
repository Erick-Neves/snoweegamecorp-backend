package com.snoweegamecorp.api.security.jwt;

import io.jsonwebtoken.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is responsible for creating and parsing JWT tokens.
 */
public class JWTCreator {

    /**
     * The header for the Authorization token.
     */
    public static final String HEADER_AUTHORIZATION = "Authorization";

    /**
     * The key for the authorities in the JWT claims.
     */
    public static final String ROLES_AUTHORITIES = "Authorities";

    /**
     * Creates a JWT token with the specified prefix, key, and JWTObject.
     *
     * @param prefix the prefix to be appended to the token
     * @param key the key used for signing the token
     * @param jwtObject the JWTObject containing the subject, issuedAt, and expiration
     * @return the created JWT token
     */
    public static final String createToken(String prefix, String key, JWTObject jwtObject) {
        String token = Jwts.builder()
                .setSubject(jwtObject.getSubject())
                .setIssuedAt(jwtObject.getIssuedAt())
                .setExpiration(jwtObject.getExpiration())
                .claim(ROLES_AUTHORITIES, checkRoles(jwtObject.getRoles()))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        return prefix + " " + token;
    }

    /**
     * Creates a JWTObject by parsing the provided token.
     *
     * @param token the JWT token
     * @param prefix the prefix to be removed from the token
     * @param key the key used for signing the token
     * @return the parsed JWTObject
     * @throws ExpiredJwtException if the token has expired
     * @throws UnsupportedJwtException if the token is unsupported
     * @throws MalformedJwtException if the token is malformed
     * @throws SignatureException if the token signature is invalid
     */
    public static JWTObject create(String token, String prefix, String key)
            throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException {
        JWTObject object = new JWTObject();
        token = token.replace(prefix, "");
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        object.setSubject(claims.getSubject());
        object.setExpiration(claims.getExpiration());
        object.setIssuedAt(claims.getIssuedAt());
        object.setRoles((List) claims.get(ROLES_AUTHORITIES));
        return object;
    }

    /**
     * Maps the roles in the provided list to the format "ROLE_{role}".
     *
     * @param roles the list of roles to be checked
     * @return the mapped roles
     */
    public static List<String> checkRoles(List<String> roles) {
        return roles.stream()
                .map(s -> "ROLE_".concat(s.replaceAll("ROLE_", "")))
                .collect(Collectors.toList());
    }
}