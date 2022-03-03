package ru.job4j.shortcut.job4j_url_shortcut.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.shortcut.job4j_url_shortcut.domain.UserDAO;
import ru.job4j.shortcut.job4j_url_shortcut.repository.UserRepository;
import ru.job4j.shortcut.job4j_url_shortcut.utils.PassGen;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    private final UserRepository userRepository;
    private BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public UserDAO findByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    public List<UserDAO> findAll() {
        return StreamSupport
                .stream(this.userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public UserDAO create(String site) {
        UserDAO rsl = new UserDAO();
        rsl.setSite(site);
        rsl.setUsername(PassGen.generate());
        rsl.setPassword(encoder.encode(PassGen.generate()));
        return userRepository.save(rsl);
    }

    public Optional<UserDAO> findByUrl(String site) {
        return userRepository.findBySite(site);
    }

    public UserDAO save(UserDAO userDAO) {
        return userRepository.save(userDAO);
    }
}
