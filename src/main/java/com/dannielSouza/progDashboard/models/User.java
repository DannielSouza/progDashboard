package com.dannielSouza.progDashboard.models;

import com.dannielSouza.progDashboard.models.Enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String password;
    private String office;
    private String image;
    @Column(name = "id_company")
    private Long idCompany;
    @JsonIgnore
    @OneToMany
    private List<Task> tasksList = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime lastLogin;

    public User(String name, String username, String password, Long idCompany, String office) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.idCompany = idCompany;
        this.office = office;
        setRole(Role.USER);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    @Override
    public boolean isEnabled() {
        return true;
    }
}
