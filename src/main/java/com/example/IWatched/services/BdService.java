package com.example.IWatched.services;
import java.util.List;

public interface BdService<T> {
    List<T> findAll();
    T findById(int id);
    T save(T obj);
}
