package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.controller.mvccontroller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.CarrierDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.ComputerDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.EmployeeDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.EntryEPDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.SertificateDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.Carrier;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.Computer;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.Employee;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.Sertificate;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.CarrierService;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.ComputerService;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.EmployeeService;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.EntryEPService;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.SertificateService;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.utils.mappingUtils.MappingUtils;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/mvc")
public class EntryEPMVCController {
    private final EntryEPService entryEPService;
    private final CarrierService carrierService;
    private final EmployeeService employeeService;
    private final ComputerService computerService;
    private final SertificateService sertificateService;

    @Autowired
    public EntryEPMVCController(EntryEPService entryEPService,
                                CarrierService carrierService,
                                EmployeeService employeeService,
                                ComputerService computerService,
                                SertificateService sertificateService) {
        this.entryEPService = entryEPService;
        this.carrierService = carrierService;
        this.employeeService = employeeService;
        this.computerService = computerService;
        this.sertificateService = sertificateService;
    }

    @GetMapping("/entryeps")
    public String showEntryEPPage(Model model) {
        List<EntryEPDTO> entryEPs = entryEPService.findAll();
        model.addAttribute("entryeps", entryEPs);
        return "entry-eps";
    }
    @GetMapping("/entryep/view/{id}")
    public String viewEntryEP(@PathVariable Integer id, Model model){
        EntryEPDTO entryEPDTO = entryEPService.findById(id);
        model.addAttribute("entryep", entryEPDTO);
        return "entry-ep_view";
    }
    @GetMapping("/entryep/edit/{id}")
    public String showEditEntryEPForm(@PathVariable Integer id, Model model){
        EntryEPDTO entryEPDTO = entryEPService.findById(id);
        model.addAttribute("entryep", entryEPDTO);
        model.addAttribute("carriers", carrierService.findAll());
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("computers", computerService.findAll());
        model.addAttribute("sertificates", sertificateService.findAll());
        return "entry-ep_edit";
    }
    @PostMapping("/entryep/edit")
    public String editEntryEP(@Valid @ModelAttribute EntryEPDTO entryEPDTO,
                              @RequestParam(name = "carrierIds", required = false) List<Integer> carrierIds,
                              @RequestParam(name = "employeeIds", required = false) List<Integer> employeeIds,
                              @RequestParam(name = "computerIds", required = false) List<Integer> computerIds,
                              @RequestParam(name = "sertificateIds", required = false)List<Integer> sertificateIds){
        EntryEPDTO existingEntryEP = entryEPService.findById(entryEPDTO.getId());

        if(carrierIds == null){
            entryEPDTO.setCarriers(existingEntryEP.getCarriers());
        } else {
            List<CarrierDTO> carriers = carrierIds.stream()
                    .map(carrierService::findById)
                    .collect(Collectors.toList());
            entryEPDTO.setCarriers(carriers);
        }

        if(employeeIds == null){
            entryEPDTO.setEmployees(existingEntryEP.getEmployees());
        } else {
            List<EmployeeDTO> employees = employeeIds.stream()
                    .map(employeeService::findById)
                    .collect(Collectors.toList());
            entryEPDTO.setEmployees(employees);
        }

        if(computerIds == null){
            entryEPDTO.setComputers(existingEntryEP.getComputers());
        } else {
            List<ComputerDTO> computers = computerIds.stream()
                    .map(computerService::findByID)
                    .collect(Collectors.toList());
            entryEPDTO.setComputers(computers);
        }

        if(sertificateIds == null){
            entryEPDTO.setSertificates(existingEntryEP.getSertificates());
        } else {
            List<SertificateDTO> sertificates = sertificateIds.stream()
                    .map(sertificateService::findById)
                    .collect(Collectors.toList());
            entryEPDTO.setSertificates(sertificates);
        }

        entryEPService.save(entryEPDTO);
        return "redirect:/mvc/entryeps";
    }
    @GetMapping("/entryep/add")
    public String showAddEntryEPForm(Model model){
        model.addAttribute("entryep", new EntryEPDTO());
        model.addAttribute("carriers", carrierService.findAll());
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("computers", computerService.findAll());
        model.addAttribute("sertificates", sertificateService.findAll());
        return "entry-ep_add";
    }
    @PostMapping("/entryep/add")
    public String addEntryEP(@Valid @ModelAttribute EntryEPDTO entryEPDTO,
                             @RequestParam(name = "carrierIds", required = false) List<Integer> carrierIds,
                             @RequestParam(name = "employeeIds", required = false) List<Integer> employeeIds,
                             @RequestParam(name = "computerIds", required = false) List<Integer> computerIds,
                             @RequestParam(name = "sertificateIds", required = false)List<Integer> sertificateIds){
        if(carrierIds != null){
            List<Carrier> carriers = carrierService.findCarriersByIds(carrierIds);
            entryEPDTO.setCarriers(carriers.stream().map(MappingUtils::mapCarrierToDto).collect(Collectors.toList()));
        }
        if(employeeIds != null){
            List<Employee> employees = employeeService.findEmployeesByIds(employeeIds);
            entryEPDTO.setEmployees(employees.stream().map(MappingUtils::mapEmployeeToDto).collect(Collectors.toList()));
        }
        if(computerIds != null){
            List<Computer> computers = computerService.findComputersByIds(computerIds);
            entryEPDTO.setComputers(computers.stream().map(MappingUtils::mapComputerToDto).collect(Collectors.toList()));
        }
        if(sertificateIds != null){
            List<Sertificate> sertificates = sertificateService.findSertificatesByIds(sertificateIds);
            entryEPDTO.setSertificates(sertificates.stream().map(MappingUtils::mapSertificateToDto).collect(Collectors.toList()));
        }
        entryEPService.save(entryEPDTO);
        return "redirect:/mvc/entryeps";
    }
    @GetMapping("/entryep/delete/{id}")
    public String deleteEntryEP(@PathVariable Integer id){
        entryEPService.deleteById(id);
        return "redirect:/mvc/entryeps";
    }
    @GetMapping("/entryeps/search")
    public String searchEntryEPs(@RequestParam String parameter, @RequestParam String value, Model model){
        List<EntryEPDTO> entryEPDTOS;
        switch (parameter){
            case "cabinet":
                entryEPDTOS = entryEPService.findEntryEPByCabinet(Integer.valueOf(value));
                break;
            case "campus":
                entryEPDTOS = entryEPService.findEntryEPByLearningCampusContainingIgnoreCase(value);
                break;
            case "name":
                entryEPDTOS = entryEPService.findEntryEPByNameOfEmployeeIbContainingIgnoreCase(value);
                break;
            case "type":
                entryEPDTOS = entryEPService.findEntryEPByTypeContainingIgnoreCase(value);
                break;
            default:
                entryEPDTOS = entryEPService.findAll();
        }
        model.addAttribute("entryeps", entryEPDTOS);
        return "entry-eps";
    }
}
