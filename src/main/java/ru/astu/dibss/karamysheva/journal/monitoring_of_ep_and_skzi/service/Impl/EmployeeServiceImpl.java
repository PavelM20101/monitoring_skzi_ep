package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.EmployeeDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.Employee;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.repository.EmployeeRepository;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.EmployeeService;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.utils.mappingUtils.MappingUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeDTO> findAll() {
        return employeeRepository.findAll().stream()
                .map(MappingUtils::mapEmployeeToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO findById(Integer id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        return employeeOptional.map(MappingUtils::mapEmployeeToDto).orElseGet(EmployeeDTO::new);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void save(EmployeeDTO dto) {
        Employee entity = MappingUtils.mapDtoToEmployee(dto);
        employeeRepository.save(entity);
    }
    public List<Employee> findEmployeesByIds(List<Integer> employeeIds) {
        Iterable<Employee> employeesIterable = employeeRepository.findAllById(employeeIds);
        List<Employee> employeesList = new ArrayList<>();
        employeesIterable.forEach(employeesList::add);
        return employeesList;
    }

    @Override
    public List<EmployeeDTO> findEmployeeByCabinet(Integer cabinet) {
        return employeeRepository.findEmployeeByCabinet(cabinet).stream()
                .map(MappingUtils::mapEmployeeToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> findEmployeeByDepartmentContainsIgnoreCase(String department) {
        return employeeRepository.findEmployeeByDepartmentContainsIgnoreCase(department).stream()
                .map(MappingUtils::mapEmployeeToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> findEmployeeByLearningCampusContainsIgnoreCase(String campus) {
        return employeeRepository.findEmployeeByLearningCampusContainsIgnoreCase(campus).stream()
                .map(MappingUtils::mapEmployeeToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> findEmployeeByNameContainingIgnoreCase(String name) {
        return employeeRepository.findEmployeeByNameContainingIgnoreCase(name).stream()
                .map(MappingUtils::mapEmployeeToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> findEmployeeByPostContainingIgnoreCase(String post) {
        return employeeRepository.findEmployeeByPostContainingIgnoreCase(post).stream()
                .map(MappingUtils::mapEmployeeToDto)
                .collect(Collectors.toList());
    }
}
