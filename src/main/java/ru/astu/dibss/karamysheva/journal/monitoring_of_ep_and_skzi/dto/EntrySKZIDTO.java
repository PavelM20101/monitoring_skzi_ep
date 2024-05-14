package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class EntrySKZIDTO {
    private Integer id;
    private String type;
    private List<CarrierDTO> carriers;
    private LocalDateTime installationDateSkzi;
    private String nameIb;
}