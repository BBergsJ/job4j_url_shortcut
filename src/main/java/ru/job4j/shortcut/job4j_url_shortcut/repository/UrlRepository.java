package ru.job4j.shortcut.job4j_url_shortcut.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.job4j.shortcut.job4j_url_shortcut.domain.Url;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UrlRepository extends CrudRepository<Url, Integer> {
    Optional<Url> findByUrl(String url);
    Optional<Url> findByKey(String key);

    @Transactional
    @Modifying
    @Query("update Url u set u.count = u.count+1 where u.key = :key")
    void incrementCounter(@Param("key")String key);
}
