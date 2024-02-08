package dev.pablodias.nlw_certification.modules.questions.controller;

import dev.pablodias.nlw_certification.modules.questions.DTOs.AlternativesResultDTO;
import dev.pablodias.nlw_certification.modules.questions.DTOs.QuestionResultDTO;
import dev.pablodias.nlw_certification.modules.questions.entities.AlternativeEntity;
import dev.pablodias.nlw_certification.modules.questions.entities.QuestionEntity;
import dev.pablodias.nlw_certification.modules.questions.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    QuestionRepository questionRepository;

    @GetMapping("/technology/{technology}")
    public List<QuestionResultDTO> findByTechnology(@PathVariable String technology) {
        var result = this.questionRepository.findByTechnology(technology);

        return result.stream().map(question -> mapQuestionToDTO(question)).collect(Collectors.toList());
    }

    static QuestionResultDTO mapQuestionToDTO(QuestionEntity question) {
        QuestionResultDTO questionResultDTO = new QuestionResultDTO();

        questionResultDTO.setDescription(question.getDescription());
        questionResultDTO.setId(question.getId());
        questionResultDTO.setTechnology(question.getTechnology());

        List<AlternativesResultDTO> alternativesResultDTOS =
                question.getAlternatives().stream().map(alternative -> mapAlternativeDTO(alternative)).collect(Collectors.toList());

        questionResultDTO.setAlternatives(alternativesResultDTOS);

        return questionResultDTO;
    }

    static AlternativesResultDTO mapAlternativeDTO(AlternativeEntity alternatives) {
        return AlternativesResultDTO.builder().id(alternatives.getId()).description(alternatives.getDescription()).build();
    }
}
