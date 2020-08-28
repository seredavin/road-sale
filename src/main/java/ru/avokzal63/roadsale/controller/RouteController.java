package ru.avokzal63.roadsale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.avokzal63.roadsale.domain.Route;
import ru.avokzal63.roadsale.repos.RouteRepo;
import ru.avokzal63.roadsale.service.BusStopService;
import ru.avokzal63.roadsale.service.RouteService;

import java.util.Map;

@Controller
@RequestMapping("/route")
public class RouteController {
    @Autowired
    private RouteService routeService;
    @Autowired
    private RouteRepo routeRepo;
    @Autowired
    private BusStopService busStopService;

    @GetMapping
    public String routeList(Model model) {
            model.addAttribute("routes", routeService.findAll());
        model.addAttribute("busStops", busStopService.findAll());
        return "routeList";
    }

    @PostMapping
    public String addRoute(Route route,
                           Model model,
                           BindingResult bindingResult) {
        model.addAttribute("routes", routeService.findAll());
        model.addAttribute("busStops", busStopService.findAll());
        if (routeRepo.findByNumberAndStartPointAndEndPoint(route.getNumber(),
                route.getStartPoint(),
                route.getEndPoint()).size() != 0) {
            model.addAttribute("Error", "Такой маршрут уже существует!");
            model.addAttribute("thisNumber", route.getNumber());
            model.addAttribute("thisStartPoint", route.getStartPoint());
            model.addAttribute("thisEndPoint", route.getEndPoint());
            return "routeList";
        }
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("thisNumber", route.getNumber());
            model.addAttribute("thisStartPoint", route.getStartPoint());
            model.addAttribute("thisEndPoint", route.getEndPoint());
            return "routeList";
        }
        routeService.addRoute(route);
        return "redirect:/route";
    }

    @GetMapping("{id}")
    public String editRoute(@PathVariable("id") Route route,
                            Model model) {
        model.addAttribute("route", route);
        model.addAttribute("busStops", busStopService.findAll());
        return "routeEdit";
    }

    @PostMapping("{id}")
    public String updateRoute(@PathVariable("id") Route route,
                              Model model,
                              @RequestParam Map<String, String> form) {
        if (form.containsKey("addPoint")) {
            routeService.updateRoute(route,
                    Integer.parseInt(form.get("postPoint")),
                    busStopService.findBuId(Integer.parseInt(form.get("addPoint"))));
            return "redirect:/route/" + route.getId();
        } else {
            routeService.updateRoute(route, form);
        }
        return "redirect:/route";
    }
}
