package dev.pablodias.nlw_certification.modules.certifications.services;

import dev.pablodias.nlw_certification.modules.students.entities.CertificationStudentEntity;
import dev.pablodias.nlw_certification.modules.students.repositories.CertificationStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Top10Service {

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    public List<CertificationStudentEntity> execute() {
        return this.certificationStudentRepository.findTop10ByOrderByGradeDesc();
    }
}
