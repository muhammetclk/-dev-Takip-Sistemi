package com.mca.projectweb.controller;


import com.mca.projectweb.configuration.CurrentUser;
import com.mca.projectweb.dto.UserDTO;
import com.mca.projectweb.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    //Arama ekranında bulunan öğretmenin card bilgileri gelir.
    @GetMapping("api/v1/teacherProfile/{teacher}")
    UserDTO getTeacherWithSearch(@PathVariable String teacher){
        logger.info("getTeacherWithSearch controller methoduna girdi, teacher: {}", teacher);
        UserDTO response = userService.getTeacherProfile(teacher);
        logger.info("getTeacherWithSearch controller methodundan dönen cevap: {}", response);
        return response;
    }

    //login olan kullanıcının profil bilgisi gelir.
    @GetMapping("api/v1/user/profile")
    UserDTO getUsersById(@AuthenticationPrincipal CurrentUser currentUser){
        logger.info("getUsersById controller methoduna girdi, id: {}", currentUser.getId());
        UserDTO response = userService.getUser(currentUser.getId());
        logger.info("getUsersById controller methodundan dönen cevap: {}", response);
        return response;
    }
}

