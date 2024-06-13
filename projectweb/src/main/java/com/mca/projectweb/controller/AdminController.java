package com.mca.projectweb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mca.projectweb.configuration.CurrentUser;
import com.mca.projectweb.dto.AuthCredentialsDTO;

import com.mca.projectweb.dto.UserCreateDTO;

import com.mca.projectweb.dto.UserDTO;
import com.mca.projectweb.entity.User;
import com.mca.projectweb.response.AuthResponse;
import com.mca.projectweb.service.AdminService;
import com.mca.projectweb.service.SearchService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
    @Autowired
    AdminService adminService;


    @PostMapping("/api/v1/admin/signup")
    public String createUser(@Valid @RequestBody UserCreateDTO userCreateDto){
        System.err.println("admin controllerdayiz");
        adminService.createUser(userCreateDto.toUser());
        return "Kayıt oluşturuldu.";
    }
}
