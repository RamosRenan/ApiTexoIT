package com.br.texo.apitexo.repositories;

import com.br.texo.apitexo.entities.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface MovieRepository extends JpaRepository<MovieEntity, Integer>
{

}
