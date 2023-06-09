package com.project.Booktion.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity
@Table(name="clients")
public class User implements Serializable {
    @Id
    @Column(name="client_id")
    @NotEmpty @Size(min=4, max=20)
    private String userId;
    @NotEmpty
    @Size(min=5)
    private String password;
    @NotEmpty
    private String name;
    @NotEmpty @Email
    private String email;
    @NotNull
    @Pattern(regexp = "0\\d{2}-\\d{4}-\\d{4}|0\\d{2}-\\d{3}-\\d{4}")
    private String phoneNumber;
    @Embedded
    private Address address;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User() {
    }
}
