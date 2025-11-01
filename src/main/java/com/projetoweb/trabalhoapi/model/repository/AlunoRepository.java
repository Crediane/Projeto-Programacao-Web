package com.projetoweb.trabalhoapi.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetoweb.trabalhoapi.model.entity.AlunoEntity;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoEntity, Long> {


    List<AlunoEntity> findByNome(String nome);

}