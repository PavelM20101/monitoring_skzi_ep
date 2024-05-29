package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class EntryEPDTO {
    private Integer id;
    @NotBlank
    private String type;
    private List<CarrierDTO> carriers;
    private List<ComputerDTO> computers;
    private List<SertificateDTO> sertificates;
    private List<EmployeeDTO> employees;
    @NotNull
    private LocalDateTime dateOfIssueEp;
    @NotNull
    private LocalDateTime dateOfReceiving;
    @NotNull
    private LocalDateTime dateOfInstallationEcp;
    @NotBlank
    private String nameOfEmployeeIb;
    @NotBlank
    private String learningCampus;
    @NotBlank
    private Integer cabinet;
}
