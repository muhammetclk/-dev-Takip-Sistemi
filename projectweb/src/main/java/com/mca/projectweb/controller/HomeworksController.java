package com.mca.projectweb.controller;
import com.mca.projectweb.configuration.CurrentUser;
import com.mca.projectweb.dto.FileDTO;
import com.mca.projectweb.service.HomeworksService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeworksController {

    private static final Logger logger = LoggerFactory.getLogger(HomeworksController.class);

    @Autowired
    HomeworksService homeworksService;

    //öğrencinin daha önce yüklediği ödevler gelir.
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/api/v1/homeworks")
    public List<FileDTO> gethomeworks(@AuthenticationPrincipal CurrentUser currentUser)  {
        logger.info("gethomeworks controller methoduna girdi, userId: {}", currentUser.getId());

        List<FileDTO> response = homeworksService.getHomeworks( currentUser.getId());
        logger.info("gethomeworks controller methodundan dönen cevap: {}", response);

        return response;
    }

    //öğretmen ilgili ödevle ilgili yüklenen ödevleri görüntüler.
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping("/api/v1/teacher/works/{assignmentId}/submissions")
    public List<FileDTO> getSubmissions(@PathVariable Long assignmentId )  {
        logger.info("getSubmissions controller methoduna girdi, assignmentId: {}", assignmentId);

        List<FileDTO> response = homeworksService.getSubmissions(assignmentId);
        logger.info("getSubmissions controller methodundan dönen cevap: {}", response);

        return response;
    }
}

