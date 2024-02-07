package dev.pablodias.nlw_certification.modules.students.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "certifications")
public class CertificationStudentEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
   private UUID id;

   @JoinColumn(name = "student_id", insertable = false, updatable = false)
   private UUID studentID;

   @Column(length = 100)
   private String technology;

   @Column(length = 10)
   private Integer grade;

   @ManyToOne
   @JoinColumn(name = "student_id", insertable = false, updatable = false)
   private StudentEntity studentEntity;

   @OneToMany
   @JoinColumn(name = "answer_certification_id")
   private List<AnswersCertificationsEntity> answerCertificationEntities;
}
