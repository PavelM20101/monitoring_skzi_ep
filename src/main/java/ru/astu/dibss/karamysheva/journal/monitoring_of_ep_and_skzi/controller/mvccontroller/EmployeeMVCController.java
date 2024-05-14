package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.controller.mvccontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.EmployeeDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.EmployeeService;

import java.util.List;

@Controller
@RequestMapping("/mvc")
public class EmployeeMVCController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeMVCController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public String showEmployeePage(Model model) {
        List<EmployeeDTO> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "employees";
    }
    @GetMapping("/employee/view/{id}")
    public String viewEmployee(@PathVariable Integer id, Model model){
        EmployeeDTO employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        return "employee_view";
    }
    @GetMapping("/employee/edit/{id}")
    public String showEditEmployeeForm(@PathVariable Integer id, Model model){
        EmployeeDTO employeeDTO = employeeService.findById(id);
        model.addAttribute("employee", employeeDTO);
        return "employee_edit";
    }
    @PostMapping("/employee/edit")
    public String editEmployee(@ModelAttribute EmployeeDTO employeeDTO){
        employeeService.save(employeeDTO);
        return "redirect:/mvc/employees";
    }
    @GetMapping("/employee/add")
    public String showAddEmployeeForm(Model model){
        model.addAttribute("employee", new EmployeeDTO());
        return "employee_add";
    }
    @PostMapping("/employee/add")
    public String addEmployee(@ModelAttribute EmployeeDTO employeeDTO){
        employeeService.save(employeeDTO);
        return "redirect:/mvc/employees";
    }

    @GetMapping("/employee/delete/{id}")
    public String deleteEmployee(@PathVariable Integer id){
        employeeService.deleteById(id);
        return "redirect:/mvc/employees";
    }
    @GetMapping("/employees/search")
    public String searchEmployees(@RequestParam String parameter, @RequestParam String value, Model model){
        List<EmployeeDTO> employees;
        switch (parameter){
            case "department":
                employees = employeeService.findEmployeeByDepartmentContainsIgnoreCase(value);
                break;
            case "campus":
                employees = employeeService.findEmployeeByLearningCampusContainsIgnoreCase(value);
                break;
            case "post":
                employees = employeeService.findEmployeeByPostContainingIgnoreCase(value);
                break;
            case "name":
                employees = employeeService.findEmployeeByNameContainingIgnoreCase(value);
                break;
            default:
                employees = employeeService.findAll();
        }
        model.addAttribute("employees", employees);
        return "employees";
    }
}
