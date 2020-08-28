package ru.avokzal63.roadsale.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.avokzal63.roadsale.domain.*;
import ru.avokzal63.roadsale.repos.dto.CashierRepo;
import ru.avokzal63.roadsale.service.BusStopService;
import ru.avokzal63.roadsale.service.TripService;
import ru.avokzal63.roadsale.service.dto.CashierService;
import ru.avokzal63.roadsale.service.dto.ChequeService;
import ru.avokzal63.roadsale.service.dto.OfdConfigureService;
import ru.avokzal63.roadsale.service.dto.TicketService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/sale")
public class SaleController {
    @Autowired
    OfdConfigureService ofdConfigureService;
    @Autowired
    private CashierRepo cashierRepo;
    @Autowired
    TicketService ticketService;
    @Autowired
    private ChequeService chequeService;
    @Autowired
    private TripService tripService;
    @Autowired
    private BusStopService busStopService;
    @Autowired
    private CashierService cashierService;

    @PostMapping
    public String addTicket(@RequestParam Map<String, String> form) throws JsonProcessingException, ParseException {
        Ticket ticket = new Ticket();
        ticket.setId(form.get("number"));
        ticket.setStartPoint(busStopService.findBuId(Integer.parseInt(form.get("startPoint"))));
        ticket.setEndPoint(busStopService.findBuId(Integer.parseInt(form.get("endPoint"))));
        ticket.setTrip(tripService.findById(Integer.parseInt(form.get("trip"))));
        ticket.setType(TickerType.PASSENGER);
        ticket.setPrice(Double.parseDouble(form.get("price")));
        Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(form.get("date"));
        ticket.setDate(date);
        ticket.setOfdConfigure(ofdConfigureService.getOfdConfigureById(1));
        Item item = new Item(ticket);
        PaymentItem paymentItem = new PaymentItem(item);
        Cheque cheque = new Cheque(ticket, item, paymentItem);
        cheque.setCashier(cashierRepo.findOneById(Integer.parseInt(form.get("cashier"))));
        if (chequeService.ifExist(cheque)) {
            return "chequeList";
        }
        cheque = chequeService.saveCheque(cheque);
        ticket.setCheque(cheque);
        ticketService.addTicket(ticket);
        cheque.setReceiptId(chequeService.send(cheque));
        chequeService.updateCheque(cheque);
        return "redirect:/sale";
    }

    @GetMapping
    public String chequesList(Model model) {
        model.addAttribute("trips", tripService.findAll());
        model.addAttribute("busStops", busStopService.findAll());
        model.addAttribute("tickets", ticketService.findAll());
        model.addAttribute("cashiers", cashierService.findAll());
        return "chequeList";
    }

    @GetMapping("/{id}")
    public String getStatus(@PathVariable String id) {
        return "index";
    }
}
