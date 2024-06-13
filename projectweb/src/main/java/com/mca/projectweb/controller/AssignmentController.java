package com.mca.projectweb.controller;
import com.mca.projectweb.configuration.CurrentUser;
import com.mca.projectweb.dto.AssignmentDTO;
import com.mca.projectweb.service.AssignmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class AssignmentController {

    private static final Logger logger = LoggerFactory.getLogger(AssignmentController.class);

    @Autowired
    AssignmentService assignmentService;

    //öğrenci öğretmenin yüklediği ödevleri görür.
    @GetMapping("/api/v1/assignment/{teacher}")
    public List<AssignmentDTO> getTeachersWorks(@PathVariable String teacher )  {
        logger.info("getTeachersWorks controller methoduna girdi, teacher: {}", teacher);

        List<AssignmentDTO> response = assignmentService.getTeachersWorks(teacher);
        logger.info("getTeachersWorks controller methodundan dönen cevap: {}", response);

        return response;
    }

    //açılan ödevin detayları gelir dosya yükleme ekranı.
    @GetMapping("/api/v1/projectDetail/{assignmentId}")
    public AssignmentDTO getProjectDetail(@PathVariable Long assignmentId )  {
        logger.info("getProjectDetail controller methoduna girdi, assignmentId: {}", assignmentId);

        AssignmentDTO response = assignmentService.getProjectDetail(assignmentId);
        logger.info("getProjectDetail controller methodundan dönen cevap: {}", response);

        return response;
    }

    //detayları açılan ödevin dosya yüklemesi buraya gelir.
    @PostMapping("/api/v1/projectUpload/{assignmentId}")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file, @PathVariable("assignmentId") long assignmentId,@AuthenticationPrincipal CurrentUser currentUser) throws IOException {
        logger.info("handleFileUpload controller methoduna girdi, assignmentId: {}", assignmentId);

        logger.info("Yüklenen dosyanın adı: {}", file.getOriginalFilename());
        logger.info("Dosya boyutu: {} bytes", file.getSize());

        assignmentService.saveFile(file.getBytes(), assignmentId, currentUser.getId());

        return ResponseEntity.ok("Dosya basariyla yüklendi.");
    }

    //öğretmen yeni ödev yüklerken buraya gelir.
    @PostMapping("/api/v1/teacher/works/{userId}")
    public String addTeacherWork(@RequestBody AssignmentDTO assignmentDTO, @PathVariable Long userId, @AuthenticationPrincipal CurrentUser currentUser){
        System.err.println("----------------currentUser:"+currentUser.getId());
        logger.info("addTeacherWork controller methoduna girdi, userId: {}", userId);

        logger.info("AssignmentDTO bilgileri:");
        logger.info("User Id: {}", assignmentDTO.getUserId());
        logger.info("CreatedAt: {}", assignmentDTO.getCreatedAt());
        logger.info("Name: {}", assignmentDTO.getName());
        logger.info("Deadline: {}", assignmentDTO.getDeadline());
        logger.info("Description: {}", assignmentDTO.getDescription());
        logger.info("Active: {}", assignmentDTO.getActive());

        assignmentService.addTeacherWork(assignmentDTO);

        return "Ödev başarılı bir şekilde eklendi";
    }
}

