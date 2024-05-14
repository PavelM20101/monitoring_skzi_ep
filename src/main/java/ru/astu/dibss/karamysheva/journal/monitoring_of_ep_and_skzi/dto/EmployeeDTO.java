package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto;

import lombok.Data;

@Data
public class EmployeeDTO {
    private Integer id;
    private String name;
    private String post;
    private String learningCampus;
    private String department;
    private Integer cabinet;
}
