package ru.avokzal63.roadsale.repos.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.avokzal63.roadsale.domain.Cashier;

public interface CashierRepo extends JpaRepository<Cashier, Integer> {
    Cashier findOneById(Integer id);

    Integer countByName(String name);

    Cashier findOneByName(String name);
}
