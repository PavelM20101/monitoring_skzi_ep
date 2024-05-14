package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.repository;

import org.springframework.data.repository.CrudRepository;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.Sertificate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SertificateRepository extends CrudRepository<Sertificate, Integer> {
    List<Sertificate> findAll();

    Optional<Sertificate> findById(Integer id);

    void deleteById(Integer id);

    @Override
    <S extends Sertificate> S save(S entity);

    List<Sertificate> findSertificateByNameContainingIgnoreCase(String name);
    List<Sertificate> findSertificateBySerialNumberContainingIgnoreCase(String serial);
    List<Sertificate> findSertificateByVerificationCenterContainingIgnoreCase(String center);
    List<Sertificate> findSertificateByDateOfExpiryBetween(LocalDateTime now, LocalDateTime expiryThreshold);
}
