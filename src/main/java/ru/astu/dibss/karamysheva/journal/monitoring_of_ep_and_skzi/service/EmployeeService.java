package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service;

import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.EmployeeDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.Employee;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> findAll();

    EmployeeDTO findById(Integer id);

    void deleteById(Integer id);

    void save(EmployeeDTO entity);

    List<Employee> findEmployeesByIds(List<Integer> employeeIds);

    List<EmployeeDTO> findEmployeeByCabinet(Integer cabinet);
    List<EmployeeDTO> findEmployeeByDepartmentContainsIgnoreCase(String department);
    List<EmployeeDTO> findEmployeeByLearningCampusContainsIgnoreCase(String campus);
    List<EmployeeDTO> findEmployeeByNameContainingIgnoreCase(String name);
    List<EmployeeDTO> findEmployeeByPostContainingIgnoreCase(String post);
}
