package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service;

import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.ComputerDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.Carrier;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.Computer;

import java.util.List;

public interface ComputerService {
    List<ComputerDTO> findAll();

    ComputerDTO findByID(Integer id);

    void deleteById(Integer id);

    void save(ComputerDTO entity);

    List<ComputerDTO> findComputerByCabinet(String cabinet);
    List<ComputerDTO> findComputerByDepartmentContainingIgnoreCase(String department);
    List<ComputerDTO> findComputerByLearningCampusContainingIgnoreCase(String campus);
    List<ComputerDTO> findComputerByMarkingContainingIgnoreCase(String marking);
    List<Computer> findComputersByIds(List<Integer> computerIds);
}
