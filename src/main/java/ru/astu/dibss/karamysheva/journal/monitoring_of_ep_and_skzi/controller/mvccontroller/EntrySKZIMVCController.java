package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.controller.mvccontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.model.IModel;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.CarrierDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.EntrySKZIDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.Carrier;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.CarrierService;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.EntrySKZIService;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.utils.mappingUtils.MappingUtils;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/mvc")
public class EntrySKZIMVCController {
    private final EntrySKZIService entrySKZIService;
    private final CarrierService carrierService;

    @Autowired
    public EntrySKZIMVCController(EntrySKZIService entrySKZIService, CarrierService carrierService) {
        this.entrySKZIService = entrySKZIService;
        this.carrierService = carrierService;
    }

    @GetMapping("/entryskzis")
    public String showEntrySKZIPage(Model model) {
        List<EntrySKZIDTO> entrySKZIs = entrySKZIService.findAll();
        model.addAttribute("entryskzis", entrySKZIs);
        return "entry-skzis";
    }
    @GetMapping("/entryskzi/view/{id}")
    public String viewEntrySKZI(@PathVariable Integer id, Model model){
        EntrySKZIDTO entrySKZIDTO = entrySKZIService.findById(id);
        model.addAttribute("entryskzi", entrySKZIDTO);
        return "entry-skzi_view";
    }
    @GetMapping("/entryskzi/edit/{id}")
    public String showEditEntrySKZIForm(@PathVariable Integer id, Model model){
        EntrySKZIDTO entrySKZIDTO = entrySKZIService.findById(id);
        model.addAttribute("entryskzi", entrySKZIDTO);
        model.addAttribute("carriers", carrierService.findAll());
        return "entry-skzi_edit";
    }
    @PostMapping("/entryskzi/edit")
    public String editEntrySKZI(@ModelAttribute EntrySKZIDTO entrySKZIDTO,
                                @RequestParam(name = "carrierIds", required = false) List<Integer> carrierIds){
        EntrySKZIDTO existingEntrySKZI = entrySKZIService.findById(entrySKZIDTO.getId());

        if(carrierIds == null){
            entrySKZIDTO.setCarriers(existingEntrySKZI.getCarriers());
        } else {
            List<CarrierDTO> carriers = carrierIds.stream()
                    .map(carrierService::findById)
                    .collect(Collectors.toList());
            entrySKZIDTO.setCarriers(carriers);
        }
        entrySKZIService.save(entrySKZIDTO);
        return "redirect:/mvc/entryskzis";
    }
    @GetMapping("/entryskzi/add")
    public String showAddEntrySKZIForm(Model model){
        model.addAttribute("entryskzi", new EntrySKZIDTO());
        model.addAttribute("carriers", carrierService.findAll());
        return "entry-skzi_add";
    }
    @PostMapping("/entryskzi/add")
    public String addEntrySKZI(@ModelAttribute EntrySKZIDTO entrySKZIDTO,
                               @RequestParam(name = "carrierIds", required = false) List<Integer> carrierIds){
        if (carrierIds != null){
            List<Carrier> carriers = carrierService.findCarriersByIds(carrierIds);
            entrySKZIDTO.setCarriers(carriers.stream().map(MappingUtils::mapCarrierToDto).collect(Collectors.toList()));
        }
        entrySKZIService.save(entrySKZIDTO);
        return "redirect:/mvc/entryskzis";
    }
    @GetMapping("/entryskzi/delete/{id}")
    public String deleteEntrySKZI(@PathVariable Integer id){
        entrySKZIService.deleteById(id);
        return "redirect:/mvc/entryskzis";
    }
    @GetMapping("/entryskzis/search")
    public String searchEntrySKZIs(@RequestParam String parameter, @RequestParam String value, Model model){
        List<EntrySKZIDTO> entrySKZIDTOS;
        switch (parameter){
            case "name":
                entrySKZIDTOS = entrySKZIService.findEntrySKZIByNameIbContainingIgnoreCase(value);
                break;
            case "type":
                entrySKZIDTOS = entrySKZIService.findEntrySKZIByTypeContainingIgnoreCase(value);
                break;
            default:
                entrySKZIDTOS = entrySKZIService.findAll();
        }
        model.addAttribute("entryskzis", entrySKZIDTOS);
        return "entry-skzis";
    }
}
