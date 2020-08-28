package ru.avokzal63.roadsale.repos.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.avokzal63.roadsale.domain.Cheque;
import ru.avokzal63.roadsale.domain.Ticket;

public interface TicketRepo extends JpaRepository<Ticket, Integer> {
    Ticket findByChequeIs(Cheque cheque);

    Ticket findOneById(String id);
}
