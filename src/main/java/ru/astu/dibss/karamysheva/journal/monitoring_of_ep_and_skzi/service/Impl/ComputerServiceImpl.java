package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.CarrierDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.ComputerDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.Carrier;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.Computer;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.repository.ComputerRepository;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.ComputerService;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.utils.mappingUtils.MappingUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComputerServiceImpl implements ComputerService {
    private ComputerRepository computerRepository;

    @Autowired
    public ComputerServiceImpl(ComputerRepository computerRepository) {
        this.computerRepository = computerRepository;
    }

    @Override
    public List<ComputerDTO> findAll() {
        return computerRepository.findAll().stream()
                .map(MappingUtils::mapComputerToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ComputerDTO findByID(Integer id) {
        Optional<Computer> computerOptional = computerRepository.findById(id);
        return computerOptional.map(MappingUtils::mapComputerToDto).orElseGet(ComputerDTO::new);
    }


    @Override
    @Transactional
    public void deleteById(Integer id) {
        computerRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void save(ComputerDTO dto) {
        Computer entity = MappingUtils.mapDtoToComputer(dto);
        computerRepository.save(entity);
    }

    @Override
    public List<ComputerDTO> findComputerByCabinet(String cabinet) {
        return computerRepository.findComputerByCabinet(cabinet).stream()
                .map(MappingUtils::mapComputerToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ComputerDTO> findComputerByDepartmentContainingIgnoreCase(String department) {
        return computerRepository.findComputerByDepartmentContainingIgnoreCase(department).stream()
                .map(MappingUtils::mapComputerToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ComputerDTO> findComputerByLearningCampusContainingIgnoreCase(String campus) {
        return computerRepository.findComputerByLearningCampusContainingIgnoreCase(campus).stream()
                .map(MappingUtils::mapComputerToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ComputerDTO> findComputerByMarkingContainingIgnoreCase(String marking) {
        return computerRepository.findComputerByMarkingContainingIgnoreCase(marking).stream()
                .map(MappingUtils::mapComputerToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Computer> findComputersByIds(List<Integer> computerIds) {
        Iterable<Computer> computerIterable = computerRepository.findAllById(computerIds);
        List<Computer> computers = new ArrayList<>();
        computerIterable.forEach(computers::add);
        return computers;
    }

}
