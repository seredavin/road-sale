package ru.avokzal63.roadsale.repos.dto.ferma;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.avokzal63.roadsale.domain.dto.ferma.AuthTokenDataDto;

import java.util.Date;

public interface AuthTokenDataRepo extends JpaRepository<AuthTokenDataDto, String> {
    AuthTokenDataDto findTopByExpirationDateUtcAfter(Date date);
}
