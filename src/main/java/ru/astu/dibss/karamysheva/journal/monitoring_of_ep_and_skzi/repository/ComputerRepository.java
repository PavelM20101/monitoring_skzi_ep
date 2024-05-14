package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.Computer;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComputerRepository extends CrudRepository<Computer, Integer> {
    List<Computer> findAll();

    Optional<Computer> findById(Integer id);

    void deleteById(Integer id);

    @Override
    <S extends Computer> S save(S entity);

    List<Computer> findComputerByCabinet(String cabinet);
    List<Computer> findComputerByDepartmentContainingIgnoreCase(String department);
    List<Computer> findComputerByLearningCampusContainingIgnoreCase(String campus);
    List<Computer> findComputerByMarkingContainingIgnoreCase(String marking);
}
