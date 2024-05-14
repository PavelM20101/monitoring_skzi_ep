package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.Carrier;

import java.util.List;
import java.util.Optional;
@Repository
public interface CarrierRepository extends CrudRepository<Carrier, Integer> {
    List<Carrier> findAll();

    Optional<Carrier> findById(Integer id);

    void deleteById(Integer id);

    @Override
    <S extends Carrier> S save(S entity);

    List<Carrier> findCarrierByMarkirovkaContainingIgnoreCase(String markirovka);
    List<Carrier> findCarrierBySerialNumberContainingIgnoreCase(String ser);
    List<Carrier> findCarrierByTypeContainingIgnoreCase(String type);

}
