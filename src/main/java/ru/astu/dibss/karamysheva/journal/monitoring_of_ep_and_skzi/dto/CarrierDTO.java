package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto;

import lombok.Data;

import java.util.List;

@Data
public class CarrierDTO {
    private Integer id;
    private String type;
    private String serialNumber;
    private String markirovka;
    private List<EmployeeDTO> employees;
}
