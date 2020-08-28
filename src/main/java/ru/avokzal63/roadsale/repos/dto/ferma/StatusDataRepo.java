package ru.avokzal63.roadsale.repos.dto.ferma;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.avokzal63.roadsale.domain.dto.ferma.StatusDataDto;

public interface StatusDataRepo extends JpaRepository<StatusDataDto, Integer> {

}
