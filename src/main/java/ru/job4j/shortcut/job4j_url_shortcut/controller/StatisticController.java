package ru.job4j.shortcut.job4j_url_shortcut.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.shortcut.job4j_url_shortcut.domain.DTOUrl;
import ru.job4j.shortcut.job4j_url_shortcut.domain.Url;
import ru.job4j.shortcut.job4j_url_shortcut.service.UrlService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/statistic")
public class StatisticController {

    private final UrlService urlService;

    public StatisticController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping
    public List<DTOUrl> statistic() {
        List<Url> rsl = urlService.findAll();
        return rsl.stream().map(u -> DTOUrl.of(u.getUrl(), u.getCount())).collect(Collectors.toList());
    }
}