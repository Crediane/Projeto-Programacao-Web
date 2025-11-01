package com.projetoweb.trabalhoapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetoweb.trabalhoapi.model.entity.TurmaEntity;

@Repository
public interface TurmaRepository extends JpaRepository<TurmaEntity, Long> { 

    TurmaEntity findByNome(String nome);

}