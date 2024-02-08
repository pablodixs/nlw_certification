package dev.pablodias.nlw_certification.modules.students.entities;

import java.util.List;
import java.util.UUID;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "students")
@Builder
public class StudentEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
   private UUID id;

   @Column(unique = true, nullable = false)
   private String email;

   @OneToMany(mappedBy = "studentEntity")
   private List<CertificationStudentEntity> certificationStudentEntities;

}
