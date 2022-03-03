package ru.job4j.shortcut.job4j_url_shortcut.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.shortcut.job4j_url_shortcut.domain.DTOUserRequest;
import ru.job4j.shortcut.job4j_url_shortcut.domain.UserDAO;
import ru.job4j.shortcut.job4j_url_shortcut.service.UserService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/registration")
public class RegController {

    private final UserService userService;

    public RegController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> registration(@Valid @RequestBody DTOUserRequest userRequest) {
        Optional<UserDAO> rsl = userService.findByUrl(userRequest.getSite());
        if (rsl.isPresent()) {
            return ResponseEntity.ok(
                    "registration : false"
            );
        }
        UserDAO userDAO = userService.create(userRequest.getSite());
        return ResponseEntity.ok(
                String.format("registration : %b, login: %s, password : %s",
                        true, userDAO.getUsername(), userDAO.getPassword())
        );
    }
}
