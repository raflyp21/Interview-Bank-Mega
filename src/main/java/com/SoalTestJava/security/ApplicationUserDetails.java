package com.SoalTestJava.security;

import com.SoalTestJava.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class ApplicationUserDetails implements UserDetails {

    private Long user_id;
    private String username;
    private String password;
    private List<GrantedAuthority> authorities = new ArrayList<>();

    public ApplicationUserDetails(User account) {
        this.user_id = account.getId();
        this.username = account.getUsername();
        this.password = account.getPassword();
       }

    public Long getUserId() {
        return this.user_id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationUserDetails that = (ApplicationUserDetails) o;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(authorities, that.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, authorities);
    }
}
