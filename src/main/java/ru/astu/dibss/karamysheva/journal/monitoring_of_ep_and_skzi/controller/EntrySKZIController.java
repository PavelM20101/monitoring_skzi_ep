package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.EntrySKZIDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.EntrySKZIService;

import java.util.List;

@RestController
@RequestMapping("/entryskzi")
@Validated
public class EntrySKZIController {
    private EntrySKZIService entrySKZIService;

    @Autowired
    public EntrySKZIController(EntrySKZIService entrySKZIService) {
        this.entrySKZIService = entrySKZIService;
    }

    @GetMapping("/findByNameIb/{name}")
    public ResponseEntity<List<EntrySKZIDTO>> getAllEntryEPWithContainsName(@PathVariable String name){
        List<EntrySKZIDTO> entrySKZIListDto = entrySKZIService.findEntrySKZIByNameIbContainingIgnoreCase(name);
        return ResponseEntity.ok(entrySKZIListDto);
    }

    @GetMapping("/findByType/{type}")
    public ResponseEntity<List<EntrySKZIDTO>> getAllEntryEPWithContainsType(@PathVariable String type){
        List<EntrySKZIDTO> entrySKZIListDto = entrySKZIService.findEntrySKZIByTypeContainingIgnoreCase(type);
        return ResponseEntity.ok(entrySKZIListDto);
    }

    @GetMapping
    public ResponseEntity<List<EntrySKZIDTO>> getAllEntrySKZIs() {
        List<EntrySKZIDTO> entrySKZIListDto = entrySKZIService.findAll();
        return ResponseEntity.ok(entrySKZIListDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntrySKZIDTO> getEntrySKZI(@PathVariable Integer id){
        EntrySKZIDTO entrySKZIDto = entrySKZIService.findById(id);
        return ResponseEntity.ok(entrySKZIDto);
    }

    @PostMapping
    public ResponseEntity<EntrySKZIDTO> addEntrySKZI(@RequestBody EntrySKZIDTO entrySKZIDto) {
        entrySKZIService.save(entrySKZIDto);
        return ResponseEntity.ok(entrySKZIDto);
    }

    @PutMapping
    public ResponseEntity<EntrySKZIDTO> updateEntrySKZI(@RequestBody EntrySKZIDTO entrySKZIDto) {
        entrySKZIService.save(entrySKZIDto);
        return ResponseEntity.ok(entrySKZIDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEntrySKZI(@PathVariable Integer id) {
        entrySKZIService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
