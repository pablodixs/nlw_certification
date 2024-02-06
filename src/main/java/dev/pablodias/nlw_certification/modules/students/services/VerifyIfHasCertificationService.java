package dev.pablodias.nlw_certification.modules.students.services;

import org.springframework.stereotype.Service;

import dev.pablodias.nlw_certification.modules.students.DTOs.VerifyHasCertificationDTO;

@Service
public class VerifyIfHasCertificationService {

   public Boolean execute(VerifyHasCertificationDTO dto) {
      if (dto.getEmail().equals("pabllosoarez") && dto.getTechnology().equals("react")) {
         return true;
      }

      return false;
   }
}
