package dev.pablodias.nlw_certification.modules.students.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "answers_certification_students")
public class AnswersCertificationsEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
   private UUID id;

   @JoinColumn(name = "certification_id")
   private UUID certificationID;

   @ManyToOne
   @JoinColumn(name = "certification_id", insertable = false, updatable = false)
   private CertificationStudentEntity certificationStudent;

   @Column(name = "student_id")
   private UUID studentID;

   @ManyToOne
   @JoinColumn(name = "student_id", insertable = false, updatable = false)
   private StudentEntity studentEntity;

   @Column(name = "question_id")
   private UUID questionID;

   @Column(name = "answer_id")
   private UUID answerID;

   @Column(name = "is_correct")
   private Boolean isCorrect;

   @CreationTimestamp
   private LocalDateTime createdAt;
}
