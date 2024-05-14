package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.EmployeeDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.SertificateDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.Carrier;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.Employee;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.Sertificate;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.repository.SertificateRepository;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.SertificateService;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.utils.mappingUtils.MappingUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SertificatServiceImpl implements SertificateService {
    private SertificateRepository sertificateRepository;

    @Autowired
    public SertificatServiceImpl(SertificateRepository sertificateRepository) {
        this.sertificateRepository = sertificateRepository;
    }

    @Override
    public List<SertificateDTO> findAll() {
        return sertificateRepository.findAll().stream()
                .map(MappingUtils::mapSertificateToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SertificateDTO findById(Integer id) {
        Optional<Sertificate> sertificateOptional = sertificateRepository.findById(id);
        return sertificateOptional.map(MappingUtils::mapSertificateToDto).orElseGet(SertificateDTO::new);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        sertificateRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void save(SertificateDTO dto) {
        Sertificate entity = MappingUtils.mapDtoToSertificate(dto);
        sertificateRepository.save(entity);
    }

    @Override
    public List<SertificateDTO> findSertificateByNameContainingIgnoreCase(String name) {
        return sertificateRepository.findSertificateByNameContainingIgnoreCase(name).stream()
                .map(MappingUtils::mapSertificateToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SertificateDTO> findSertificateBySerialNumberContainingIgnoreCase(String serial) {
        return sertificateRepository.findSertificateBySerialNumberContainingIgnoreCase(serial).stream()
                .map(MappingUtils::mapSertificateToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SertificateDTO> findSertificateByVerificationCenterContainingIgnoreCase(String center) {
        return sertificateRepository.findSertificateByVerificationCenterContainingIgnoreCase(center).stream()
                .map(MappingUtils::mapSertificateToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Sertificate> findSertificatesByIds(List<Integer> sertificatesIds) {
        Iterable<Sertificate> sertificateIterable = sertificateRepository.findAllById(sertificatesIds);
        List<Sertificate> sertificates = new ArrayList<>();
        sertificateIterable.forEach(sertificates::add);
        return sertificates;
    }
    @Override
    public List<SertificateDTO> findSertificatesExpiringSoon() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiryThreshold = now.plusDays(45);

        List<Sertificate> expiringSoonSertificates = sertificateRepository.findSertificateByDateOfExpiryBetween(now, expiryThreshold);

        return expiringSoonSertificates.stream()
                .map(MappingUtils::mapSertificateToDto)
                .collect(Collectors.toList());
    }
}
