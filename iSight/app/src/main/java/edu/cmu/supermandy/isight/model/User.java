package edu.cmu.supermandy.isight.model;

/**
 * Created by Mandy on 4/11/16.
 */
public class User {
    String username;
    String email;
    String password;
    Integer age;
    String phoneNum;

    public User(String username, String email, String password, Integer age, String phoneNum) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.age = age;
        this.phoneNum = phoneNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
