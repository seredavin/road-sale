package ru.avokzal63.roadsale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.avokzal63.roadsale.service.RouteService;
import ru.avokzal63.roadsale.service.TripService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/trip")
public class TripController {
    @Autowired
    private RouteService routeService;
    @Autowired
    private TripService tripService;

    @GetMapping
    public String tripList(Model model) {
        model.addAttribute("trips", tripService.findAll());
        model.addAttribute("routes", routeService.findAll());
        return "tripList";
    }

    @PostMapping
    public String addtrip(Model model,
                          @RequestParam Map<String, String> form) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(form.get("date"));
        tripService.addTrip(routeService.getById(Integer.parseInt(form.get("route"))), date);


        return "redirect:/trip";
    }
}
