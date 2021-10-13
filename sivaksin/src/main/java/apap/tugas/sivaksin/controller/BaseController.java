package apap.tugas.sivaksin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {
    @GetMapping("/")
    private String home() { return "home"; }
}
