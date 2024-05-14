package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service;

import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.SertificateDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.Carrier;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.Sertificate;

import java.util.List;

public interface SertificateService {
    List<SertificateDTO> findAll();

    SertificateDTO findById(Integer id);

    void deleteById(Integer id);

    void save(SertificateDTO entity);
    List<SertificateDTO> findSertificateByNameContainingIgnoreCase(String name);
    List<SertificateDTO> findSertificateBySerialNumberContainingIgnoreCase(String serial);
    List<SertificateDTO> findSertificateByVerificationCenterContainingIgnoreCase(String center);
    List<Sertificate> findSertificatesByIds(List<Integer> sertificatesIds);
    List<SertificateDTO> findSertificatesExpiringSoon();
}
