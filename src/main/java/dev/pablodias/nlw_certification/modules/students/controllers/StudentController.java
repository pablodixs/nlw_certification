package dev.pablodias.nlw_certification.modules.students.controllers;

import dev.pablodias.nlw_certification.modules.students.DTOs.StudentCertificationAnswersDTO;
import dev.pablodias.nlw_certification.modules.students.entities.CertificationStudentEntity;
import dev.pablodias.nlw_certification.modules.students.services.StudentCertificationAnswersService;
import org.springframework.web.bind.annotation.RestController;

import dev.pablodias.nlw_certification.modules.students.DTOs.VerifyHasCertificationDTO;
import dev.pablodias.nlw_certification.modules.students.services.VerifyIfHasCertificationService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/students")
public class StudentController {

   @Autowired
   private VerifyIfHasCertificationService verifyIfHasCertificationService;

   @Autowired
   private StudentCertificationAnswersService studentCertificationAnswersService;

   @PostMapping("/verifyhascertification")
   public String verifyIfHasCertification(@RequestBody VerifyHasCertificationDTO verifyHasCertificationDTO) {
      var itHasCertification = this.verifyIfHasCertificationService.execute(verifyHasCertificationDTO);

      if (itHasCertification) {
         return "Usuário não pode fazer a prova";
      }

      return "Usuário pode fazer a prova";
   }

   @PostMapping("/certification/answer")
   public CertificationStudentEntity certificationAnswer(@RequestBody StudentCertificationAnswersDTO studentCertificationAnswersDTO) {
      return this.studentCertificationAnswersService.execute(studentCertificationAnswersDTO);
   }
}
