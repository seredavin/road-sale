package ru.avokzal63.roadsale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.avokzal63.roadsale.service.dto.TicketService;

@Controller
@RequestMapping
public class MainController {
    @Autowired
    TicketService ticketService;

    @GetMapping("/")
    public String getCred(Model model) {
        return "ofd";
    }

    @GetMapping("/ofd")
    public String getOfd(@RequestParam(required = false, defaultValue = "") String ticketNumber, Model model) {
        try {
            if (!ticketNumber.isEmpty() && ticketService.getLinkById(ticketNumber) != null) {
                model.addAttribute("ticket", ticketService.getLinkById(ticketNumber));
                model.addAttribute("ticketNumber", ticketNumber);
                model.addAttribute("qrString", ticketService.getQrFromId(ticketNumber));
            }
        } catch (NullPointerException e) {
            model.addAttribute("error", "Чека еще нет в системе");
        }
        return "ofd";
    }
}
