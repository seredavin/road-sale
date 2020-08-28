package ru.avokzal63.roadsale.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.avokzal63.roadsale.domain.User;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsernameAndActivationCodeIsNull(String name);

    User findByUsername(String username);
}
