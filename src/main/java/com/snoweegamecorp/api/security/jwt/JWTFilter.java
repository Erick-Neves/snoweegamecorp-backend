package com.snoweegamecorp.api.security.jwt;

import com.snoweegamecorp.api.security.SecurityConfig;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JWTFilter class to handle JWT token authentication and authorization.
 */
public class JWTFilter extends OncePerRequestFilter {
    /**
     * Perform the JWT token authentication and authorization.
     * @param request the HTTP servlet request
     * @param response the HTTP servlet response
     * @param filterChain the filter chain
     * @throws ServletException if a servlet exception occurs
     * @throws IOException if an input or output exception occurs
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(JWTCreator.HEADER_AUTHORIZATION);
        try {
            if (token!=null && !token.isEmpty()){
                JWTObject tokenObject = JWTCreator.create(
                        token,
                        SecurityConfig.PREFIX,
                        SecurityConfig.KEY);
                List<SimpleGrantedAuthority> authorities = authorities(tokenObject.getRoles());
                UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(
                        tokenObject.getSubject(),
                        null,
                        authorities);
                SecurityContextHolder.getContext().setAuthentication(userToken);
            } else {
                SecurityContextHolder.clearContext();
            }
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException e){
            e.printStackTrace();
            response.setStatus(HttpStatus.FORBIDDEN.value());
        }
    }
    /**
     * Get the authorities based on the roles.
     * @param roles the roles
     * @return the list of simple granted authorities
     */
    private List<SimpleGrantedAuthority> authorities(List<String> roles){
        return roles.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
