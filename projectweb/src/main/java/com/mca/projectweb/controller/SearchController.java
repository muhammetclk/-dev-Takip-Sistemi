package com.mca.projectweb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mca.projectweb.configuration.CurrentUser;
import com.mca.projectweb.dto.AuthCredentialsDTO;

import com.mca.projectweb.dto.UserDTO;
import com.mca.projectweb.entity.User;
import com.mca.projectweb.response.AuthResponse;
import com.mca.projectweb.service.SearchService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SearchController {

    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
    @Autowired
    SearchService searchService;

    //arama ekranında aratılan ifade buraya gelir.
    @GetMapping("/api/v1/search/")
    public List<UserDTO> getTeachersWithSearch(@RequestParam(name = "search") String search )  {
        logger.info("getTeachersWithSearch controller methodu search parametresiyle çağrıldı: {}", search);

        List<UserDTO> result = searchService.getTeachersWithSearch(search);

        logger.info("getTeachersWithSearch cevap: {}", result);
        return result;


    }
}
