package com.urlshortener.repository;

import com.urlshortener.dto.UserDto;
import com.urlshortener.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface  UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM user WHERE id = :id", nativeQuery = true)
    List<User> getUsers(@Param("id") Long id);

    @Query(value = "SELECT * FROM user WHERE email = :email", nativeQuery = true)
    Optional<User> getUser(@Param("email") String email);

    @Query("SELECT new com.urlshortener.dto.UserDto(u.id, u.email, u.name) FROM User u")
    List<UserDto> findAllUsers();

}
