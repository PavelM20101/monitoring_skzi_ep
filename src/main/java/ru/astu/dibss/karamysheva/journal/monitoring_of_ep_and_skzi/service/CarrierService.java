package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service;

import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.CarrierDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.Carrier;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.Employee;

import java.util.List;

public interface CarrierService {
    List<CarrierDTO> findAll();

    CarrierDTO findById(Integer id);

    void deleteById(Integer id);

    void save(CarrierDTO entity);
//    void saveWithEmployeeIds(CarrierDTO dto, List<Integer> employeeIds);
    List<CarrierDTO> findCarrierByMarkirovkaContainingIgnoreCase(String markirovka);
    List<CarrierDTO> findCarrierBySerialNumberContainingIgnoreCase(String ser);
    List<CarrierDTO> findCarrierByTypeContainingIgnoreCase(String type);

    List<Carrier> findCarriersByIds(List<Integer> carrierIds);
}
