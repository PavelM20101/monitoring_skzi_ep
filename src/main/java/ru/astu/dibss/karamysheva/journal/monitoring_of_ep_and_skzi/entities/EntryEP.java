package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "entry_ep")
@Data
@RequiredArgsConstructor
public class EntryEP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "type")
    private String type;

    @OneToMany(targetEntity = Carrier.class, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "entry_ep_id")
    private List<Carrier> carrier;

    @OneToMany(targetEntity = Computer.class, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "entry_ep_id")
    private List<Computer> computer;

    @OneToMany(targetEntity = Sertificate.class, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "entry_ep_id")
    private List<Sertificate> sertificate;

    @OneToMany(targetEntity = Employee.class, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "entry_ep_id")
    private List<Employee> employees;

    @Column(name = "date_of_issue_ep")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime dateOfIssueEp;

    @Column(name = "date_of_receiving")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime dateOfReceiving;

    @Column(name = "date_of_installation_ecp")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime dateOfInstallationEcp;

    @Column(name = "name_of_employee_ib")
    private String nameOfEmployeeIb;

    @Column(name = "learning_campus")
    private String learningCampus;

    @Column(name = "cabinet")
    private Integer cabinet;
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
