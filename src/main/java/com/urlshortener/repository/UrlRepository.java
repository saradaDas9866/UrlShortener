package com.urlshortener.repository;

import com.urlshortener.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface  UrlRepository extends JpaRepository<Url, Long> {

    @Query(value = "SELECT * FROM user WHERE id = :id", nativeQuery = true)
    List<Url> getUsers(@Param("id") Long id);
}
