package com.rss.Entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by root on 15/01/17.
 */
@Entity
@Table(name="feeds", uniqueConstraints= @UniqueConstraint(columnNames={"url", "user_id"}))
public class Feed {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name="url")
    private String url;

    @NotNull
    @Column(name="user_id")
    private int user_id;

    public Feed(String url, int user_id) {
        this.url = url;
        this.user_id = user_id;
    }

    public Feed() {
    }

    public Long getId() {
        return this.id;
    }

    public String getUrl() { return this.url; }

    public int getUser_id() { return this.user_id; }

    public String feedToJSON() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(this);
    }
}
