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
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.ComputerDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.ComputerService;

import java.util.List;

@RestController
@RequestMapping("/computer")
public class ComputerController {
    private final ComputerService computerService;

    @Autowired
    public ComputerController(ComputerService computerService) {
        this.computerService = computerService;
    }

    @GetMapping
    public ResponseEntity<List<ComputerDTO>> getAllComputers() {
        List<ComputerDTO> computerDTOList = computerService.findAll();
        return ResponseEntity.ok(computerDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComputerDTO> getComputer(@PathVariable Integer id){
        ComputerDTO computerDTO = computerService.findByID(id);
        return ResponseEntity.ok(computerDTO);
    }

    @PostMapping
    public ResponseEntity<ComputerDTO> addComputer(@RequestBody ComputerDTO computerDTO) {
        computerService.save(computerDTO);
        return ResponseEntity.ok(computerDTO);
    }

    @PutMapping
    public ResponseEntity<ComputerDTO> updateComputer(@RequestBody ComputerDTO computerDTO) {
        computerService.save(computerDTO);
        return ResponseEntity.ok(computerDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteComputer(@PathVariable Integer id) {
        computerService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
