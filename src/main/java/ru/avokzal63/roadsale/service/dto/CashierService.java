package ru.avokzal63.roadsale.service.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.avokzal63.roadsale.domain.Cashier;
import ru.avokzal63.roadsale.repos.dto.CashierRepo;

import java.util.List;
import java.util.Map;

@Service
public class CashierService {
    @Autowired
    private CashierRepo cashierRepo;
    public void addCashier(Cashier cashier) {
        if (cashierRepo.countByName(cashier.getName()) == 0)
            cashierRepo.save(cashier);
    }

    public List<Cashier> findAll() {
        return cashierRepo.findAll();
    }

    public Cashier findByName(String name) {
        return cashierRepo.findOneByName(name);
    }

    public void saveCashier(Cashier cashier, Map<String, String> form) {
        if (form.get("name").isEmpty()) return;
        if (cashierRepo.countByName(form.get("name")) != 0
                && !form.get("name").equals(cashier.getName())) return;
            cashier.setName(form.get("name"));
        if (!form.get("inn").isEmpty()) {
            cashier.setInn(form.get("inn"));
        }
        cashierRepo.save(cashier);
    }
}
