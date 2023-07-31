package com.br.texo.apitexo.service;

import com.br.texo.apitexo.entities.MovieEntity;
import com.br.texo.apitexo.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService
{
    @Autowired
    MovieRepository movieRepository;

    public List<MovieEntity> saveAll(List<MovieEntity> movieEntityList)
    {
        return movieRepository.saveAll(movieEntityList);
    }
}
