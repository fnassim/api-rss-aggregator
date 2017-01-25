package com.rss.Entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by nassim_a on 30-Dec-16.
 */
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name="username", unique = true)
    private String username;

    @NotNull
    @Column(name="password")
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public String getUsername() { return this.username; }

    public String getPassword() { return this.password; }

    public String userToJSON() {
        Gson gson = new Gson();

        return gson.toJson(this);
    }
}
