package ru.job4j.shortcut.job4j_url_shortcut.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.shortcut.job4j_url_shortcut.domain.DTOUrl;
import ru.job4j.shortcut.job4j_url_shortcut.domain.Url;
import ru.job4j.shortcut.job4j_url_shortcut.service.UrlService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/convert")
public class ConvertController {

    private final UrlService urlService;

    public ConvertController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping
    public ResponseEntity<?> convert(@Valid @RequestBody DTOUrl dtoUrl) {
        Optional<Url> rsl = urlService.findByUrl(dtoUrl.getUrl());
        if (rsl.isEmpty()) {
            rsl = Optional.ofNullable(urlService.create(dtoUrl.getUrl()));
        }
        return ResponseEntity.ok("code:" + rsl.get().getKey());
    }
}
