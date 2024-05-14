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
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.EmployeeDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employeeListDto = employeeService.findAll();
        return ResponseEntity.ok(employeeListDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Integer id){
            EmployeeDTO employeeDTO = employeeService.findById(id);
            return ResponseEntity.ok(employeeDTO);
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.save(employeeDTO);
        return ResponseEntity.ok(employeeDTO);
    }

    @PutMapping
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.save(employeeDTO);
        return ResponseEntity.ok(employeeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
