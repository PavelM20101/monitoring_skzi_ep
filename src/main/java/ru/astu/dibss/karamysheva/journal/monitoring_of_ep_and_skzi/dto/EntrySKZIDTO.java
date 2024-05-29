package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class EntrySKZIDTO {
    private Integer id;
    @NotBlank
    private String type;
    private List<CarrierDTO> carriers;
    @NotNull
    private LocalDateTime installationDateSkzi;
    @NotBlank
    private String nameIb;
}