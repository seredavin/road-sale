package ru.avokzal63.roadsale.service.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.avokzal63.roadsale.domain.Cheque;
import ru.avokzal63.roadsale.domain.OfdConfigure;
import ru.avokzal63.roadsale.domain.dto.ferma.ReceiptIdResponseDto;
import ru.avokzal63.roadsale.repos.dto.ChequeRepo;
import ru.avokzal63.roadsale.repos.dto.TicketRepo;
import ru.avokzal63.roadsale.service.dto.ferma.AuthTokenService;

import java.util.List;

@Service
public class ChequeService {
    @Autowired
    private ChequeRepo chequeRepo;
    @Autowired
    private TicketRepo ticketRepo;
    @Autowired
    private AuthTokenService authTokenService;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OfdConfigureService ofdConfigureService;

    public Cheque saveCheque(Cheque cheque) {
        return chequeRepo.save(cheque);
    }

    public boolean ifExist(Cheque cheque) {
        return chequeRepo.countAllByInvoiceId(cheque.getInvoiceId()) != 0;
    }

    public void updateCheque(Cheque cheque) {
        Cheque chequeFromDb = chequeRepo.findOneById(cheque.getId());
        chequeFromDb.setReceiptId(cheque.getReceiptId());
        chequeRepo.save(chequeFromDb);
    }

    public String send(Cheque cheque) throws JsonProcessingException {
        OfdConfigure ofdConfigure = ticketRepo.findByChequeIs(cheque).getOfdConfigure();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "{\n" +
                "\"Request\":"
        + mapper.writeValueAsString(cheque)
        +"}";
        HttpEntity<String> request = new HttpEntity<>(jsonString, headers);
        String url = ofdConfigure.getServiceUrl()
                + "/kkt/cloud/receipt?AuthToken="
                + authTokenService.getAuthTokenDto(1).getAuthToken();
        try {
            ResponseEntity<ReceiptIdResponseDto> exchange = restTemplate.exchange(url,
                    HttpMethod.POST,
                    request,
                    new ParameterizedTypeReference<ReceiptIdResponseDto>() {
                    });
            ReceiptIdResponseDto receiptIdResponseDto = exchange.getBody();
            return receiptIdResponseDto.getData().getReceiptId();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public List<Cheque> getNoResponseCheques() {
        return chequeRepo.getAllByReceiptIdNotNullAndStatusDataDtoIsNull();
    }

}
