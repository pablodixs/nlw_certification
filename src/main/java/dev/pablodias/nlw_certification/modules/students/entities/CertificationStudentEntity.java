package dev.pablodias.nlw_certification.modules.students.entities;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificationStudentEntity {
   private UUID id;
   private UUID studentID;
   private String technology;
   private Integer grade;
   private List<AnswersCertificationsEntity> answerCertificationEntities;
}
