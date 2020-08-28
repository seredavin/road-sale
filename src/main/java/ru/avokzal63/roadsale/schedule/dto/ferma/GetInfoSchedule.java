package ru.avokzal63.roadsale.schedule.dto.ferma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.avokzal63.roadsale.domain.Cheque;
import ru.avokzal63.roadsale.domain.dto.ferma.StatusDataDto;
import ru.avokzal63.roadsale.service.dto.ChequeService;
import ru.avokzal63.roadsale.service.dto.ferma.StatusDataService;

import java.text.SimpleDateFormat;

@Service
public class GetInfoSchedule {
    @Autowired
    private ChequeService chequeService;

    @Autowired
    private StatusDataService statusDataService;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 30000)
    public void reportCurrentTime() {
        for (Cheque cheque : chequeService.getNoResponseCheques()) {
            System.out.println(cheque.getReceiptId());
            StatusDataDto statusDataDto = statusDataService.getStatusDataDto(cheque);
            statusDataService.save(statusDataDto);
            cheque.setStatusDataDto(statusDataDto);
            chequeService.saveCheque(cheque);
        }

    }

}
