package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ComputerDTO {
    private Integer id;
    @NotBlank
    private String marking;
    @NotBlank
    private String learningCampus;
    @NotBlank
    private String department;
    @NotBlank
    private String cabinet;
}
