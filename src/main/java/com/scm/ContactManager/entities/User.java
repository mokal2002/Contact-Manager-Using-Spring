package com.scm.ContactManager.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity(name="user")
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    private String userId;
    @Column(name="user_name", nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Getter(AccessLevel.NONE)
    private String password;
    @Column(length = 1000)
    private String about;
    private String profilePic;
    private String phoneNumber;

    // Information
    @Getter(AccessLevel.NONE)
    private boolean enabled=false;
    private boolean emailVerified=false;
    private boolean phoneVerified=false;
    @Enumerated(value = EnumType.STRING)
    // Relationships self, google and facebook
    private Provider provider = Provider.SELF;
    private String providerUserId;

    @OneToMany(mappedBy = "user", cascade= CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<Contact>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roleList = new ArrayList<String>();

    private String emailToken;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> roles= roleList.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());

        return roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
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
        return this.enabled;
    }
}
