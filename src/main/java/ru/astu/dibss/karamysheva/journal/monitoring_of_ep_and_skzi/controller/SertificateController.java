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
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.SertificateDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.SertificateService;

import java.util.List;

@RestController
@RequestMapping("/sertificate")
public class SertificateController {
    private SertificateService sertificateService;

    @Autowired
    public SertificateController(SertificateService sertificateService) {
        this.sertificateService = sertificateService;
    }

    @GetMapping
    public ResponseEntity<List<SertificateDTO>> getAllSertificates() {
        List<SertificateDTO> sertificateListDto = sertificateService.findAll();
        return ResponseEntity.ok(sertificateListDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SertificateDTO> getSertificate(@PathVariable Integer id){
        SertificateDTO sertificateDTO = sertificateService.findById(id);
        return ResponseEntity.ok(sertificateDTO);
    }

    @PostMapping
    public ResponseEntity<SertificateDTO> addSertificate(@RequestBody SertificateDTO sertificateDTO) {
        sertificateService.save(sertificateDTO);
        return ResponseEntity.ok(sertificateDTO);
    }

    @PutMapping
    public ResponseEntity<SertificateDTO> updateSertificate(@RequestBody SertificateDTO sertificateDTO) {
        sertificateService.save(sertificateDTO);
        return ResponseEntity.ok(sertificateDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSertificate(@PathVariable Integer id) {
        sertificateService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
