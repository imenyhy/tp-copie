package com.example.tp.security;

import com.example.tp.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserPrincipal implements UserDetails {

    private User user;

    public UserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        // Ajoute tous les rôles de l'utilisateur à la collection d'autorités
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword(); // Retourne le mot de passe de l'utilisateur
    }

    @Override
    public String getUsername() {
        return user.getEmail(); // Retourne l'email comme nom d'utilisateur
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // L'account n'est jamais expiré
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // L'account n'est jamais verrouillé
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Les identifiants ne sont jamais expirés
    }

    @Override
    public boolean isEnabled() {
        return true; // L'utilisateur est toujours actif
    }

    public User getUser() {
        return user;
    }
}
