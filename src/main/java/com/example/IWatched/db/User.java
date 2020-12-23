package com.example.IWatched.db;

import java.util.Collection;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements UserDetails {

  @Id
  @Size(min = 2, message = "Не меньше 2 знаков")
  private String username;

  private String name;

  private String surname;

  @Lob
  private byte[] avatar;

  @Size(min = 2, message = "Не меньше 2 знаков")
  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  private Set<Role> roles;

  @Transient
  private String passwordConfirm;

  @ManyToMany
  @JoinColumn(name = "movie_id")
  private Set<Movie> watchedMovies;
  @ManyToMany
  @JoinColumn(name = "movie_id")
  private Set<Movie> wantedMovies;

  public User() {
  }

  public User(String username) {
    this.username = username;
  }

  public Set<Movie> getWatchedMovies() {
    return watchedMovies;
  }

  public Set<Movie> getWantedMovies() {
    return wantedMovies;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return getRoles();
  }

  @Override
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
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

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public String getPasswordConfirm() {
    return passwordConfirm;
  }

  public void setPasswordConfirm(String passwordConfirm) {
    this.passwordConfirm = passwordConfirm;
  }

  public void addMovieToWanted(Movie movie) {
    this.wantedMovies.add(movie);
  }

  public void addMovieToWatched(Movie movie) {
    this.watchedMovies.add(movie);
  }

  public void deleteMovieFromWanted(Movie movie) {
    this.wantedMovies.remove(movie);
  }

  public void deleteMovieFromWatched(Movie movie) {
    this.watchedMovies.remove(movie);
  }

  public boolean isMovieWatched(Movie movie) {
    return this.watchedMovies.contains(movie);
  }

  public boolean isMovieWanted(Movie movie) {
    return this.wantedMovies.contains(movie);
  }

  public byte[] getAvatar() {
    return avatar;
  }
}

