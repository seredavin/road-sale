package ru.avokzal63.roadsale.repos.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.avokzal63.roadsale.domain.Cheque;

import java.util.List;

public interface ChequeRepo extends JpaRepository<Cheque, Integer> {
    Cheque findOneById(int id);
    Integer countAllByInvoiceId(String id);
    List<Cheque> getAllByReceiptIdNotNullAndStatusDataDtoIsNull();
}
