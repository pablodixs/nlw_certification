package dev.pablodias.nlw_certification.modules.students.entities;

import java.util.List;
import java.util.UUID;

public class StudentEntity {
  private UUID id;
  private String email;
  private List<CertificationStudentEntity> certificationStudentEntities;

}
