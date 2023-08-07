package com.br.texo.apitexo.repositories;

import com.br.texo.apitexo.entities.MovieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface MovieRepository extends Repository<MovieEntity, Integer>
{
    public Page<MovieEntity> findAll(Pageable pageable);

    public List<MovieEntity> findByWinner(String isWinner);
}
