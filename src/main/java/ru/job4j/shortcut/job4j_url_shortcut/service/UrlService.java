package ru.job4j.shortcut.job4j_url_shortcut.service;

import org.springframework.stereotype.Service;
import ru.job4j.shortcut.job4j_url_shortcut.domain.Url;
import ru.job4j.shortcut.job4j_url_shortcut.repository.UrlRepository;
import ru.job4j.shortcut.job4j_url_shortcut.utils.PassGen;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public Optional<Url> findByUrl(String url) {
        return urlRepository.findByUrl(url);
    }

    public Url create(String url) {
        Url rsl = new Url();
        rsl.setUrl(url);
        rsl.setKey(PassGen.generate());
        rsl.setCount(0);
        urlRepository.save(rsl);
        return rsl;
    }

    public Optional<Url> findByKey(String key) throws IllegalArgumentException {
        Optional<Url> rsl = urlRepository.findByKey(key);
        if (rsl.isEmpty()) {
            throw new IllegalArgumentException();
        }
        urlRepository.incrementCounter(key);
        return rsl;
    }

    public List<Url> findAll() {
        return StreamSupport
                .stream(this.urlRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
