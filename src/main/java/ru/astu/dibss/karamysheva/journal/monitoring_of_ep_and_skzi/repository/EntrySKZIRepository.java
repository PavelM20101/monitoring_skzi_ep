package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.repository;

import org.springframework.data.repository.CrudRepository;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.EntrySKZI;

import java.util.List;
import java.util.Optional;

public interface EntrySKZIRepository extends CrudRepository<EntrySKZI, Integer> {

    List<EntrySKZI> findEntrySKZIByNameIbContainingIgnoreCase(String name);
    List<EntrySKZI> findEntrySKZIByTypeContainingIgnoreCase(String type);

    List<EntrySKZI> findAll();

    Optional<EntrySKZI> findById(Integer id);

    void deleteById(Integer id);

    @Override
    <S extends EntrySKZI> S save(S entity);
}
