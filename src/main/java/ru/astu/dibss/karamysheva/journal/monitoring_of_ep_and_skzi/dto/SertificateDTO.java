package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class SertificateDTO {
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private String serialNumber;
    @NotBlank
    private String verificationCenter;
    @NotNull
    private LocalDateTime dateOfIssue;
    @NotNull
    private LocalDateTime dateOfExpiry;
    private boolean expirySoon;
    public boolean isExpiringSoon() {
        if (dateOfExpiry == null) {
            return false;
        }
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiryThreshold = now.plusDays(45);
        return expiryThreshold.isAfter(dateOfExpiry);
    }
}
