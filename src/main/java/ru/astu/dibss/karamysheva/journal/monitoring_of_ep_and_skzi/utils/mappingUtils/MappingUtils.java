package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.utils.mappingUtils;

import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.CarrierDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.ComputerDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.EmployeeDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.EntryEPDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.EntrySKZIDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.SertificateDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.Carrier;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.Computer;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.Employee;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.EntryEP;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.EntrySKZI;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.Sertificate;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MappingUtils {
    public static CarrierDTO mapCarrierToDto(Carrier entity){
        CarrierDTO dto = new CarrierDTO();
        dto.setId(entity.getId());
        dto.setType(entity.getType());
        dto.setMarkirovka(entity.getMarkirovka());
        dto.setSerialNumber(entity.getSerialNumber());
        if (entity.getEmployees() != null) {
            dto.setEmployees(entity.getEmployees().stream()
                    .map(MappingUtils::mapEmployeeToDto)
                    .collect(Collectors.toList()));
        } else {
            dto.setEmployees(new ArrayList<>());
        }
        return dto;
    }

    public static Carrier mapDtoToCarrier(CarrierDTO dto){
        Carrier entity = new Carrier();
        entity.setId(dto.getId());
        entity.setType(dto.getType());
        entity.setMarkirovka(dto.getMarkirovka());
        entity.setSerialNumber(dto.getSerialNumber());
        if (dto.getEmployees() != null) {
            entity.setEmployees(dto.getEmployees().stream()
                    .map(MappingUtils::mapDtoToEmployee)
                    .collect(Collectors.toList()));
        } else {
            entity.setEmployees(new ArrayList<>());
        }
        return entity;
    }

    public static EmployeeDTO mapEmployeeToDto(Employee entity){
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPost(entity.getPost());
        dto.setCabinet(entity.getCabinet());
        dto.setDepartment(entity.getDepartment());
        dto.setLearningCampus(entity.getLearningCampus());
        return dto;
    }

    public static Employee mapDtoToEmployee(EmployeeDTO dto){
        Employee entity = new Employee();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPost(dto.getPost());
        entity.setCabinet(dto.getCabinet());
        entity.setDepartment(dto.getDepartment());
        entity.setLearningCampus(dto.getLearningCampus());
        return entity;
    }

    public static ComputerDTO mapComputerToDto(Computer entity){
        ComputerDTO dto = new ComputerDTO();
        dto.setId(entity.getId());
        dto.setMarking(entity.getMarking());
        dto.setLearningCampus(entity.getLearningCampus());
        dto.setCabinet(entity.getCabinet());
        dto.setDepartment(entity.getDepartment());
        return dto;
    }

    public static Computer mapDtoToComputer(ComputerDTO dto){
        Computer entity = new Computer();
        entity.setId(dto.getId());
        entity.setMarking(dto.getMarking());
        entity.setLearningCampus(dto.getLearningCampus());
        entity.setCabinet(dto.getCabinet());
        entity.setDepartment(dto.getDepartment());
        return entity;
    }

    public static SertificateDTO mapSertificateToDto(Sertificate entity){
        SertificateDTO dto = new SertificateDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSerialNumber(entity.getSerialNumber());
        dto.setVerificationCenter(entity.getVerificationCenter());
        dto.setDateOfIssue(entity.getDateOfIssue());
        dto.setDateOfExpiry(entity.getDateOfExpiry());
        return dto;
    }

    public static Sertificate mapDtoToSertificate(SertificateDTO dto){
        Sertificate entity = new Sertificate();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSerialNumber(dto.getSerialNumber());
        entity.setVerificationCenter(dto.getVerificationCenter());
        entity.setDateOfIssue(dto.getDateOfIssue());
        entity.setDateOfExpiry(dto.getDateOfExpiry());
        return entity;
    }

    public static EntrySKZIDTO mapEntrySKZIToDto(EntrySKZI entity){
        EntrySKZIDTO dto = new EntrySKZIDTO();
        dto.setId(entity.getId());
        dto.setType(entity.getType());
        dto.setNameIb(entity.getNameIb());
        dto.setInstallationDateSkzi(entity.getInstallationDateSkzi());
        if(entity.getCarrier() != null){
            dto.setCarriers(entity.getCarrier().stream()
                    .map(MappingUtils::mapCarrierToDto)
                    .collect(Collectors.toList()));
        } else {
            dto.setCarriers(new ArrayList<>());
        }

        return dto;
    }

    public static EntrySKZI mapDtoToEntrySKZI(EntrySKZIDTO dto){
        EntrySKZI entity = new EntrySKZI();
        entity.setId(dto.getId());
        entity.setType(dto.getType());
        entity.setNameIb(dto.getNameIb());
        entity.setInstallationDateSkzi(dto.getInstallationDateSkzi());
        if (dto.getCarriers() != null){
            entity.setCarrier(dto.getCarriers().stream()
                    .map(MappingUtils::mapDtoToCarrier)
                    .collect(Collectors.toList()));
        } else {
            entity.setCarrier(new ArrayList<>());
        }

        return entity;
    }

    public static EntryEPDTO mapEntryEPToDto(EntryEP entity){
        EntryEPDTO dto = new EntryEPDTO();
        dto.setId(entity.getId());
        dto.setType(entity.getType());
        dto.setDateOfIssueEp(entity.getDateOfIssueEp());
        dto.setDateOfReceiving(entity.getDateOfReceiving());
        dto.setDateOfInstallationEcp(entity.getDateOfInstallationEcp());
        dto.setNameOfEmployeeIb(entity.getNameOfEmployeeIb());
        dto.setLearningCampus(entity.getLearningCampus());
        dto.setCabinet(entity.getCabinet());
        if (entity.getCarrier() != null){
            dto.setCarriers(entity.getCarrier().stream()
                    .map(MappingUtils::mapCarrierToDto)
                    .collect(Collectors.toList()));
        } else {
            dto.setCarriers(new ArrayList<>());
        }
        if (entity.getComputer() != null){
            dto.setComputers(entity.getComputer().stream()
                    .map(MappingUtils::mapComputerToDto)
                    .collect(Collectors.toList()));
        } else {
            dto.setComputers(new ArrayList<>());
        }
        if(entity.getSertificate() != null){
            dto.setSertificates(entity.getSertificate().stream()
                    .map(MappingUtils::mapSertificateToDto)
                    .collect(Collectors.toList()));
        } else {
            dto.setSertificates(new ArrayList<>());
        }
        if(entity.getEmployees() != null){
            dto.setEmployees(entity.getEmployees().stream()
                    .map(MappingUtils::mapEmployeeToDto)
                    .collect(Collectors.toList()));
        } else {
            dto.setEmployees(new ArrayList<>());
        }

        return dto;
    }

    public static EntryEP mapDtoToEntryEP(EntryEPDTO dto){
        EntryEP entity = new EntryEP();
        entity.setId(dto.getId());
        entity.setType(dto.getType());
        entity.setDateOfIssueEp(dto.getDateOfIssueEp());
        entity.setDateOfReceiving(dto.getDateOfReceiving());
        entity.setDateOfInstallationEcp(dto.getDateOfInstallationEcp());
        entity.setNameOfEmployeeIb(dto.getNameOfEmployeeIb());
        entity.setLearningCampus(dto.getLearningCampus());
        entity.setCabinet(dto.getCabinet());
        if(dto.getCarriers() != null){
            entity.setCarrier(dto.getCarriers().stream()
                    .map(MappingUtils::mapDtoToCarrier)
                    .collect(Collectors.toList()));
        } else {
            entity.setCarrier(new ArrayList<>());
        }
        if(dto.getComputers() != null){
            entity.setComputer(dto.getComputers().stream()
                    .map(MappingUtils::mapDtoToComputer)
                    .collect(Collectors.toList()));
        } else {
            entity.setComputer(new ArrayList<>());
        }
        if(dto.getSertificates() != null){
            entity.setSertificate(dto.getSertificates().stream()
                    .map(MappingUtils::mapDtoToSertificate)
                    .collect(Collectors.toList()));
        } else {
            entity.setSertificate(new ArrayList<>());
        }
        if(dto.getEmployees() != null){
            entity.setEmployees(dto.getEmployees().stream()
                    .map(MappingUtils::mapDtoToEmployee)
                    .collect(Collectors.toList()));
        } else {
            entity.setEmployees(new ArrayList<>());
        }
        return entity;
    }
}
