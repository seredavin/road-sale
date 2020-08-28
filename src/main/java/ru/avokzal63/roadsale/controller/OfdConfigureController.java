package ru.avokzal63.roadsale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.avokzal63.roadsale.domain.OfdConfigure;
import ru.avokzal63.roadsale.service.dto.OfdConfigureService;

@Controller
//@PreAuthorize("hasAnyAuthority('ADMIN')")
@RequestMapping("/ofd-config")
public class OfdConfigureController {
    @Autowired
    private OfdConfigureService ofdConfigureService;

    @PostMapping("{ofdConfigureId}")
    public String saveConfig(@PathVariable String ofdConfigureId,
                             OfdConfigure ofdConfigure) {
        ofdConfigureService.updateConfigure(ofdConfigure, ofdConfigureId);
        return "redirect:/ofd-config";
    }

    @GetMapping
    public String listConfig(Model model) {
        model.addAttribute("configures", ofdConfigureService.listConfigure());
        return "ofdConfigureList";
    }

    @GetMapping("{ofdId}")
    public String editConfig(@PathVariable String ofdId,
                             Model model) {
//        model.addAttribute("configure", ofdConfigure);
        model.addAttribute("configure", ofdConfigureService.getOfdConfigureById(
                Integer.parseInt(ofdId)));
        return "ofdConfigureEdit";
    }

}
