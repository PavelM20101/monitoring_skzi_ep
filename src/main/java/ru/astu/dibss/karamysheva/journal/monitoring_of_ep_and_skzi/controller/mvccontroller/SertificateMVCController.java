package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.controller.mvccontroller;

import net.sf.jsqlparser.expression.operators.relational.MinorThanEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.SertificateDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.SertificateService;

import java.util.List;

@Controller
@RequestMapping("/mvc")
public class SertificateMVCController {
    private final SertificateService sertificateService;

    @Autowired
    public SertificateMVCController(SertificateService sertificateService) {
        this.sertificateService = sertificateService;
    }

    @GetMapping("/sertificates")
    public String showSertificatePage(Model model) {
        List<SertificateDTO> sertificates = sertificateService.findAll();
        List<SertificateDTO> expiringSoonSertificates = sertificateService.findSertificatesExpiringSoon();

        model.addAttribute("sertificates", sertificates);
        model.addAttribute("expiringSoonSertificates", expiringSoonSertificates);

        return "sertificates";
    }
    @GetMapping("/sertificate/view/{id}")
    public String viewSertificate(@PathVariable Integer id, Model model){
        SertificateDTO sertificate = sertificateService.findById(id);
        model.addAttribute("sertificate", sertificate);
        return "sertificate_view";
    }
    @GetMapping("/sertificate/edit/{id}")
    public String showEditSertificateForm(@PathVariable Integer id, Model model){
        SertificateDTO sertificateDTO = sertificateService.findById(id);
        model.addAttribute("sertificate", sertificateDTO);
        return "sertificate_edit";
    }
    @PostMapping("/sertificate/edit")
    public String editSertificate(@ModelAttribute SertificateDTO sertificateDTO){
        sertificateService.save(sertificateDTO);
        return "redirect:/mvc/sertificates";
    }
    @GetMapping("/sertificate/add")
    public String showAddSertificateForm(Model model){
        model.addAttribute("sertificate", new SertificateDTO());
        return "sertificate_add";
    }
    @PostMapping("/sertificate/add")
    public String addSertificate(@ModelAttribute SertificateDTO sertificateDTO){
        sertificateService.save(sertificateDTO);
        return "redirect:/mvc/sertificates";
    }
    @GetMapping("/sertificate/delete/{id}")
    public String deleteSertificate(@PathVariable Integer id){
        sertificateService.deleteById(id);
        return "redirect:/mvc/sertificates";
    }
    @GetMapping("/sertificates/search")
    public String searchSertificates(@RequestParam String parameter, @RequestParam String value, Model model){
        List<SertificateDTO> sertificates;
        switch (parameter){
            case "name":
                sertificates = sertificateService.findSertificateByNameContainingIgnoreCase(value);
                break;
            case "serial":
                sertificates = sertificateService.findSertificateBySerialNumberContainingIgnoreCase(value);
                break;
            case "center":
                sertificates = sertificateService.findSertificateByVerificationCenterContainingIgnoreCase(value);
                break;
            default:
                sertificates = sertificateService.findAll();
        }
        model.addAttribute("sertificates", sertificates);
        return "sertificates";
    }
}
