package dev.pablodias.nlw_certification.modules.students.services;

import dev.pablodias.nlw_certification.modules.students.repositories.CertificationStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.pablodias.nlw_certification.modules.students.DTOs.VerifyHasCertificationDTO;

@Service
public class VerifyIfHasCertificationService {

   @Autowired
   private CertificationStudentRepository certificationStudentRepository;

   public Boolean execute(VerifyHasCertificationDTO dto) {
      var result = this.certificationStudentRepository.findByStudentEmailAndTechnology(dto.getEmail(), dto.getTechnology());

      if(!result.isEmpty()) {
         return true;
      }

      return false;
   }
}
