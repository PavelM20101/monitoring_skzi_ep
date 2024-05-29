package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.CarrierDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.Carrier;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.Employee;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.repository.CarrierRepository;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.repository.EmployeeRepository;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.CarrierService;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.utils.mappingUtils.MappingUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CarrierServiceImpl implements CarrierService {
    private CarrierRepository carrierRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public CarrierServiceImpl(CarrierRepository carrierRepository, EmployeeRepository employeeRepository) {
        this.carrierRepository = carrierRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<CarrierDTO> findAll() {
        return carrierRepository.findAll().stream()
                .map(MappingUtils::mapCarrierToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CarrierDTO findById(Integer id) {
        Optional<Carrier> carrierOptional = carrierRepository.findById(id);
        return carrierOptional.map(MappingUtils::mapCarrierToDto).orElseGet(CarrierDTO::new);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        carrierRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void save(CarrierDTO dto) {
        Carrier entity = MappingUtils.mapDtoToCarrier(dto);
        carrierRepository.save(entity);
    }

    @Override
    public List<CarrierDTO> findCarrierByMarkirovkaContainingIgnoreCase(String markirovka) {
        return carrierRepository.findCarrierByMarkirovkaContainingIgnoreCase(markirovka).stream()
                .map(MappingUtils::mapCarrierToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarrierDTO> findCarrierBySerialNumberContainingIgnoreCase(String ser) {
        return carrierRepository.findCarrierBySerialNumberContainingIgnoreCase(ser).stream()
                .map(MappingUtils::mapCarrierToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarrierDTO> findCarrierByTypeContainingIgnoreCase(String type) {
        return carrierRepository.findCarrierByTypeContainingIgnoreCase(type).stream()
                .map(MappingUtils::mapCarrierToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Carrier> findCarriersByIds(List<Integer> carrierIds) {
        Iterable<Carrier> carrierIterable = carrierRepository.findAllById(carrierIds);
        List<Carrier> carriers = new ArrayList<>();
        carrierIterable.forEach(carriers::add);
        return carriers;
    }

}
