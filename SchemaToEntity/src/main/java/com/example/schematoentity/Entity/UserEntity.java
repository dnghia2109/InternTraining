package com.example.schematoentity.Entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "user", schema = "db_demo_secu_03", catalog = "")
public class UserEntity {
    private long id;
    private String email;
    private String name;
    private String password;
    private Object role;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "role")
    public Object getRole() {
        return role;
    }

    public void setRole(Object role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id && Objects.equals(email, that.email) && Objects.equals(name, that.name) && Objects.equals(password, that.password) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, name, password, role);
    }
}
