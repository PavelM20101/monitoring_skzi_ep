package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.repository;

import org.springframework.data.repository.CrudRepository;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.EntryEP;

import java.util.List;
import java.util.Optional;

public interface EntryEPRepository extends CrudRepository<EntryEP, Integer> {

    List<EntryEP> findEntryEPByCabinet(Integer integer);

    List<EntryEP> findEntryEPByLearningCampusContainingIgnoreCase(String campus);

    List<EntryEP> findEntryEPByNameOfEmployeeIbContainingIgnoreCase(String name);

    List<EntryEP> findEntryEPByTypeContainingIgnoreCase(String type);

    List<EntryEP> findAll();

    Optional<EntryEP> findById(Integer id);

    void deleteById(Integer id);

    @Override
    <S extends EntryEP> S save(S entity);
}
