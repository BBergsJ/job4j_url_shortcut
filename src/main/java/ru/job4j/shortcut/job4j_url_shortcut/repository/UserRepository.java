package ru.job4j.shortcut.job4j_url_shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.shortcut.job4j_url_shortcut.domain.UserDAO;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserDAO, Integer> {
    UserDAO findByUsername(String username);
    Optional<UserDAO> findBySite(String site);
}
