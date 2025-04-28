package com.urlshortener.repository;

import com.urlshortener.entity.UserSecret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSecretRepository extends JpaRepository<UserSecret, Long> {

    @Query(value = "SELECT * FROM user WHERE id = :id", nativeQuery = true)
    List<UserSecret> getUsers(@Param("id") Long id);
}
