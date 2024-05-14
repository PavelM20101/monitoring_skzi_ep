package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class SertificateDTO {
    private Integer id;
    private String name;
    private String serialNumber;
    private String verificationCenter;
    private LocalDateTime dateOfIssue;
    private LocalDateTime dateOfExpiry;
    private boolean expirySoon;
    public boolean isExpiringSoon() {
        if (dateOfExpiry == null) {
            return false; // или выбрать другое поведение в случае, если дата истечения не установлена
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiryThreshold = now.plusDays(45); // 45 days from now

        return expiryThreshold.isAfter(dateOfExpiry);
    }
}
