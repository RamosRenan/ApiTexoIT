package com.br.texo.apitexo.service;

import com.br.texo.apitexo.entities.MovieEntity;
import com.br.texo.apitexo.repositories.MovieJpaRepository;
import com.br.texo.apitexo.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService
{
    /**
     * Obtenho do contexto uma instância de movie repossitory
     *
     */
    @Autowired
    MovieJpaRepository movieJpaRepository;

    @Autowired
    MovieRepository movieRepository;

    /**
     * Salva uma lista de movies de forma massiva
     *
     * @param movieEntityList
     * @return
     */
    public List<MovieEntity> saveAll(List<MovieEntity> movieEntityList)
    {
        return movieJpaRepository.saveAll(movieEntityList);
    }

    /**
     * Obtenho todos os 50 primeiros registros
     *
     * @return
     */
    public Page<MovieEntity> findTop50()
    {
        Pageable pageable = PageRequest.of(0, 50);
        return movieRepository.findAll(pageable);
    }

    /**
     * Obtem todos os filmes vencedores
     *
     */
    public List<MovieEntity> findByWinner()
    {
        return this.movieRepository.findByWinner("yes");
    }
}
