package com.quickquiz.entities;

import java.time.LocalDateTime;
import org.bson.types.ObjectId;

public class UserSchema {

    public UserSchema(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserSchema(String name, String email, String username, String gender, String password, LocalDateTime current) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.gender = gender;
        this.password = password;
        this.current = current;
    }

    public UserSchema() {
    }

    public UserSchema(ObjectId id, String name, String email, String username, String gender, String password, LocalDateTime current) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.gender = gender;
        this.password = password;
        this.current = current;
    }

    private ObjectId id;
    private String name;
    private String email;
    private String username;
    private String gender;
    private String password;
    private LocalDateTime current;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCurrent() {
        return current;
    }

    public void setCurrent(LocalDateTime current) {
        this.current = current;
    }

}
