package ru.job4j.shortcut.job4j_url_shortcut.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.shortcut.job4j_url_shortcut.domain.Url;
import ru.job4j.shortcut.job4j_url_shortcut.service.UrlService;

import java.util.Optional;

@RestController
@RequestMapping("/redirect")
public class RedirectController {

    private final UrlService urlService;

    public RedirectController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> redirect(@PathVariable String code) {
        try {
            Optional<Url> rsl = urlService.findByKey(code);
            return new ResponseEntity<>(rsl.get().getUrl(), HttpStatus.FOUND);
        } catch (IllegalArgumentException iae) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "URL not found");
        }
    }
}
