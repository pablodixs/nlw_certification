package dev.pablodias.nlw_certification.modules.students.entities;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.metamodel.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "certifications")
@Builder
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

   @OneToMany(cascade = CascadeType.ALL)
   @JoinColumn(name = "answer_certification_id")
   @JsonManagedReference
   private List<AnswersCertificationsEntity> answerCertificationEntities;
}
