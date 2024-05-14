package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.controller.mvccontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.ComputerDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.ComputerService;

import java.util.List;

@Controller
@RequestMapping("/mvc")
public class ComputerMVCController {
    private final ComputerService computerService;

    @Autowired
    public ComputerMVCController(ComputerService computerService) {
        this.computerService = computerService;
    }

    @GetMapping("/computers")
    public String showComputerPage(Model model) {
        List<ComputerDTO> computers = computerService.findAll();
        model.addAttribute("computers", computers);
        return "computers";
    }
    @GetMapping("/computer/view/{id}")
    public String viewComputer(@PathVariable Integer id, Model model){
        ComputerDTO computer = computerService.findByID(id);
        model.addAttribute("computer", computer);
        return "computer_view";
    }
    @GetMapping("/computer/edit/{id}")
    public String showEditComputerForm(@PathVariable Integer id, Model model){
        ComputerDTO computerDTO = computerService.findByID(id);
        model.addAttribute("computer", computerDTO);
        return "computer_edit";
    }
    @PostMapping("/computer/edit")
    public String editComputer(@ModelAttribute ComputerDTO computerDTO){
        computerService.save(computerDTO);
        return "redirect:/mvc/computers";
    }
    @GetMapping("/computer/add")
    public String showAddComputerForm(Model model){
        model.addAttribute("computer", new ComputerDTO());
        return "computer_add";
    }
    @PostMapping("/computer/add")
    public String addComputer(@ModelAttribute ComputerDTO computerDTO){
        computerService.save(computerDTO);
        return "redirect:/mvc/computers";
    }
    @GetMapping("/computer/delete/{id}")
    public String deleteComputer(@PathVariable Integer id){
        computerService.deleteById(id);
        return "redirect:/mvc/computers";
    }
    @GetMapping("/computers/search")
    public String searchComputers(@RequestParam String parameter, @RequestParam String value, Model model){
        List<ComputerDTO> computers;
        switch (parameter){
            case "cabinet":
                computers = computerService.findComputerByCabinet(value);
                break;
            case "department":
                computers = computerService.findComputerByDepartmentContainingIgnoreCase(value);
                break;
            case "campus":
                computers = computerService.findComputerByLearningCampusContainingIgnoreCase(value);
                break;
            case "marking":
                computers = computerService.findComputerByMarkingContainingIgnoreCase(value);
                break;
            default:
                computers = computerService.findAll();
        }
        model.addAttribute("computers", computers);
        return "computers";
    }
}
