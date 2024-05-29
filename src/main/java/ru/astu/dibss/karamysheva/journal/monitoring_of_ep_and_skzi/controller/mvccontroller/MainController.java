package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.controller.mvccontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.dto.SertificateDTO;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.SertificateService;

import java.util.List;

@Controller
public class MainController {
    private final SertificateService sertificateService;

    @Autowired
    public MainController(SertificateService sertificateService) {
        this.sertificateService = sertificateService;
    }

    @GetMapping("/main")
    public String showMainPage(Model model) {
        List<SertificateDTO> expiringSoonSertificates = sertificateService.findSertificatesExpiringSoon(); // Предполагается, что у вас есть метод для этого
        model.addAttribute("expiringSoonSertificates", expiringSoonSertificates);
        return "main";
    }

    @GetMapping("/home")
    public String redirectToHomePage() {
        return "redirect:/main";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }
}
