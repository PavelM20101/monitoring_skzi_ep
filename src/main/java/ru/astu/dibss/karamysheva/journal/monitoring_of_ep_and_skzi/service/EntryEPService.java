package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service;

import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.EntryEPDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.Employee;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.EntryEP;

import java.util.List;

public interface EntryEPService {
    List<EntryEPDTO> findAll();

    EntryEPDTO findById(Integer id);

    void deleteById(Integer id);

    void save(EntryEPDTO dto);
    List<EntryEPDTO> findEntryEPByCabinet(Integer integer);

    List<EntryEPDTO> findEntryEPByLearningCampusContainingIgnoreCase(String campus);

    List<EntryEPDTO> findEntryEPByNameOfEmployeeIbContainingIgnoreCase(String name);

    List<EntryEPDTO> findEntryEPByTypeContainingIgnoreCase(String type);
}
