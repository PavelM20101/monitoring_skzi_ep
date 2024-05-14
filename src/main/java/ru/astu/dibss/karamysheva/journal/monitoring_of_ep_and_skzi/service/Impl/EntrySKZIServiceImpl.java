package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.EmployeeDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.EntrySKZIDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.Employee;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.EntryEP;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.EntrySKZI;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.repository.EntrySKZIRepository;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.EntrySKZIService;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.utils.mappingUtils.MappingUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EntrySKZIServiceImpl implements EntrySKZIService {
    private EntrySKZIRepository entrySKZIRepository;

    @Autowired
    public EntrySKZIServiceImpl(EntrySKZIRepository entrySKZIRepository) {
        this.entrySKZIRepository = entrySKZIRepository;
    }

    @Override
    public List<EntrySKZIDTO> findAll() {
        return entrySKZIRepository.findAll().stream()
                .map(MappingUtils::mapEntrySKZIToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EntrySKZIDTO findById(Integer id) {
        Optional<EntrySKZI> entrySKZIOptional = entrySKZIRepository.findById(id);
        return entrySKZIOptional.map(MappingUtils::mapEntrySKZIToDto).orElseGet(EntrySKZIDTO::new);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        entrySKZIRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void save(EntrySKZIDTO dto) {
        EntrySKZI entity = MappingUtils.mapDtoToEntrySKZI(dto);
        entrySKZIRepository.save(entity);
    }

    @Override
    public List<EntrySKZIDTO> findEntrySKZIByNameIbContainingIgnoreCase(String name) {
        return entrySKZIRepository.findEntrySKZIByNameIbContainingIgnoreCase(name).stream()
                .map(MappingUtils::mapEntrySKZIToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EntrySKZIDTO> findEntrySKZIByTypeContainingIgnoreCase(String type) {
        return entrySKZIRepository.findEntrySKZIByTypeContainingIgnoreCase(type).stream()
                .map(MappingUtils::mapEntrySKZIToDto)
                .collect(Collectors.toList());
    }
}
