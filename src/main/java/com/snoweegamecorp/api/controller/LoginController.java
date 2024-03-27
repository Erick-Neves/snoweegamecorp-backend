package com.snoweegamecorp.api.controller;

import com.snoweegamecorp.api.dto.LoginDTO;
import com.snoweegamecorp.api.dto.SessionDTO;
import com.snoweegamecorp.api.model.User;
import com.snoweegamecorp.api.repository.UserRepository;
import com.snoweegamecorp.api.security.SecurityConfig;
import com.snoweegamecorp.api.security.jwt.JWTCreator;
import com.snoweegamecorp.api.security.jwt.JWTObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;

@RestController
public class LoginController {
    @Autowired
    private PasswordEncoder encoder; // Password encoder for encrypting and decrypting passwords
    @Autowired
    private SecurityConfig securityConfig; // Configuration for security settings
    @Autowired
    private UserRepository repository; // Repository for user data

    /**
     * Handles the login request and returns a session token if successful.
     *
     * @param loginDTO The login data transfer object containing the username and password.
     * @return The session data transfer object containing the login username and session token.
     * @throws RuntimeException If the login fails or the password is invalid.
     */
    @PostMapping("/login")
    public SessionDTO logar(@RequestBody LoginDTO loginDTO){
        User user = repository.findByUsername(loginDTO.getUsername()); // Find the user by username
        if(user!=null) {
            boolean passwordOk =  encoder.matches(loginDTO.getPassword(), user.getPassword()); // Check if the password matches
            if (!passwordOk) {
                throw new RuntimeException("Senha inválida para o login: " + loginDTO.getUsername()); // Throw an exception if the password is invalid
            }
            // Estamos enviando um objeto Sessão para retornar mais informações do usuário
            SessionDTO sessao = new SessionDTO();
            sessao.setLogin(user.getUsername()); // Set the login username

            JWTObject jwtObject = new JWTObject();
            jwtObject.setIssuedAt(new Date(System.currentTimeMillis())); // Set the issued at date
            jwtObject.setExpiration((new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION))); // Set the expiration date
            jwtObject.setRoles(user.getRoles()); // Set the user roles
            sessao.setToken(JWTCreator.createToken(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject)); // Create a JWT token and set it in the session
            return sessao; // Return the session data transfer object
        }else {
            throw new RuntimeException("Erro ao tentar fazer login"); // Throw an exception if the login fails
        }
    }
}