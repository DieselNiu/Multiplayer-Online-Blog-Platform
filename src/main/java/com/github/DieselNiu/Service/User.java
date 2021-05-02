package com.github.DieselNiu.Service;

public class User {
    Integer id;

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
