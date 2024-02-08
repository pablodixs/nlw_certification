package dev.pablodias.nlw_certification.modules.students.services;

import dev.pablodias.nlw_certification.modules.questions.entities.AlternativeEntity;
import dev.pablodias.nlw_certification.modules.questions.entities.QuestionEntity;
import dev.pablodias.nlw_certification.modules.questions.repositories.QuestionRepository;
import dev.pablodias.nlw_certification.modules.students.DTOs.StudentCertificationAnswersDTO;
import dev.pablodias.nlw_certification.modules.students.entities.AnswersCertificationsEntity;
import dev.pablodias.nlw_certification.modules.students.entities.CertificationStudentEntity;
import dev.pablodias.nlw_certification.modules.students.entities.StudentEntity;
import dev.pablodias.nlw_certification.modules.students.repositories.CertificationStudentRepository;
import dev.pablodias.nlw_certification.modules.students.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StudentCertificationAnswersService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    public CertificationStudentEntity execute(StudentCertificationAnswersDTO dto) {
        List<QuestionEntity> questions = questionRepository.findByTechnology(dto.getTechnology());
        List<AnswersCertificationsEntity> answersCertifications = new ArrayList<>();

        AtomicInteger correctAnswers = new AtomicInteger();

        dto.getQuestionAnswers().forEach(questionAnswer -> {
            var question = questions.stream()
                    .filter(q -> q.getId().equals(questionAnswer.getQuestionId()))
                    .findFirst().get();

            var findCorrectAlternative = question.getAlternatives().stream()
                    .filter(AlternativeEntity::getIsCorrect)
                    .findFirst().get();

            if (findCorrectAlternative.getId().equals(questionAnswer.getAlternativeId())) {
                questionAnswer.setIsCorrect(true);
                correctAnswers.getAndIncrement();
            } else {
                questionAnswer.setIsCorrect(false);
            }

            var answersCertificationsEntity = AnswersCertificationsEntity.builder()
                    .answerID(questionAnswer.getAlternativeId())
                    .questionID(questionAnswer.getQuestionId())
                    .isCorrect(questionAnswer.getIsCorrect())
                    .build();

            answersCertifications.add(answersCertificationsEntity);
        } );

        var student = this.studentRepository.findByEmail(dto.getEmail());

        UUID studentId;

        if(student.isEmpty()) {
            var newStudent = StudentEntity.builder().email(dto.getEmail()).build();
            newStudent = this.studentRepository.save(newStudent);
            studentId = newStudent.getId();
        } else {
            studentId = student.get().getId();
        }

         CertificationStudentEntity certificationStudent =
                 CertificationStudentEntity.builder()
                         .studentID(studentId)
                         .technology(dto.getTechnology())
                         .grade(correctAnswers.get())
                         .build();

        var certificationStudentEntity = this.certificationStudentRepository.save(certificationStudent);

        answersCertifications.forEach(answersCertification -> {
                    answersCertification.setCertificationID(certificationStudentEntity.getId());
                    answersCertification.setCertificationStudent(certificationStudentEntity);
                });

        certificationStudentEntity.setAnswerCertificationEntities(answersCertifications);

        this.certificationStudentRepository.save(certificationStudent);

        return certificationStudentEntity;
    }
}
