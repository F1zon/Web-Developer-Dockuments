package com.example.webdev.db.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authorize")
public class AuthorizeDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;
    @Column(name = "authority")
    private String authority;

    @ManyToOne
    @JoinColumn(name = "personal_id")
    private PersonalDao personDao;
}
