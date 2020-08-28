package ru.avokzal63.roadsale.service.dto.ferma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.avokzal63.roadsale.domain.OfdConfigure;
import ru.avokzal63.roadsale.domain.dto.ferma.AuthTokenDataDto;
import ru.avokzal63.roadsale.domain.dto.ferma.AuthTokenResponseDto;
import ru.avokzal63.roadsale.repos.dto.ferma.AuthTokenDataRepo;
import ru.avokzal63.roadsale.service.dto.OfdConfigureService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthTokenService {
    private final RestTemplate restTemplate;
    @Autowired
    AuthTokenDataRepo authTokenDataRepo;
    @Autowired
    OfdConfigureService ofdConfigureService;


    public AuthTokenService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AuthTokenDataDto getAuthTokenDto(int ofdConfigureId) {
        AuthTokenDataDto authTokenDataDto = authTokenDataRepo.findTopByExpirationDateUtcAfter(new Date());
        if (authTokenDataDto != null) return authTokenDataDto;
        HashMap<String, String> credentials = new HashMap<>();
        OfdConfigure ofdConfigure = ofdConfigureService.getOfdConfigureById(ofdConfigureId);
        credentials.put("Login", ofdConfigure.getServiceUser());
        credentials.put("Password", ofdConfigure.getServicePassword());
        HttpEntity<Map<String, String>> request = new HttpEntity<>(credentials);
        AuthTokenResponseDto authTokenResponseDto = restTemplate.exchange(ofdConfigure.getServiceUrl() + "/Authorization/CreateAuthToken",
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<AuthTokenResponseDto>() {
                }).getBody();
        authTokenDataDto = authTokenResponseDto.getData();
        return authTokenDataDto;
    }
}
