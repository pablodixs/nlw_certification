package dev.pablodias.nlw_certification.modules.questions.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResultDTO {

    private UUID id;
    private String technology;
    private String description;


    private List<AlternativesResultDTO> alternatives;
}
