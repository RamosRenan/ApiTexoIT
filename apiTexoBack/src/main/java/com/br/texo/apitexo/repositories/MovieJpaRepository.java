package com.br.texo.apitexo.repositories;

import com.br.texo.apitexo.entities.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieJpaRepository extends JpaRepository<MovieEntity, Integer>
{
}
