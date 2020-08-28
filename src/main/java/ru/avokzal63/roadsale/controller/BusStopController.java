package ru.avokzal63.roadsale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.avokzal63.roadsale.domain.BusStop;
import ru.avokzal63.roadsale.repos.BusStopRepo;
import ru.avokzal63.roadsale.service.BusStopService;

import java.util.Map;

@Controller
@RequestMapping("/bus-stop")
public class BusStopController {
    @Autowired
    private BusStopService busStopService;
    @Autowired
    private BusStopRepo busStopRepo;

    @GetMapping
    public String busStopList(Model model) {
        model.addAttribute("busStops", busStopService.findAll());
        return "busStopList";
    }

    @PostMapping
    public String addBusStop(BusStop busStop,
                             Model model,
                             BindingResult bindingResult) {
        model.addAttribute("busStops", busStopService.findAll());
        if (busStopRepo.findByName(busStop.getName()).size() !=
                0) {
            model.addAttribute("nameError", "Остановка с таким названием уже существует!");
            model.addAttribute("thisName", busStop.getName());
            return "busStopList";
        }
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("thisName", busStop.getName());

            return "busStopList";
        }
        busStopService.addBusStop(busStop);
        return "redirect:/bus-stop";
    }
}
