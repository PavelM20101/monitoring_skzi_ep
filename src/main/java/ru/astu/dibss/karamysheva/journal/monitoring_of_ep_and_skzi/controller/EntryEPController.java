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
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.EntryEPDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.EntryEPService;

import java.util.List;

@RestController
@RequestMapping("/entryep")
public class EntryEPController {
    private EntryEPService entryEPService;

    @Autowired
    public EntryEPController(EntryEPService entryEPService) {
        this.entryEPService = entryEPService;
    }

    @GetMapping("/findByNameIb/{name}")
    public ResponseEntity<List<EntryEPDTO>> getAllEntryEPWithContainsName(@PathVariable String name){
        List<EntryEPDTO> entryEPListDto = entryEPService.findEntryEPByNameOfEmployeeIbContainingIgnoreCase(name);
        return ResponseEntity.ok(entryEPListDto);
    }

    @GetMapping("/findByCabinet/{cabinet}")
    public ResponseEntity<List<EntryEPDTO>> getAllEntryEPWithContainsCabinet(@PathVariable Integer cabinet){
        List<EntryEPDTO> entryEPListDto = entryEPService.findEntryEPByCabinet(cabinet);
        return ResponseEntity.ok(entryEPListDto);
    }

    @GetMapping("/findByCampus/{campus}")
    public ResponseEntity<List<EntryEPDTO>> getAllEntryEPWithContainsCampus(@PathVariable String campus){
        List<EntryEPDTO> entryEPListDto = entryEPService.findEntryEPByLearningCampusContainingIgnoreCase(campus);
        return ResponseEntity.ok(entryEPListDto);
    }

    @GetMapping("/findByType/{type}")
    public ResponseEntity<List<EntryEPDTO>> getAllEntryEPWithContainsType(@PathVariable String type){
        List<EntryEPDTO> entryEPListDto = entryEPService.findEntryEPByTypeContainingIgnoreCase(type);
        return ResponseEntity.ok(entryEPListDto);
    }

    @GetMapping
    public ResponseEntity<List<EntryEPDTO>> getAllEntryEPs() {
        List<EntryEPDTO> entryEPListDto = entryEPService.findAll();
        return ResponseEntity.ok(entryEPListDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntryEPDTO> getEntryEP(@PathVariable Integer id){
        EntryEPDTO entryEPDto = entryEPService.findById(id);
        return ResponseEntity.ok(entryEPDto);
    }

    @PostMapping
    public ResponseEntity<EntryEPDTO> addEntryEP(@RequestBody EntryEPDTO entryEPDto) {
        entryEPService.save(entryEPDto);
        return ResponseEntity.ok(entryEPDto);
    }

    @PutMapping
    public ResponseEntity<EntryEPDTO> updateEntryEP(@RequestBody EntryEPDTO entryEPDto) {
        entryEPService.save(entryEPDto);
        return ResponseEntity.ok(entryEPDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEntryEP(@PathVariable Integer id) {
        entryEPService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
