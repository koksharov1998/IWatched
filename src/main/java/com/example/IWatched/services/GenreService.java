package com.example.IWatched.services;

import com.example.IWatched.db.Genre;
import com.example.IWatched.db.Movie;
import com.example.IWatched.db.Rating;
import com.example.IWatched.repos.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService implements BdService<Genre> {
    private GenreRepository genreRepository;

    @Autowired
    public void DataInit(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> findAll() {
        return (List<Genre>) genreRepository.findAll();
    }

    @Override
    public Genre findById(int id) {
        return genreRepository.findById(id).get();
    }

    @Override
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }
}
