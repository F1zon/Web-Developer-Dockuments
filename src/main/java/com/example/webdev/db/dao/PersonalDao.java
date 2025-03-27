package com.example.webdev.db.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "сотрудники")
public class PersonalDao implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "post")
    private int post;
    @Column(name = "department")
    private int department;
    @Column(name = "mail")
    private String mail;
    @Column(name = "phone")
    private String phone;
    @Column(name = "pass")
    private String password;
    @Column(name = "enabled")
    private boolean enabled;

    @OneToMany(mappedBy = "personDao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AuthorizeDao> authorizeDaoList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorizeDaoList.stream()
                .map(authorizeDao -> new SimpleGrantedAuthority(authorizeDao.getAuthority()))
                .toList();
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
