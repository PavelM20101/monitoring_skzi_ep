package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto;

import lombok.Data;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class EntryEPDTO {
    private Integer id;
    private String type;
    private List<CarrierDTO> carriers;
    private List<ComputerDTO> computers;
    private List<SertificateDTO> sertificates;
    private List<EmployeeDTO> employees;
    private LocalDateTime dateOfIssueEp;
    private LocalDateTime dateOfReceiving;
    private LocalDateTime dateOfInstallationEcp;
    private String nameOfEmployeeIb;
    private String learningCampus;
    private Integer cabinet;
}
