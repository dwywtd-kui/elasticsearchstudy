package com.haan.elasticsearchstudy.entity;

public class User {
    private String user;
    private Integer age;
    private String message;

    public User() {
    }

    public User(String user, Integer age, String message) {
        this.user = user;
        this.age = age;
        this.message = message;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "User{" +
                "user='" + user + '\'' +
                ", age=" + age +
                ", message='" + message + '\'' +
                '}';
    }
}
