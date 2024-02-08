package dev.pablodias.nlw_certification.modules.certifications.controllers;

import dev.pablodias.nlw_certification.modules.certifications.services.Top10Service;
import dev.pablodias.nlw_certification.modules.students.entities.CertificationStudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    @Autowired
    private Top10Service top10Service;

    @GetMapping("/top10")
    public List<CertificationStudentEntity> top10() {
        return this.top10Service.execute();
    }
}
