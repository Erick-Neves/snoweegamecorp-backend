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
    private PasswordEncoder encoder;
    @Autowired
    private SecurityConfig securityConfig;
    @Autowired
    private UserRepository repository;
    @PostMapping("/login")
    public SessionDTO logar(@RequestBody LoginDTO loginDTO){
        User user = repository.findByUsername(loginDTO.getUsername());
        if(user!=null) {
            boolean passwordOk =  encoder.matches(loginDTO.getPassword(), user.getPassword());
            if (!passwordOk) {
                throw new RuntimeException("Senha inválida para o login: " + loginDTO.getUsername());
            }
            //Estamos enviando um objeto Sessão para retornar mais informações do usuário
            SessionDTO sessao = new SessionDTO();
            sessao.setLogin(user.getUsername());

            JWTObject jwtObject = new JWTObject();
            jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
            jwtObject.setExpiration((new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION)));
            jwtObject.setRoles(user.getRoles());
            sessao.setToken(JWTCreator.createToken(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));
            return sessao;
        }else {
            throw new RuntimeException("Erro ao tentar fazer login");
        }
    }
}
