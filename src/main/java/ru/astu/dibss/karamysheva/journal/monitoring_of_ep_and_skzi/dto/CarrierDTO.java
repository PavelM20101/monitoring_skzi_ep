package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class CarrierDTO {
    private Integer id;
    @NotBlank
    private String type;
    private String serialNumber;
    private String markirovka;
    private List<EmployeeDTO> employees;
}
