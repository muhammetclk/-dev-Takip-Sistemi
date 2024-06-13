package com.mca.projectweb.controller;


import com.mca.projectweb.dto.AuthCredentialsDTO;
import com.mca.projectweb.response.AuthResponse;
import com.mca.projectweb.service.AuthService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    AuthService authService;

    //kullanıcı login işlemleri sırasında istek yaptığı endpoint

    @PostMapping("api/v1/auth")
    AuthResponse handleAuthentication(@Valid @RequestBody AuthCredentialsDTO creds){
        logger.info("handleAuthentication controller methodu creds parametresiyle çağrıldı: {}", creds);

        AuthResponse response = authService.authenticate(creds);
        logger.info("handleAuthentication controller methodundan dönen cevap: {}", response);

        return response;
    }


}
