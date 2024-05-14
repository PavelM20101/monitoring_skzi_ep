package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service;

import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.EntrySKZIDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.Employee;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.EntryEP;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.EntrySKZI;

import java.util.List;

public interface EntrySKZIService {
    List<EntrySKZIDTO> findAll();

    EntrySKZIDTO findById(Integer id);

    void deleteById(Integer id);

    void save(EntrySKZIDTO dto);
    List<EntrySKZIDTO> findEntrySKZIByNameIbContainingIgnoreCase(String name);

    List<EntrySKZIDTO> findEntrySKZIByTypeContainingIgnoreCase(String type);
}
