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

    @Column(name="name")
    String firstname;

    public User(Long id, String firstname) {
        this.id = id;
        this.firstname = firstname;
    }

    public User() {
    }

    @Column(name="description")
    String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstname;
    }
}
