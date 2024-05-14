package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.EmployeeDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.EntryEPDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.Employee;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.EntryEP;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.repository.EntryEPRepository;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.EntryEPService;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.utils.mappingUtils.MappingUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EntryEPServiceImpl implements EntryEPService {
    private EntryEPRepository entryEPRepository;

    @Autowired
    public EntryEPServiceImpl(EntryEPRepository entryEPRepository) {
        this.entryEPRepository = entryEPRepository;
    }

    @Override
    public List<EntryEPDTO> findAll() {
        return entryEPRepository.findAll().stream()
                .map(MappingUtils::mapEntryEPToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EntryEPDTO findById(Integer id) {
        Optional<EntryEP> entryEPOptional = entryEPRepository.findById(id);
        return entryEPOptional.map(MappingUtils::mapEntryEPToDto).orElseGet(EntryEPDTO::new);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        entryEPRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void save(EntryEPDTO dto) {
        EntryEP entity = MappingUtils.mapDtoToEntryEP(dto);
        entryEPRepository.save(entity);
    }

    @Override
    public List<EntryEPDTO> findEntryEPByCabinet(Integer integer) {
        return entryEPRepository.findEntryEPByCabinet(integer).stream()
                .map(MappingUtils::mapEntryEPToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EntryEPDTO> findEntryEPByLearningCampusContainingIgnoreCase(String campus) {
        return entryEPRepository.findEntryEPByLearningCampusContainingIgnoreCase(campus).stream()
                .map(MappingUtils::mapEntryEPToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EntryEPDTO> findEntryEPByNameOfEmployeeIbContainingIgnoreCase(String name) {
        return entryEPRepository.findEntryEPByNameOfEmployeeIbContainingIgnoreCase(name).stream()
                .map(MappingUtils::mapEntryEPToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EntryEPDTO> findEntryEPByTypeContainingIgnoreCase(String type) {
        return entryEPRepository.findEntryEPByTypeContainingIgnoreCase(type).stream()
                .map(MappingUtils::mapEntryEPToDto)
                .collect(Collectors.toList());
    }


}
