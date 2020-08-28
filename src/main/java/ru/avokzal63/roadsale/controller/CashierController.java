package ru.avokzal63.roadsale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.avokzal63.roadsale.domain.Cashier;
import ru.avokzal63.roadsale.domain.Role;
import ru.avokzal63.roadsale.repos.dto.CashierRepo;
import ru.avokzal63.roadsale.service.dto.CashierService;

import java.util.Map;

@Controller
@RequestMapping("/cashier")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class CashierController {
    @Autowired
    CashierService cashierService;
    @Autowired
    CashierRepo cashierRepo;

    @PostMapping
    public String addCashier(Cashier cashier,
                             Model model,
                             BindingResult bindingResult) {
        model.addAttribute("cashiers", cashierService.findAll());
        if (cashierRepo.countByName(cashier.getName()) != 0) {
            model.addAttribute("nameError", "Кассир с таким именем уже существует!");
            model.addAttribute("thisName", cashier.getName());
            return "cashierList";
        }
        if (!cashier.getInn().matches("\\d+")) {
            model.addAttribute("innError", "ИНН должно содержать только цифры!");
            model.addAttribute("thisInn", cashier.getInn());
            model.addAttribute("thisName", cashier.getName());
            return "cashierList";
        }
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("thisInn", cashier.getInn());
            model.addAttribute("thisName", cashier.getName());
            return "userList";
        }
        cashierService.addCashier(cashier);
        return "redirect:/cashier";
    }

    @PostMapping("{cashier}")
    public String updateCashier(Model model,
                                @RequestParam Map<String, String> form,
                                @PathVariable Cashier cashier) {
        cashierService.saveCashier(cashier, form);
        return "redirect:/cashier";
    }

    @GetMapping("{cashier}")
    public String userEditForm(@PathVariable Cashier cashier, Model model) {
        model.addAttribute("cashier", cashier);
        model.addAttribute("roles", Role.values());
        return "cashierEdit";
    }

    @GetMapping
    public String cashierList(Model model) {
        model.addAttribute("cashiers", cashierService.findAll());
        return "cashierList";
    }
}
