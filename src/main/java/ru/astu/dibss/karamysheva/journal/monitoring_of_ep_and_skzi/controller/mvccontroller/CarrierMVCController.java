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
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.EmployeeDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.Employee;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.CarrierService;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.EmployeeService;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.utils.mappingUtils.MappingUtils;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/mvc")
public class CarrierMVCController {
    private final CarrierService carrierService;
    private final EmployeeService employeeService;

    @Autowired
    public CarrierMVCController(CarrierService carrierService, EmployeeService employeeService) {
        this.carrierService = carrierService;
        this.employeeService = employeeService;
    }

    @GetMapping("/carriers")
    public String getAllCarriers(Model model) {
        model.addAttribute("carriers", carrierService.findAll());
        return "carriers";
    }

    @GetMapping("/view/{id}")
    public String viewCarrier(@PathVariable Integer id, Model model) {
        CarrierDTO carrier = carrierService.findById(id);
        model.addAttribute("carrier", carrier);
        return "carrier-view";
    }

    @GetMapping("/carrier/edit/{id}")
    public String showEditCarrierForm(@PathVariable Integer id, Model model) {
        CarrierDTO carrierDTO = carrierService.findById(id);
        model.addAttribute("carrier", carrierDTO);
        model.addAttribute("employees", employeeService.findAll());
        return "carrier_edit";
    }

    @PostMapping("/carrier/edit")
    public String editCarrier(@Valid @ModelAttribute CarrierDTO carrierDTO,
                              @RequestParam(name = "employeeIds", required = false) List<Integer> employeeIds) {
        // Получаем текущий перевозчик из базы данных
        CarrierDTO existingCarrier = carrierService.findById(carrierDTO.getId());

        // Если идентификаторы сотрудников не переданы, используем текущих сотрудников перевозчика
        if (employeeIds == null) {
            carrierDTO.setEmployees(existingCarrier.getEmployees());
        } else {
            // Иначе, получаем новых сотрудников из переданных идентификаторов
            List<EmployeeDTO> employees = employeeIds.stream()
                    .map(employeeService::findById)
                    .collect(Collectors.toList());
            carrierDTO.setEmployees(employees);
        }

        // Сохраняем обновленные данные перевозчика
        carrierService.save(carrierDTO);

        return "redirect:/mvc/carriers";
    }


    @GetMapping("/carrier/add")
    public String showAddCarrierForm(Model model) {
        model.addAttribute("carrier", new CarrierDTO());
        model.addAttribute("employees", employeeService.findAll());
        return "carrier_add";
    }

    @PostMapping("/carrier/add")
    public String addCarrier(@Valid @ModelAttribute CarrierDTO carrierDTO,
                             @RequestParam(name = "employeeIds", required = false) List<Integer> employeeIds) {
        if (employeeIds != null) {
            List<Employee> employees = employeeService.findEmployeesByIds(employeeIds);
            carrierDTO.setEmployees(employees.stream().map(MappingUtils::mapEmployeeToDto).collect(Collectors.toList()));
        }
        carrierService.save(carrierDTO);
        return "redirect:/mvc/carriers";
    }

    @GetMapping("/carrier/delete/{id}")
    public String deleteCarrier(@PathVariable Integer id) {
        carrierService.deleteById(id);
        return "redirect:/mvc/carriers";
    }

    @GetMapping("/carriers/search")
    public String searchCarriers(@RequestParam String parameter, @RequestParam String value, Model model) {
        List<CarrierDTO> carriers;
        switch (parameter) {
            case "type":
                carriers = carrierService.findCarrierByTypeContainingIgnoreCase(value);
                break;
            case "serialNumber":
                carriers = carrierService.findCarrierBySerialNumberContainingIgnoreCase(value);
                break;
            case "markirovka":
                carriers = carrierService.findCarrierByMarkirovkaContainingIgnoreCase(value);
                break;
            default:
                carriers = carrierService.findAll();
        }
        model.addAttribute("carriers", carriers);
        return "carriers";
    }

//    @GetMapping("/carriers/searchEmployees")
//    public String searchEmployees(@RequestParam String parameter, @RequestParam String value, Model model) {
//        List<EmployeeDTO> employees;
//        switch (parameter) {
//            case "department":
//                employees = employeeService.findEmployeeByDepartmentContainsIgnoreCase(value);
//                break;
//            case "campus":
//                employees = employeeService.findEmployeeByLearningCampusContainsIgnoreCase(value);
//                break;
//            case "name":
//                employees = employeeService.findEmployeeByNameContainingIgnoreCase(value);
//                break;
//            case "post":
//                employees = employeeService.findEmployeeByPostContainingIgnoreCase(value);
//                break;
//            default:
//                employees = new ArrayList<>();
//        }
//        model.addAttribute("employees", employees);
//        return "carrier_result_search_employees";
//    }
}

