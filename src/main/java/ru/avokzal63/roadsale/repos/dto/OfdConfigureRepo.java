package ru.avokzal63.roadsale.repos.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.avokzal63.roadsale.domain.OfdConfigure;

public interface OfdConfigureRepo extends JpaRepository<OfdConfigure, String> {
    OfdConfigure findByName(String name);
    OfdConfigure findById(Integer id);
}
