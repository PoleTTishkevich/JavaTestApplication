package com.tishkevich.spring.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_account", uniqueConstraints = {@UniqueConstraint(name = "VK_USER_UK", columnNames = "username")})
public class UserAccount {

    @Override
    public String toString() {
        return "UserAccount [id=" + id + ", username=" + username + ", password=" + password + "]";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 36, nullable = false)
    private String username;

    @Column(name = "password", length = 128, nullable = false)
    private String password;
}
