package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    List<Employee> findAll();

    Optional<Employee> findById(Integer id);

    void deleteById(Integer id);

    @Override
    <S extends Employee> S save(S entity);

    List<Employee> findEmployeeByCabinet(Integer cabinet);
    List<Employee> findEmployeeByDepartmentContainsIgnoreCase(String department);
    List<Employee> findEmployeeByLearningCampusContainsIgnoreCase(String campus);
    List<Employee> findEmployeeByNameContainingIgnoreCase(String name);
    List<Employee> findEmployeeByPostContainingIgnoreCase(String post);
}
