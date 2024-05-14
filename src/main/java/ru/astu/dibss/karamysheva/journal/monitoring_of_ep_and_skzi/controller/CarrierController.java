package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.CarrierDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.CarrierService;

import java.util.List;

@RestController
@RequestMapping("/carrier")
public class CarrierController {
    private final CarrierService carrierService;

    @Autowired
    public CarrierController(CarrierService carrierService) {
        this.carrierService = carrierService;
    }

    @GetMapping
    public ResponseEntity<List<CarrierDTO>> getAllCarriers() {
        List<CarrierDTO> carrierListDTO = carrierService.findAll();
        return ResponseEntity.ok(carrierListDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarrierDTO> getCarrier(@PathVariable Integer id){
        CarrierDTO carrierDTO = carrierService.findById(id);
        return ResponseEntity.ok(carrierDTO);
    }

    @PostMapping
    public ResponseEntity<CarrierDTO> addCarrier(@RequestBody CarrierDTO carrierDTO) {
        carrierService.save(carrierDTO);
        return ResponseEntity.ok(carrierDTO);
    }

    @PutMapping
    public ResponseEntity<CarrierDTO> updateCarrier(@RequestBody CarrierDTO carrierDTO) {
        carrierService.save(carrierDTO);
        return ResponseEntity.ok(carrierDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCarrier(@PathVariable Integer id) {
        carrierService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
