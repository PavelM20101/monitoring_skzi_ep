package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmployeeDTO {
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private String post;
    @NotBlank
    private String learningCampus;
    @NotBlank
    private String department;
    @NotBlank
    private Integer cabinet;
}
