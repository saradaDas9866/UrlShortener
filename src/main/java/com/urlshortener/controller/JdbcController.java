package com.urlshortener.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

@RestController
@Slf4j
@RequestMapping("/api")
public class JdbcController {

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String jdbcUsername;

    @Value("${spring.datasource.password}")
    private String jdbcPassword;

    @PostMapping("/createConnection")
    public ResponseEntity<String> createUser(@RequestBody String payLoad) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
        log.debug("Connections established");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from User");
        while (rs.next()) {
            log.debug("Name is : {} Email is : {}", rs.getString("name"), rs.getString("email"));
        }
        boolean isInserted = stmt.execute("INSERT INTO `user` ( `email`, `name`) VALUES ( \"pkdas@gmail.com\", \"PKDAS\");");

        return ResponseEntity.ok("JDBC connection has been made and query is executed!");
    }
}
