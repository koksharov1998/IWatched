package com.example.IWatched.db;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Id
  @Size(min=2, message = "Не меньше 2 знаков")
  private String username;

  private String name;

  private String surname;

  @Size(min=2, message = "Не меньше 2 знаков")
  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  private Set<Role> roles;

  @Transient
  private String passwordConfirm;

  public User() {
  }

  public User(String username) {
    this.username = username;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return getRoles();
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

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles(){
    return roles;
  }

  public void setRoles(Set<Role> roles){
    this.roles = roles;
  }

  public String getPasswordConfirm() {
    return passwordConfirm;
  }

  public void setPasswordConfirm(String passwordConfirm) {
    this.passwordConfirm = passwordConfirm;
  }
}

