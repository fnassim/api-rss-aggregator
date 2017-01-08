package com.rss.Entities;

import javax.persistence.*;

/**
 * Created by nassim_a on 30-Dec-16.
 */
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="username", unique = true)
    private String username;

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
}
