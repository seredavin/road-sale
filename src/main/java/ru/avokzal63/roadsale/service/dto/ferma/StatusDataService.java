package ru.avokzal63.roadsale.service.dto.ferma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.avokzal63.roadsale.domain.Cheque;
import ru.avokzal63.roadsale.domain.OfdConfigure;
import ru.avokzal63.roadsale.domain.dto.ferma.StatusDataDto;
import ru.avokzal63.roadsale.domain.dto.ferma.StatusResponseDto;
import ru.avokzal63.roadsale.repos.dto.ferma.StatusDataRepo;
import ru.avokzal63.roadsale.service.dto.TicketService;

@Service
public class StatusDataService {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private AuthTokenService authTokenService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StatusDataRepo statusDataRepo;

    public StatusDataDto getStatusDataDto(Cheque cheque) {
        OfdConfigure ofdConfigure = ticketService.getTicketByCheque(cheque).getOfdConfigure();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String jsonString = "{\n" +
                "\"Request\":"
                + "{\"ReceiptId\": \"" + cheque.getReceiptId()
                + "\"}"
                +"}";
        HttpEntity<String> request = new HttpEntity<>(jsonString, headers);
        String url = ofdConfigure.getServiceUrl()
                + "/kkt/cloud/status?AuthToken="
                + authTokenService.getAuthTokenDto(ofdConfigure.getId()).getAuthToken();
        try {
            ResponseEntity<StatusResponseDto> exchange = restTemplate.exchange(url,
                    HttpMethod.POST,
                    request,
                    new ParameterizedTypeReference<StatusResponseDto>() {
                    });
            StatusResponseDto statusResponseDto = exchange.getBody();
            System.out.println(statusResponseDto.getData().getStatusDataDeviceDto().getFpd());
            return statusResponseDto.getData();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void save(StatusDataDto statusDataDto) {
        statusDataRepo.save(statusDataDto);
    }
}
